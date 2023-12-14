package org.example.CSHomeWorkPlus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080); // 监听端口号为8080
            System.out.println("服务器已启动，等待客户端连接...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 接受客户端连接
                System.out.println("客户端连接成功：" + clientSocket);

                // 创建一个新线程处理客户端连接
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // 接收客户端的请求
                String request = reader.readLine();
                System.out.println("接收到客户端的请求：" + request);

                // 处理客户端请求
                String response;
                if (request.equals("hello")) {
                    response = "hello";
                } else {
                    response = "打个招呼呵";
                }
                writer.println(response);

                // 关闭连接
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}