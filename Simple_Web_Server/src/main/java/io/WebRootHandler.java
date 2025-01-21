package io;

import java.io.*;
import java.net.URLConnection;

public class WebRootHandler {

    private final File webroot;

    public WebRootHandler(String webrootPath) throws WebRootNotExistException {
        webroot = new File(webrootPath);
        if(!webroot.exists() || !webroot.isDirectory()){
            throw new WebRootNotExistException("This Web root not exist <_> ");
        }
    }

    private boolean IfEndWithSlash(String relativePath){
        return relativePath.endsWith("/");
    }

    /*
    This method checks whether a given relative path corresponds to a valid and accessible file or directory within the web root
     */
    private boolean CheckIfProvidedRelativePathExist(String relativePath){
        File file = new File(webroot , relativePath);

        if(!file.exists()){
            System.out.println(" -- > from CheckIfProvidedRelativePathExist "+ file.getName() +" Not Exist");
            return false;
        }
        try {
            if(file.getCanonicalPath().startsWith(webroot.getCanonicalPath())){
                System.out.println(" -- > from CheckIfProvidedRelativePathExist "+ file.getName() +" Exist");
                return true;
            }
        } catch (IOException e) {
            System.out.println(" -- > from CheckIfProvidedRelativePathExist "+ file.getName() +" Exist But Can't Opened !! ");
            return false;
        }
        return false;
    }

    public String getFileMimeType(String relativePath) throws FileNotFoundException {
        /*
        A MIME type is a standard way of indicating the nature and format of a file or data being transferred over the internet.
         */
        if(IfEndWithSlash(relativePath)){
            relativePath += "web.html";  // By default, serve this file
        }

        if (!relativePath.contains(".")) {
            relativePath += ".html";
        }

        if(!CheckIfProvidedRelativePathExist(relativePath)){
            throw new FileNotFoundException("This file "+ relativePath+" Not Exist");
        }

        File file = new File(webroot, relativePath);

       String mimeType = URLConnection.getFileNameMap().getContentTypeFor(file.getName());

        // Fallback for common file types to ensure the right mimeType
        if (mimeType == null) {
            if (relativePath.endsWith(".css")) {
                mimeType = "text/css";
            } else if (relativePath.endsWith(".js")) {
                mimeType = "application/javascript";
            } else if (relativePath.endsWith(".html")) {
                mimeType = "text/html";
            } else if (relativePath.endsWith(".png")) {
                mimeType = "image/png";
            } else if (relativePath.endsWith(".jpg") || relativePath.endsWith(".jpeg")) {
                mimeType = "image/jpeg";
            } else if (relativePath.endsWith(".gif")) {
                mimeType = "image/gif";
            } else {
                mimeType = "application/octet-stream"; // Default fallback
            }
        }
        return mimeType;

    }

    public byte[] getFileByteArrayData(String relativePath) throws FileNotFoundException, ReadFileException {

        System.out.println("From Web root Handler the relativePath is "+ relativePath);
        if(IfEndWithSlash(relativePath)){
            relativePath += "web.html";  // By default serve this file
        }

        if (!relativePath.contains(".")) {
            relativePath += ".html";
        }


        if(!CheckIfProvidedRelativePathExist(relativePath)){
            throw new FileNotFoundException("This file "+ relativePath+" Not Exist");
        }


        File file = new File(webroot, relativePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] bytes = new byte[(int)file.length()];
        try {
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Problem in reading the file ..!!...");
            throw new ReadFileException(e);
        }

        return bytes;

    }

    public String getFileintoStringFormat(String relativePath) throws IOException {
        System.out.println("From Web root Handler the relativePath is "+ relativePath);
        if(IfEndWithSlash(relativePath)){
            relativePath += "web.html";  // By default serve this file
        }

        if(!relativePath.endsWith(".html") && !relativePath.equals("/favicon.ico")){ // /favicon.ico is file Requested Automatically by the Browsers to Visualize an icon next to the site Header
            relativePath += ".html";
        }


        if(!CheckIfProvidedRelativePathExist(relativePath)){
            throw new FileNotFoundException("This file "+ relativePath+" Not Exist");
        }
//        System.out.println(webroot.getAbsolutePath());
             String PATH = "src/webRoot"+relativePath ;
        System.out.println(PATH);



        int i = 0;
        StringBuffer SB = new StringBuffer();
        FileReader webReader = new FileReader(PATH);


        while ((i = webReader.read()) != -1) {
            SB.append((char) i);
        }

        String html = SB.toString();

        return html;
    }
}
