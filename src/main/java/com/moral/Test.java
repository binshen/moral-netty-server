package com.moral;

import com.moral.util.NetUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5678);
            System.out.println("启动服务器....");
            Socket s = ss.accept();
            System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端："+ Arrays.toString(mess.getBytes()));

//            System.out.println(mess);
//            String strUTF8 = URLDecoder.decode(mess, "UTF-8");
//            System.out.println(strUTF8);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write(mess+"\n");
            bw.flush();
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
