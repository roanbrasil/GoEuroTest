package goeuro.devtest.core;

import goeuro.devtest.config.Config;
import goeuro.devtest.exception.GoEuroErrorHandler;
import goeuro.devtest.exception.GoEuroException;
import goeuro.devtest.pojo.GoEuroObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Http GoEuro Client request API GoEuro
 * 
 * @author roanbrasil
 *
 */
public class GoEuroClient {

	private static final Logger log = LoggerFactory
			.getLogger(GoEuroClient.class);

	private final Config config;

	/**
	 * Constructor
	 * 
	 * @param config
	 */
	public GoEuroClient(Config config) {
		this.config = config;
	}

	/**
	 * Method responsable about to request the URI and parse the results
	 * 
	 * @throws UnknownHostException
	 */
	public List<GoEuroObject> start() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new GoEuroErrorHandler());
		// set the content type to use json
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		// response using exchange and bring the result type to GoEuroObject
		// List on Body Content
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					"http://api.goeuro.com/api/v2/position/suggest/en/"
							+ config.getCommand(), HttpMethod.GET, request,
					new ParameterizedTypeReference<String>() {
					});
			log.info("Http Status Code: {} ", responseEntity.getStatusCode());
			
			// verifiy if return is Https Status 200 - OK and put in the list
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.getTypeFactory();
				
				//map responseEntity.getBody() Json String inGoEuroObject List
				List<GoEuroObject> goEuroList = mapper.readValue(
						responseEntity.getBody(),
						TypeFactory.defaultInstance().constructCollectionType(
								List.class, GoEuroObject.class));
				log.info("GoEuroList Converted: {}", goEuroList.toString());
				return goEuroList;
			}
		} catch (GoEuroException e) {
			log.error("Error Code: {} ", e.getStatusCode());
		}
		return null;
	}
}
