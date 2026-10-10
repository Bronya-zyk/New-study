package query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private static final String SERVER_URL = "http://127.0.0.1:8080/query";//设置接口

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try{
                QueryRequest req = new QueryRequest();
                System.out.print("请输入快递单号（输入exit退出）: ");
                String trackingNumber = scanner.nextLine().trim();
                // TODO 1: 输入exit时退出循环（
                if(trackingNumber.equals("exit"))break;
                System.out.print("请输入手机号：");
                String phone = scanner.nextLine().trim();
                req.setTrackingNumber(trackingNumber);
                req.setPhone(phone);

                // TODO 2: 用fastjson构造json格式的请求体，样例为
                String json = "{\"trackingNumber\":\"" + req.getTrackingNumber() + "\",\"phone\":\"" + req.getPhone() + "\"}";//手动转json
               // TODO 3: 创建HttpURLConnection，把上面的json写入请求体并发送HTTP POST请求
                URL url = URI.create(SERVER_URL).toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {//连接，输出流
                    writer.write(json);
                }

                int respCode = connection.getResponseCode();
                if (respCode == 200) {
                    String sb=readBody(connection.getInputStream());
                    System.out.println("服务器返回：" + sb);
                } else {
                    System.out.println("请求失败，code:" + respCode);
                }
                connection.disconnect();
            } catch (Exception e) {
                System.out.println("请求发生错误: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("客户端已退出");
    }
    private static String readBody(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}