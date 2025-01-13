package Config;

import Util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/*
    This Class was implemented in Singleton design pattern to ensure that
    only one instance of a class is created
 */
public class ConfigurationManager {

    private static ConfigurationManager cfgmanager ;
    private static Configurator currentConfiguration; // instance to load the configuration

    private  ConfigurationManager(){
        /* this private constructor makes this class is accessible from any other class in the project. */
    }

    public static ConfigurationManager getInstance(){
            if(cfgmanager == null)
                cfgmanager = new ConfigurationManager();

            return  cfgmanager;
    }

    /*this method used for loading the configuration json file*/
    public void loadConfigutration(String filepath) {
        FileReader jsonReader;
        StringBuffer SB = new StringBuffer();
        int i = 0;

        try {
            jsonReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigException("Configuration Manager doesn't find the configuration file");
        }

        try {
            while ((i = jsonReader.read()) != -1) {
                SB.append((char) i);
            }
        } catch (IOException e) {
                throw new HttpConfigException(e);
        }
        JsonNode Jnode = null;
        try {
             Jnode =  Json.parse(SB.toString());
        } catch (IOException e) {
            throw new HttpConfigException("Can't Parse this file :(" ,e);
        }

        // Test the Node
//        try {
//            System.out.println(Json.JsonNToString(Jnode , true));
//        } catch (JsonProcessingException e) {
//            throw new HttpConfigException("Can't convert to String !!!!");
//        }


        try {
            currentConfiguration = Json.fromJson(Jnode , Configurator.class);

        } catch (JsonProcessingException e) {
            throw new HttpConfigException("Can't convert this Json node into Configurator class :(",e);
        }

//        System.out.println(currentConfiguration.getPort());
//        System.out.println(currentConfiguration.getWebRoot());




    }

    /*this method used for showing the Current Configuration */
    public Configurator getCurrentConfiguration(){

        if(currentConfiguration == null){

            throw  new HttpConfigException("No Current Configuration Right now !!");
        }
        return currentConfiguration;
    }

}
