package goeuro.devtest.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties File reader
 * 
 * @author roanbrasil
 *
 */
public class PropertiesReader {

	private static final Logger log = LoggerFactory
			.getLogger(PropertiesReader.class);

	private String fileName;

	public PropertiesReader(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * reader one or a list of fields from properties file
	 * 
	 * @param fields
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> reader(String... fields) {
		Properties prop = new Properties();
		InputStream input = null;

		HashMap<String, String> map = new HashMap<String, String>();

		try {

			input = new FileInputStream(fileName);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			for (String field : fields) {
				map.put(field, prop.getProperty(field));
				log.info("Property Name: [ {} ] Property Value: [ {} ]", field,
						prop.getProperty(field) + "]");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
}
