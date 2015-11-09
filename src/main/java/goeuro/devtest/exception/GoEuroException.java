package goeuro.devtest.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author roanbrasil
 *
 */
public class GoEuroException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1042294767094208669L;

	private HttpStatus statusCode;

    private String body;

    public GoEuroException(String msg) {
        super(msg);
    }

    public GoEuroException(HttpStatus statusCode, String body, String msg) {
        super(msg);
        this.statusCode = statusCode;
        this.body = body;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
