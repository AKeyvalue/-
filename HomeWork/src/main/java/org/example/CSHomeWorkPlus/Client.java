package org.example.CSHomeWorkPlus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080); // 连接服务器，指定服务器的主机名和端口号
            System.out.println("与服务器连接成功：" + socket);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // 发送消息给服务器
            writer.println("hello");

            // 接收服务器的响应
            String response = reader.readLine();
            System.out.println("服务器的响应：" + response);

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}