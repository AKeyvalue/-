package org.example.CSHomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 创建ServerSocket对象，绑定端口号
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务器启动，等待客户端连接...");

            while (true) {
                // 监听客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端已连接: " + clientSocket.getInetAddress());

                // 创建输入流和输出流
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                // 读取客户端发送的数据
                String data = input.readLine();

                if (data.equals("hello")) {
                    // 发送响应数据给客户端
                    output.println("hello");
                } else {
                    output.println("打个招呼呵");
                }

                // 关闭连接
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}