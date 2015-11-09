package goeuro.devtest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * GeoPosition Pojo class 
 * @author roanbrasil
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoPosition {

	private String latitude;
	private String longitude;

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitute
	 *            the longitute to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Latitude: " + latitude + " , Longitude: " + longitude;
	}
}
