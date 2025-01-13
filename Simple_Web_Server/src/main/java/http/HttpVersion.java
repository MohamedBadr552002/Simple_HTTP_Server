package http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1",1,1);

    public final String LITERAL ;
    public final int MAJOR ;
    public final int MINOR ;

    private static final Pattern httpVersionRegexPattern =  Pattern.compile("^HTTP/(?<major>\\d+).(?<minor>\\d+)");
    HttpVersion(String LITERAL, int MAJOR, int MINOR) {
        this.LITERAL = LITERAL;
        this.MAJOR = MAJOR;
        this.MINOR = MINOR;
    }


    public static HttpVersion getBetCompatibleVersion(String LiteralVersion) throws HttpParsingException {

        Matcher matcher = httpVersionRegexPattern.matcher(LiteralVersion);
        if(!matcher.find() || matcher.groupCount() !=2){
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }
        int major = Integer.parseInt(matcher.group("major"));
        int minor = Integer.parseInt(matcher.group("minor"));

        HttpVersion tempBestVersion = null;

        for(HttpVersion version : HttpVersion.values()){
            if(version.LITERAL.equals(LiteralVersion)){
                return version;
            }else {
                if(version.MAJOR == major){
                    if(version.MINOR < minor)
                        tempBestVersion = version;
                }
            }
        }
        return tempBestVersion ;
    }

}
