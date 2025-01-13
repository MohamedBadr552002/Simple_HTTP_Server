package Core;

import http.*;
import io.ReadFileException;
import io.WebRootHandler;
import io.WebRootNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandlerThread extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHandlerThread.class);
    private Socket Client;

    private static int number =0;
    public ConnectionHandlerThread(Socket socket) {
        this.Client = socket;
        LOGGER.info("Thread number: "+number+" ,handles Client  : " + this.Client.getInetAddress());
    }

    @Override
    public void run() {
        InputStream inStream =null;
        OutputStream outStream =null;


        try {
            inStream = Client.getInputStream();
            outStream = Client.getOutputStream();



            /*todo*/
            // Handling the Requisites

//            /****************** First Trial *****************/
            HttpParser parser = new HttpParser();
            HttpRequest request = null;


            try {
                request = parser.parseHttpRequest(inStream);
//                System.out.println(request.getMethod());
//                System.out.println(request.getRequestTarget());
//                System.out.println(request.getHttpVersion());
//                System.out.println(request.getHeader());


            } catch (HttpParsingException e) {
                throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
            }

            WebRootHandler webRootHandler;
            try {
                webRootHandler = new WebRootHandler("src/webRoot/");
            } catch (WebRootNotExistException e) {
                throw new RuntimeException(e);
            }

            if(request.getMethod() == HttpMethod.GET){
                String html_ = webRootHandler.getFileintoStringFormat(request.getRequestTarget());

                final String CRLF = "\n\r";

                // HTTP Response ....
                String response = "HTTP/1.1 200 ok" + CRLF + "Content-Length: " + html_.getBytes() + CRLF + CRLF + html_ + CRLF + CRLF;

               outStream.write(response.getBytes());


            }

            /**********************************/

//
//           String PATH = "src/webRoot" + request.getRequestTarget() +".html";
//            System.out.println(PATH);
//
//            int i = 0;
//            StringBuffer SB = new StringBuffer();
//
//
//            FileReader webReader = new FileReader(PATH);
//            while ((i = webReader.read()) != -1) {
//                SB.append((char) i);
//            }
//
//            String html = SB.toString();
//
//            final String CRLF = "\n\r";
//
//            // HTTP Response ....
//            String response = "HTTP/1.1 200 ok" + CRLF + "Content-Length: " + html.getBytes() + CRLF + CRLF + html + CRLF + CRLF;
//
//
//            outStream.write(response.getBytes());


        } catch (
                IOException | HttpParsingException e) {
            LOGGER.info("Fail to Handle client Request :(");
        }finally {
            try {
                outStream.close();
                inStream.close();
                Client.close();
            } catch (IOException e) {}

        }

    }
}
