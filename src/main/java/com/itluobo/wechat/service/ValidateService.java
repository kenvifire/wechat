package com.itluobo.wechat.service;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

/**
 * Created by hannahzhang on 15/6/2.
 */
public class ValidateService {
    public static void main(String [] args) throws IOException{
        ServerSocket socket = new ServerSocket();

        socket.bind(new InetSocketAddress(80));

        Socket client = socket.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String line;

        while ((line = br.readLine()) != null){
           System.out.println(line);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        bw.write("200\n");
        bw.write("\n");
        bw.write("\n");

    }
}
