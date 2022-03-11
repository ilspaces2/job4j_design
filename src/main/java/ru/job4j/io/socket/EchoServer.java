package ru.job4j.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.startsWith("GET")) {
                            String parameter = str.split(" ")[1];
                            if ("/?msg=Exit".equals(parameter)) {
                                server.close();
                                break;
                            } else if ("/?msg=Hello".equals(parameter)) {
                                out.write("Hello".getBytes());
                            } else {
                                out.write("What".getBytes());
                            }
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}

