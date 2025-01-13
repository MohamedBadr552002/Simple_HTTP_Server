package http;

public enum HttpMethod {
    GET, //Request Data from Server
    POST, // Submit data to the Server
    PUT, // Update Data Already on the Server
    DELETE, // Delete Data from Server
    HEAD;//
    public static final int MAXLENGTH ;

    static {
        System.out.println(HEAD.ordinal());
        int temp = -1;
        for( HttpMethod method :values()){
            if(method.name().length() > temp){
                temp = method.name().length();
            }
        }
        MAXLENGTH = temp ;
    }
}
