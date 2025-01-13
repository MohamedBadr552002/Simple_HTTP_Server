package Util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;


/*
    This Class will Used .....
 */
public class Json {

    private static ObjectMapper objectmapper =defultOM(); // ObjectMapper is a class from the Jackson library used to convert Java objects to JSON and vice versa

    /* */
    private static ObjectMapper defultOM(){
        ObjectMapper om = new ObjectMapper();
        /*
        * With this setting, if the JSON contains properties that don't have corresponding fields in the Java class,
        * Jackson will ignore those properties instead of throwing an exception.
        * */
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
        return om;
    }

    public static JsonNode parse(String jsonPath) throws IOException {
        return objectmapper.readTree(jsonPath);
    }

    // Return Generic type
    public static <T> T fromJson(JsonNode node , Class<T> obj) throws JsonProcessingException {

        return objectmapper.treeToValue(node , obj);
    }

    public static JsonNode toJson(Object obj){
        return  objectmapper.valueToTree(obj);
    }


    // this method used to Convert Json Node to String format
    public static String JsonNToString(JsonNode node , boolean pretty) throws JsonProcessingException {
        ObjectWriter objWriter = objectmapper.writer();
        if(pretty){
            objWriter = objWriter.with(SerializationFeature.INDENT_OUTPUT); // This makes the JSON output more human-readable.
        }
        return  objWriter.writeValueAsString(node);
    }


}
