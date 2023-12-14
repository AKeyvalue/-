package org.example.CSHomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建Socket对象，指定服务器地址和端口号
            Socket socket = new Socket("localhost", 8888);

            // 创建输入流和输出流
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // 从控制台读取要发送的数据
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("请输入要发送的消息：");
            String message = consoleInput.readLine();

            // 发送数据给服务器
            output.println(message);

            // 接收服务器响应
            String response = input.readLine();
            System.out.println("服务器响应: " + response);

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}