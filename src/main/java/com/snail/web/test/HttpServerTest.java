package com.snail.web.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServerTest {
    public static void main(String[] args) {
        try {

            /*监听端口号，只要是8888就能接收到*/
            ServerSocket ss = new ServerSocket(8888);

            while (true) {
                /*实例化客户端，固定套路，通过服务端接受的对象，生成相应的客户端实例*/
                Socket socket = ss.accept();
                /*获取客户端输入流，就是请求过来的基本信息：请求头，换行符，请求体*/
                BufferedReader bd = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                /**
                 * 接受HTTP请求，并解析数据
                 */
                String requestHeader;
                int contentLength = 0;
                while ((requestHeader = bd.readLine()) != null && !requestHeader.isEmpty()) {
                    System.out.println(requestHeader);
                    /**
                     * 获得GET参数
                     */
                    if (requestHeader.startsWith("GET")) {
                        int begin = requestHeader.indexOf("/?") + 2;
                        int end = requestHeader.indexOf("HTTP/");
                        String condition = requestHeader.substring(begin, end);
//                        System.out.println("GET参数是：" + condition);
                    }
                    /**
                     * 获得POST参数
                     * 1.获取请求内容长度
                     */
                    if (requestHeader.startsWith("Content-Length")) {
                        int begin = requestHeader.indexOf("Content-Lengh:") + "Content-Length:".length();
                        String postParamterLength = requestHeader.substring(begin).trim();
                        contentLength = Integer.parseInt(postParamterLength);
//                        System.out.println("POST参数长度是：" + Integer.parseInt(postParamterLength));
                    }
                }
                StringBuffer sb = new StringBuffer();
                if (contentLength > 0) {
                    for (int i = 0; i < contentLength; i++) {
                        sb.append((char) bd.read());
                    }
//                    System.out.println("POST参数是：" + sb.toString());
                }
                /*发送回执*/
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                pw.println("HTTP/1.1 200 OK");
//                pw.println("Content-type:text/plain");
                pw.println("Content-type:application/json");


                pw.println();
//                pw.println("{\"name\":\"lzy\"}");

                pw.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
