package ac.uk.napier.set07110Coursework;

import weather.WeatherData;
import java.util.ArrayList;
import java.util.HashMap;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import mapgui.MapGui;

/**
 * Taylor Courtney
 * @author 40398643
 * QUESTION 11
 * 
 * If you decide to answer question 11 then the main method below should be used as the entry point for your application
 * You may use as many other classes as you feel necessary as long as all code is supplied 
 * 
 * Remember to add -Xmx1024m to the VM arguments using menu run --> run configurations in eclipse
 */
public class Answer11 {
	public static void main(String[] args)  {
		System.out.println("Question 11");
		//Defines a hashmap, inheriting the WeatherStation class, with the weather readings gathered in WeatherStationData
		HashMap<Integer, WeatherStation> weatherStations = WeatherStationData.getWeatherStations();
		//ArrayList used to store the coordinates of the postcodes to be displayed on the map
		ArrayList<Coordinate> coordinates = new ArrayList<>();
        int counter = 0;
        //Since we're calculating the distance of postcodes from the Aonach Mor weather station, we need to get it by key store it as an object before hand
        WeatherStation aonachMor = weatherStations.get(3041);
        //Iterates pc as each individual postcode for every loop
        for(Postcode pc : Postcode.getPostcodes()) {
        	//Calculates the distance between the current postcode and Aonach Mor via their latitudes and longitudes
    		double distance = WeatherData.getDistanceBetweenPoints(pc.getLat(), pc.getLon(), aonachMor.getLat(), aonachMor.getLon());
    		if(distance <= 10) {
    			counter++;
    			//Places the postcodes to be displayed in the ArrayList by storing them as coordinates
    			Coordinate coord = new Coordinate(pc.getLat(), pc.getLon());
    			coordinates.add(coord);
    		}
        }
        MapGui.showMap(coordinates);
        System.out.println("There are " + counter + " postcodes within the radius of Aonach Mor");
	}
}