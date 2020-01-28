package ac.uk.napier.set07110Coursework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * Taylor Courtney
 * @author 40398643
 * Postcode class:
 * 	Objects inherit the following variables and methods, storing the postcodes required
 */

public class Postcode extends Coordinate{
	private static ArrayList<Postcode> postcodes; 
	private String name;
	
	public Postcode(String name, double lat, double lon) {
		super(lat, lon);
		this.name = name;
		
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Postcode [name=" + name + ", latitude=" + getLat() + ", longitude=" + getLon() + "]";
	}
	public static ArrayList<Postcode> getPostcodes() {
		if(postcodes == null) {
			postcodes = new ArrayList<>();
			try {
				//Reads all the values in the postcodes.csv file and stores them in tempReader
				BufferedReader tempReader = new BufferedReader(new FileReader("postcodes.csv"));
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
						//Adds those values to the ArrayList of Postcode objects
						Postcode postcode = new Postcode(name, lat, lon);
						postcodes.add(postcode);
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
		return postcodes;
	}
}