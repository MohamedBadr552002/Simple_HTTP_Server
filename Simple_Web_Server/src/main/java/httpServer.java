
/*
* Driver Class for http Server
*
* */


import Config.ConfigurationManager;
import Config.Configurator;
import Core.ServerListenerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class httpServer {



    public static void main(String[] args){
        System.out.println("Turn on the Server .....");

        ConfigurationManager.getInstance().loadConfigutration("src/main/resources/http.json");
        Configurator Cconf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Server port: " + Cconf.getPort());
        System.out.println("Server WebRoot: " + Cconf.getWebRoot());

        try {
            ServerListenerThread listenerThread = new ServerListenerThread(Cconf.getPort(),Cconf.getWebRoot());
            listenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Turn off the Server .....");


    }



    private static void startStream(Configurator Cconf){
        try {

            ServerSocket ssocket = new ServerSocket(Cconf.getPort());
            Socket socket = ssocket.accept();

            // Read and Send Data from / to Client
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private static void endStream( ServerSocket ssocket, Socket socket ,  InputStream inStream , OutputStream outStream){

        try {
            inStream.close();
            outStream.close();
            socket.close();
            ssocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Turn off the Server .....");

    }




}
