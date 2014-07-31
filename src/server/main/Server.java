/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverConnection.utils.FilesOperations;
import serverConnection.utils.ProtocolProperties;
import serverConnection.utils.ServerProperties;

public class Server {

    private ServerSocket serverSocket;
    private int port;
    private int kaCounter = 0;
    private DataInputStream stdIn;
    private DataOutputStream stdOut;
    private byte[] messageData;
    private Thread clientCommunicationThread;

    public Server() {
        this.port = ServerProperties.TIME_PERIOD;
    }

    public void startServer(String ip) throws IOException {
        serverSocket = new ServerSocket(port, port, InetAddress.getByName(ip));

        System.out.println("Uruchamianie serwera na porcie: " + port + " i ip: " + serverSocket.getInetAddress());

        clientCommunicationThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Socket client = null;
                    client = serverSocket.accept();
                    stdIn = new DataInputStream(client.getInputStream());
                    stdOut = new DataOutputStream(client.getOutputStream());

                    while (true) {
                        System.out.println("Oczekiwanie na klientów...");
                        getIdentifyMessage();
                        getKeepAlive();
                        setScheduler();
                    }
                } catch (SocketException ex) {
                    System.err.println("Nie można utworzyć socketu");
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        clientCommunicationThread.run();
    }

    private void setScheduler() {
        Timer scheduler = new Timer();
        scheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    sendOnePLNCoinsCountRequest();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, ServerProperties.TIME_PERIOD, ServerProperties.TIME_PERIOD);
    }

    private void getIdentifyMessage() throws IOException {

        messageData = new byte[50];
        stdIn.read(messageData);

        switch (messageData[ProtocolProperties.SECOND_MSG_BYTE]) {
            case (byte) 0x01: {
                FilesOperations.saveDataToFile(System.currentTimeMillis() + "   Grupa: Identyfikacja myjni, numer protokołu  " + messageData[4] + messageData[5] + messageData[6] + messageData[7] + "\n");
            }
            break;
            case (byte) 0x02: {

            }
            break;
            case (byte) 0x03: {

            }
            break;
            case (byte) 0x04: {

            }
            break;
            default: {

            }
        }
    }

    private void getKeepAlive() throws IOException {
        messageData = new byte[ProtocolProperties.FIVE_BYTES_MESSAGE];
        stdIn.read(messageData);

        byte tmp = messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1];
        messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1] = (byte) (tmp + 1);

        FilesOperations.saveDataToFile(System.currentTimeMillis()
                + "   Grupa: POTWIERDZENIE_I_STEROWANIE  KEEP_ALIVE "
                + messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1]
                + "\n");

        sendMessage(messageData);

    }

    public void sendOnePLNCoinsCountRequest() throws IOException {
        messageData = new byte[ProtocolProperties.SIX_BYTES_MESSAGE];
        sendMessage(new byte[]{ProtocolProperties.NULL, (byte) 0x02, ProtocolProperties.NULL, (byte) 0x01, ProtocolProperties.NULL, ProtocolProperties.NULL});

        int count = stdIn.read(messageData);
        FilesOperations.saveDataToFile(System.currentTimeMillis() + "   Grupa: cykliczne dane finansowe, ilość monet 1 zł  " + messageData[5] + "\n");
    }

    private void sendMessage(byte[] message) throws IOException {
        stdOut.write(message);
        stdOut.flush();
    }

}
