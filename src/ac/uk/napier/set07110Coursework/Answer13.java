package ac.uk.napier.set07110Coursework;

import java.util.ArrayList;
import java.util.HashMap;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import mapGui.MapGui;

/**
 * Taylor Courtney
 * @author 40398643
 * QUESTION 13
 * 
 * If you decide to answer question 13 then the main method below should be used as the entry point for your application
 * You may use as many other classes as you feel necessary as long as all code is supplied 
 * 
 * Remember to add -Xmx1024m to the VM arguments using menu run --> run configurations in eclipse
 */
public class Answer13 {
	public static void main(String[] args) {
		System.out.println("Question 13");
		//Defines a hashmap, inheriting the WeatherStation class, with the weather readings gathered in WeatherStationData
		HashMap<Integer, WeatherStation> weatherStations = WeatherStationData.getWeatherStations();
		//Sets up a hashmap, inheriting the WeatherStation class, to store the 2 stations who's readings we're comparing
		HashMap<Integer, WeatherStation> stations = new HashMap<>();
		//ArrayList used to store the coordinates of the postcodes to be displayed on the map
		ArrayList<Coordinate> coordinates = new ArrayList<>();
		//Stores the 2 stations for comparison in the hashmap stations
		stations.put(0, weatherStations.get(3318));
		stations.put(1, weatherStations.get(3166));
		int index = 0;
		double[] meanTemp = new double[2];
		//Iterates ws as the values in the stations hashmap
		for(WeatherStation ws : stations.values()) {
			double sum = 0;
			int counter = 0;
			//Iterates wr as the current stations readings
			for(WeatherReading wr : ws.getWeatherReadings()) {
				if(wr.getMonth() == 1) {
					sum = sum + wr.getTemperature();
					counter++;
				}
			}
			//Calculates the mean temperature for both the stations
			meanTemp[index] = sum/counter;
			index++;
			//Places the stations to be displayed in the ArrayList by storing them as coordinates
			Coordinate coord = new Coordinate(ws.getLat(), ws.getLon());
			coordinates.add(coord);
		}
		//Swaps the mean temperatures stored in the array if temp 1 > temp 2 so when calculating the difference between them it wont be negative
		if(meanTemp[1] > meanTemp[0]) {
			double temp = meanTemp[0];
			meanTemp[0] = meanTemp[1];
			meanTemp[1] = temp;
		}
		MapGui.showMap(coordinates);
		System.out.println("The difference in mean temperature between " + weatherStations.get(3318).getName() + " and " + weatherStations.get(3166).getName() + " during January is " + (meanTemp[0] - meanTemp[1]) + "°C");
	}	
}