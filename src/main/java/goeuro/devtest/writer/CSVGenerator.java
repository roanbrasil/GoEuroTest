package goeuro.devtest.writer;

import goeuro.devtest.pojo.GoEuroObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Class responsable about write a CSV file of list from GoEuro API
 * 
 * @author roanbrasil
 *
 */
public class CSVGenerator {

	private static final Logger log = LoggerFactory
			.getLogger(CSVGenerator.class);

	public static final char SEPARATOR = ',';

	/**
	 * 
	 * @param goEuroObjList
	 * @return List<String[]>
	 */
	private static List<String[]> toStringList(List<GoEuroObject> goEuroObjList) {
		List<String[]> data = new ArrayList<String[]>();
		// add header record
		data.add(new String[] { "Id", "Name", "Type", "Latitude", "Longitude" });
		if (goEuroObjList!=null) {
			Iterator<GoEuroObject> it = goEuroObjList.iterator();
			while (it.hasNext()) {
				GoEuroObject goEuroObj = it.next();
				data.add(new String[] { goEuroObj.getId().toString(),
						goEuroObj.getName(), goEuroObj.getType(),
						goEuroObj.getGeoPosition().getLatitude(),
						goEuroObj.getGeoPosition().getLongitude() });
			}
			return data;
		}
		return null;
	}

	/**
	 * 
	 * @param goEuroObj
	 * @throws IOException
	 */
	public static void writeCSV(List<GoEuroObject> goEuroObj, String csv)
			throws IOException {

		FileWriter fileWriter = new FileWriter(csv);
		CSVWriter csvWriter = new CSVWriter(fileWriter, SEPARATOR);

		List<String[]> data = toStringList(goEuroObj);

		if(data!=null)
		csvWriter.writeAll(data);
		csvWriter.close();
		log.info("File Writer: [ {} ] Csv File: [ {} ]", fileWriter, csv);
	}
}
