package goeuro.devtest.client;

import goeuro.devtest.config.Config;
import goeuro.devtest.config.DefaultConfig;
import goeuro.devtest.config.InvalidConfig;
import goeuro.devtest.core.GoEuroClient;
import goeuro.devtest.pojo.GoEuroObject;
import goeuro.devtest.reader.PropertiesReader;
import goeuro.devtest.writer.CSVGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the GoEuro Test application, using the GoEuro API containing
 * geolocalized information about the location specified. Input: location name.
 * Output: CSV file. Quote marks is not required to single location name, just
 * required if the location name contains spaces Command: java -jar
 * GoEuroTest.jar "location"
 * 
 * @author roanbrasil
 *
 */
@SpringBootApplication
public class GoEuroApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(GoEuroApplication.class);

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		log.info("*************** Beginning Processing Go Euro API ***************");

		// SpringApplication.run(GoEuroApplication.class, args);

		log.info("*************** Ending Processing Go Euro API ***************");
		
		String um = "https://docs.google.com/document/d/1yT4dY_d8zDbm8mC-vq0Md5jqCX6_aA4VSJonbkbSShw/edit?usp=sharing";
		String dois = "https://zookeeper.apache.org/doc/r3.1.2/zookeeperStarted.html";
		List<String> list = new ArrayList<>();
		list.add(um);
		list.add(dois);
		list.add(dois);
		list.add(dois);
		List<String> novaList = GoEuroApplication.printRepeatedElements(list);
		for(String s: novaList){
			System.out.println(s);
		}

	}

	public  static List<String> printRepeatedElements(List<String> inputFileElements) {
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < inputFileElements.size(); i++) {
			if (newList.isEmpty()) {
				newList.add(inputFileElements.get(i));
			} else {
				int count = 0;
				for (String s : newList) {
					if (inputFileElements.get(i).equals(s)) {
						count++;
					}
				}
				if (count == 0) {
					newList.add(inputFileElements.get(i));
				}
			}
		}
		return newList;
	}

	/**
	 * Override run method from CommandLineRunner to verify if there are just
	 * one argument required
	 * 
	 * 
	 * @param strings
	 * @throws IOException
	 * 
	 */
	@Override
	public void run(String... strings) throws IOException {
		Config config = buildConfiguration(strings);
		if (config.isValid()) {
			PropertiesReader r = new PropertiesReader("application.properties");
			HashMap<String, String> map = r.reader("csv");
			String csv = map.get("csv");
			List<GoEuroObject> goEuroObj = new ArrayList<GoEuroObject>();
			goEuroObj = new GoEuroClient(config).start();
			CSVGenerator.writeCSV(goEuroObj, csv);

		} else {
			config.getCommand();
		}
	}

	/**
	 * Build Configuration
	 * 
	 * @param args
	 * @return
	 */
	private static Config buildConfiguration(String[] args) {
		Config config;

		switch (args.length) {
		case 1:
			config = new DefaultConfig(args[0].replace("\"", ""));
			break;
		default:
			config = new InvalidConfig();
		}

		return config;
	}
}
