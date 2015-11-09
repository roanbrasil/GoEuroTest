package goeuro.devtest.test;

import goeuro.devtest.config.Config;
import goeuro.devtest.config.DefaultConfig;
import goeuro.devtest.core.GoEuroClient;
import goeuro.devtest.pojo.GoEuroObject;
import goeuro.devtest.reader.PropertiesReader;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Unit Test GoEuroClient class
 * 
 * @author roanbrasil
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context.xml" })
public class GoEuroClientTest {

	@Test
	public void testAddReturnCorrectResponse() throws Exception {

		PropertiesReader r = new PropertiesReader("application.properties");
		HashMap<String, String> map = r
				.reader("testAddReturnCorrectResponseString");

		Config config = new DefaultConfig("Berlin".replace("\"", ""));
		GoEuroClient goEuroClient = new GoEuroClient(config);
		List<GoEuroObject> goEuroObject = goEuroClient.start();

		String returnedStringObject = map
				.get("testAddReturnCorrectResponseString");

		Assert.hasText(returnedStringObject);
		System.out.println(goEuroObject.toString());
	}

	@Test
	public void testAddReturn400BadRequestResponse() throws Exception {
		Config config = new DefaultConfig("".replace("\"", ""));
		GoEuroClient goEuroClient = new GoEuroClient(config);
		List<GoEuroObject> goEuroObject = goEuroClient.start();
		Assert.isNull(goEuroObject);
	}
}
