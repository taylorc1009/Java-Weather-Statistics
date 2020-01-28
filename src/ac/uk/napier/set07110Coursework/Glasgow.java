package ac.uk.napier.set07110Coursework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Taylor Courtney
 * @author 40398643
 * Glasgow class:
 * 	Objects inherit the following variables and methods, storing the postcodes required from Glasgow
 */

public class Glasgow {
	private static ArrayList<Glasgow> gPostcodes;
	private String name;
	private double lat;
	private double lon;
	public Glasgow(String name, double lat, double lon) {
		super();
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}
	public String getName() {
		return name;
	}
	public double getLat() {
		return lat;
	}
	public double getLon() {
		return lon;
	}
	@Override
	public String toString() {
		return "Glasgow [name=" + name + ", lat=" + lat + ", lon=" + lon + "]";
	}
	public static ArrayList<Glasgow> getGPostcodes() {
		if(gPostcodes == null) {
			gPostcodes = new ArrayList<>();
			try {
				//Reads all the values in the g.csv file and stores them in tempReader
				BufferedReader tempReader = new BufferedReader(new FileReader("g.csv"));
				String line = "";
				//Loops as long as the next line is defined
				while((line = tempReader.readLine()) != null) {
					try {
						//Splits the values in line (the current line of the values read from the CSV) and stores them in the fields array
						String[] fields = line.split(",");
						//Stores the values in the current line in respective variables
						String name = fields[0];
						double lat = Double.parseDouble(fields[1]);
						double lon = Double.parseDouble(fields[2]);
						//Adds those values to the ArrayList of gPostcode objects
						Glasgow gPostcode = new Glasgow(name, lat, lon);
						gPostcodes.add(gPostcode);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}				
				tempReader.close();
			} catch(FileNotFoundException e ) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return gPostcodes;
	}
}