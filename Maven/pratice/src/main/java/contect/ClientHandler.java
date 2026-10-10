package contect;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private String username;
    private String logFilePath;
    
    // 模拟数据库，存储注册用户 (用户名 -> 密码)
    private static final Map<String, String> userDatabase = new ConcurrentHashMap<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // 1. 生成该连接的日志文件路径
            logFilePath = LogUtil.generateLogFileName(socket.getPort());
            LogUtil.writeLog(logFilePath, "客户端连接建立");

            // 2. 处理登录/注册
            if (!handleAuth(in, out)) {
                return; // 认证失败，断开连接
            }

            // 3. 通信循环
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(clientMessage)) {
                    out.println("服务器: 连接已断开。");
                    LogUtil.writeLog(logFilePath, "客户端主动断开连接");
                    break;
                }

                // 格式化输出：用户名—通信内容
                String formattedMsg = username + "—" + clientMessage;
                System.out.println("收到消息: " + formattedMsg);
                
                // 记录日志
                LogUtil.writeLog(logFilePath, "收到: " + formattedMsg);

                // 反馈给客户端
                String response = "服务器已收到 [" + username + "] 的消息: " + clientMessage;
                out.println(response);
                LogUtil.writeLog(logFilePath, "发送: " + response);
            }

        } catch (IOException e) {
            System.err.println("客户端通信异常: " + e.getMessage());
            if (logFilePath != null) {
                LogUtil.writeLog(logFilePath, "通信异常: " + e.getMessage());
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理用户认证（登录/注册）
     */
    private boolean handleAuth(BufferedReader in, PrintWriter out) throws IOException {
        while (true) {
            out.println("请输入指令 (LOGIN 用户名 密码) 或 (REGISTER 用户名 密码):");
            String authLine = in.readLine();
            if (authLine == null) return false;

            String[] parts = authLine.split(" ");
            if (parts.length != 3) {
                out.println("格式错误，请重新输入。");
                continue;
            }

            String command = parts[0].toUpperCase();
            String user = parts[1];
            String pass = parts[2];

            if ("REGISTER".equals(command)) {
                if (userDatabase.containsKey(user)) {
                    out.println("注册失败：用户名已存在。");
                } else {
                    userDatabase.put(user, pass);
                    out.println("注册成功！请使用 LOGIN 登录。");
                }
            } else if ("LOGIN".equals(command)) {
                if (userDatabase.containsKey(user) && userDatabase.get(user).equals(pass)) {
                    this.username = user;
                    out.println("登录成功！欢迎 " + user + "。可以开始聊天了 (输入 exit 退出)。");
                    LogUtil.writeLog(logFilePath, "用户 " + user + " 登录成功");
                    return true;
                } else {
                    out.println("登录失败：用户名或密码错误。");
                }
            } else {
                out.println("未知指令，请输入 LOGIN 或 REGISTER。");
            }
        }
    }
}