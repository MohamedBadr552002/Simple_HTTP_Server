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

/**********
                String html_ = webRootHandler.getFileintoStringFormat(request.getRequestTarget());
                byte[] fileData = html_.getBytes();


                String mimeType = webRootHandler.getFileMimeType(request.getRequestTarget());

                final String CRLF = "\r\n";
                String responseHeaders = "HTTP/1.1 200 OK" + CRLF +
                        "Content-Type: " + mimeType + CRLF +
                        "Content-Length: " + fileData.length + CRLF + CRLF;

               outStream.write(responseHeaders.getBytes());
               outStream.write(fileData);
 *****/


                byte[] fileData = webRootHandler.getFileByteArrayData(request.getRequestTarget());

                String mimeType = webRootHandler.getFileMimeType(request.getRequestTarget());
                final String CRLF = "\r\n";
                String responseHeaders = "HTTP/1.1 200 OK" + CRLF +
                        "Content-Type: " + mimeType + CRLF +
                        "Content-Length: " + fileData.length + CRLF + CRLF;
                outStream.write(responseHeaders.getBytes());
                outStream.write(fileData);


            }



        } catch (
                IOException | HttpParsingException | ReadFileException e) {
            LOGGER.info("Fail to Handle client Request :(");
        } finally {
            try {
                outStream.close();
                inStream.close();
                Client.close();
            } catch (IOException e) {}

        }

    }
}
