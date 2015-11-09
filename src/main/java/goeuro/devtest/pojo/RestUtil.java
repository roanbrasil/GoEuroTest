package goeuro.devtest.pojo;
import org.springframework.http.HttpStatus;

/**
 * Class responsable to verify if HttpStatus contains error on response
 * @author roanbrasil
 *
 */
public class RestUtil {
	/**
	 * verify if there is error on response after request
	 * @param status
	 * @return boolean
	 */
	public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
