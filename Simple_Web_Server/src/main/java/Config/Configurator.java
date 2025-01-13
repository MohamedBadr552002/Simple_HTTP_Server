package Config;

public class Configurator {

    /*NOTE: this class members Must be the same names of Json field to be mapped in Right Way*/
    private int port;
    private String webRoot;


    public int getPort() {
        return port;
    }

    public String getWebRoot() {
        return this.webRoot;
    }

    public void setPort(int port) {
        this.port = port;
       // System.out.println("port has been set ");
    }

    public void setWebRoot(String webRoot) {
        //System.out.println("port has been set -- "+ webRoot);
        this.webRoot = webRoot;
    }
}
