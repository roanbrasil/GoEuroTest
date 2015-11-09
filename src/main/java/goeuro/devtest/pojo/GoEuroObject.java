package goeuro.devtest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pojo GoEuro Json Object to request all data
 * 
 * @author roanbrasil
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoEuroObject {

	private Long id;
	private String name;
	private String type;
	private GeoPosition geoPosition;

	/**
	 * @return the id
	 */
	@JsonProperty("_id")
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the geoPosition
	 */
	@JsonProperty("geo_position")
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	/**
	 * @param geoPosition
	 *            the geoPosition to set
	 */
	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id->"+id
				+" , name-> "+name
				+" , type-> "+type
				+" , GeoPosition-> "+geoPosition;
	}
}
