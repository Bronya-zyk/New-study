package com.express;

import com.alibaba.fastjson2.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 8080;
    // key:快递单号_手机号, value:取件码
    private static final Map<String, String> expressMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        initializeExpressData();
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/query", new QueryHandler());//重载handler
        server.setExecutor(null);//单线程
        server.start();//启动服务器，后台子线程监听，main的代码继续跑
        System.out.println("✅ Server started on port " + PORT);
    }

    private static void initializeExpressData() {
        // 作业给的测试数据
        expressMap.put("SF123456789_13005433678", "1234");
        expressMap.put("JD987654321_19805433168", "5678");
        expressMap.put("YT456789123_13905479698", "9012");
        expressMap.put("ZT789123456_18505433664", "3456");
    }

    static class QueryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            QueryResponse resp = new QueryResponse();
            
            String method = exchange.getRequestMethod();
            if (!"POST".equals(method)) {//String可以接.equal
                resp.setMsg("仅支持POST请求");
                sendResponse(exchange, 405, resp);
                return;
            }

            try {
                //读取请求体
                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);//字节转json字符串
                QueryRequest request = JSON.parseObject(body, QueryRequest.class);//json转对象

                String trackingNumber = request.getTrackingNumber();
                String phone = request.getPhone();

                // TODO 2: 校验单号和手机号不能为空，不合法就抛异常走400分支

                if (trackingNumber == null || trackingNumber.isBlank() || phone == null || phone.isBlank()) {
                    resp.setMsg("快递单号或手机号不能为空");
                    resp.setPickCode(null);
                    sendResponse(exchange, 400, resp);
                    return;
                }

                // TODO 3: 用"单号_手机号"拼成key，在expressMap中查询取件码

                String key = trackingNumber + "_" + phone;
                String pickCode = expressMap.get(key);
                // TODO 4: 根据查询结果构造QueryResponse

                if (pickCode == null) {
                    resp.setMsg("未找到该快递");
                    resp.setPickCode(null);
                } else {
                    resp.setMsg("查询成功");
                    resp.setPickCode(pickCode);
                }
                // TODO 5: 把响应对象序列化成JSON并发送响应（状态码200）
                sendResponse(exchange, 200, resp);

            } catch (Exception e) {
                resp.setMsg("请求参数错误");
                resp.setPickCode(null);
                sendResponse(exchange, 400, resp);
            }
        }

        private void sendResponse(HttpExchange exchange, int statusCode, QueryResponse resp) throws IOException {//自己封装的函数
            String json = JSON.toJSONString(resp);//转json字符串
            exchange.getResponseHeaders().set("Content-Type", "application/json;charset=utf-8");//设置响应头
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);//转字节
            exchange.sendResponseHeaders(statusCode, bytes.length);//状态和json
            try (OutputStream os = exchange.getResponseBody()) {//获取输出流
                os.write(bytes);//完成网络传输
            }
            exchange.close();
        }
    }
}