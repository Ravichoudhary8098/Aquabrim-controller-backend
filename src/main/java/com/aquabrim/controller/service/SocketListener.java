package com.aquabrim.controller.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SocketListener {
    private static final String CONN_HOST = "aquabrim-controller-backend.vercel.app";
    private static final int CONN_PORT = 443;

    @Autowired
    private SocketHandler socketHandler;

    @PostConstruct
    public void init() {
        // Start listening for incoming connections
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(CONN_PORT, 0, InetAddress.getByName(CONN_HOST))) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    socketHandler.handleRequest(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

//    private void handleRequest(Socket clientSocket) {
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//
//            // Read data from the client
//            StringBuilder requestData = new StringBuilder();
//            String line;
//            while ((line = in.readLine()) != null) {
//                requestData.append(line);
//            }
//
//            // Process the received data
//            String response = myService.processData(requestData.toString());
//
//            // Send response back to the client if needed
//            out.println(response);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// }
//}
}
