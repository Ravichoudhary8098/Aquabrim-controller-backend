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
    private static final String CONN_HOST = "localhost";
    private static final int CONN_PORT = 7070;

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
}
