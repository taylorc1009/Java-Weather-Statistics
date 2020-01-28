package ac.uk.napier.set07110Coursework;

import java.util.HashMap;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import mapgui.MapGui;

/**
 * Taylor Courtney
 * @author 40398643
 * QUESTION 12
 * 
 * If you decide to answer question 12 then the main method below should be used as the entry point for your application
 * You may use as many other classes as you feel necessary as long as all code is supplied 
 * 
 * Remember to add -Xmx1024m to the VM arguments using menu run --> run configurations in eclipse
 */
public class Answer12 {
	public static void main(String[] args) {
		System.out.println("Question 12");
		//Defines a hashmap, inheriting the WeatherStation class, with the weather readings gathered in WeatherStationData
		HashMap<Integer, WeatherStation> weatherStations = WeatherStationData.getWeatherStations();
		int mostReadings = 0;
		int counter;				
		WeatherStation mostConsecutive = null;
		//Iterates tempStation as each weather station in the hashmap
		for(WeatherStation tempStation : weatherStations.values()) {
			counter = 0;
			//Iterates wr as the weather readings from the current station in tempStation
			for(WeatherReading wr : tempStation.getWeatherReadings()) {
				if(wr.getWindSpeed() > 50) {
					counter++;
					if(counter > mostReadings) {
						mostReadings = counter;
						mostConsecutive = tempStation;
					}
				} else {
					counter = 0;
				}
			}			
		}
		System.out.println(mostConsecutive.getName() + " has " + mostReadings + " wind speed readings over 50 MPH" );
		//Creates a new coordinate based on the longitude and latitude of the station with the most consecutive wind speed readings > 50 MPH
		Coordinate coordinate = new Coordinate(mostConsecutive.getLat(), mostConsecutive.getLon());
		MapGui.showMap(coordinate);
	}	
}