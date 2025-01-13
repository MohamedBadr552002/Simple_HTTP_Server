package Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    /*
     * This code sets up a basic TCP server socket that listens for a connection on a specified port,
     * accepts an incoming connection, retrieves input and output streams
     * */
    final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    private int port;
    private String webRoot;
    private ServerSocket ssocket;

    public ServerListenerThread(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
       // this.ssocket = new ServerSocket(this.port);// ServerSocket is a class in Java that allows a server to wait for requests to come in over a network.
        this.ssocket = new ServerSocket(this.port, 50, InetAddress.getByName("0.0.0.0"));  // Making the Server Seen by other external devices over the network or the internet
    }

    @Override
    public void run() {
        try {
                while (ssocket.isBound() && (!ssocket.isClosed())) {
                    Socket socket = ssocket.accept();//The accept() method is called on the ServerSocket to block and wait until a client attempts to connect.
                    ConnectionHandlerThread handlerThread = new ConnectionHandlerThread(socket);
                    handlerThread.start();
                }



        } catch (IOException e) {
               LOGGER.info("Error in Client Request !! ");
        }finally {
            if(ssocket != null){
                try {
                    ssocket.close();
                } catch (IOException e) {}
            }
        }


    }
}
