/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.main;

import java.io.IOException;

/**
 *
 * @author Mateusz Olczak
 */
public class ServerStarter {

    public static void main(String[] args) {
        Server socketServer = null;
        try {
            socketServer = new Server();
            socketServer.startServer(args[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
