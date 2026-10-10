package contect;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // 初始化 JLine 终端
            Terminal terminal = TerminalBuilder.builder().system(true).build()
        ) {
            System.out.println("已连接到服务器 " + SERVER_IP + ":" + SERVER_PORT);
            
            // 创建 LineReader，设置历史文件路径（任务 4 要求）
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .variable(LineReader.HISTORY_FILE, "client_history.txt") // 历史记录保存在此文件
                    .build();

            // 开启一个线程专门接收服务器消息，重写run
            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println("\n[服务器] " + serverMsg);
                        System.out.print("> "); // 提示符
                    }
                } catch (IOException e) {
                    System.out.println("服务器连接已断开。");
                }
            }).start();

            // 主线程负责读取用户输入并发送
            while (true) {
                // 使用 JLine 读取输入，支持上下键历史记录
                String userInput = reader.readLine("> ");
                
                if (userInput == null || "exit".equalsIgnoreCase(userInput.trim())) {
                    out.println("exit");
                    break;
                }
                
                if (!userInput.trim().isEmpty()) {
                    out.println(userInput);
                }
            }

        } catch (IOException e) {
            System.err.println("客户端异常: " + e.getMessage());
            System.err.println("请确保服务器已启动。");
        }
    }
}