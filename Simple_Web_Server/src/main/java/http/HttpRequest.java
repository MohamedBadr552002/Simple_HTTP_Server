package http;

import java.util.HashMap;

public class HttpRequest extends HttpMessage{

    private HttpMethod method;
    private String requestTarget;
    private HttpVersion httpVersion;
    private String originalHttpVesion ;

    private HashMap<String,String> header = new HashMap<>();


     HttpRequest() {

    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

     void setMethod(String methodName) throws HttpParsingException {

         for(HttpMethod method :HttpMethod.values())
         {
             if(methodName.equals(method.name())){
                 this.method = method;
                 return;
             }
         }
         throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED); // throw exeption when Method not found
    }

     void setTarget(String Target) throws HttpParsingException {
        if(Target == null || Target.length() <=0) {
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = Target;
     }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public String getOriginalHttpVesion() {
        return originalHttpVesion;
    }

    public void setHttpVersion(String ShttpVersion) throws HttpParsingException {
         this.originalHttpVesion = ShttpVersion;
        this.httpVersion = HttpVersion.getBetCompatibleVersion(ShttpVersion);
        if(httpVersion == null)
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);
    }

    public void addHeader(String fieldName, String fieldValue) {
            header.put(fieldName,fieldValue);
    }
}
