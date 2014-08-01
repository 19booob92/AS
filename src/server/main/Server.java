/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.client.Client;
import serverConnection.utils.FilesOperations;
import serverConnection.utils.ProtocolProperties;
import serverConnection.utils.ServerProperties;

public class Server {

    private ServerSocket serverSocket;
    private int port;
    private int kaCounter = 0;
    private Thread clientCommunicationThread;
    private Set<Client> clientsList;
    private Client simpleClient;

    public Server() {
        this.clientsList = new LinkedHashSet<>();
        this.port = ServerProperties.PORT_NUMBER;
    }

    public void startServer(String ip) throws IOException {
        serverSocket = new ServerSocket(port, port, InetAddress.getByName(ip));

        System.out.println("Uruchamianie serwera na porcie: " + port + " i ip: " + serverSocket.getInetAddress());

        while (true) {
            try {
                simpleClient = new Client(serverSocket.accept(), clientsList);
                clientsList.add(simpleClient);
                Thread client = new Thread(simpleClient);
                client.start();

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
