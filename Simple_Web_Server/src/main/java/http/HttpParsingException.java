package http;

/*
Custom Behavior: By extending Exception, this class adds additional context to exceptions related to HTTP parsing by associating them with an HTTP status code.
Error Handling: It provides a way to handle errors in HTTP parsing with specific HTTP status codes and messages.

 */
public class HttpParsingException extends Exception{

    private final HttpStatusCode errorCode ;

    public HttpParsingException(HttpStatusCode errorCode) {
        super(errorCode.MESSAGE);// Call the parent class constructor with the error message
        this.errorCode = errorCode;
    }

    public HttpStatusCode getErrorCode() {
        return errorCode;
    }
}
