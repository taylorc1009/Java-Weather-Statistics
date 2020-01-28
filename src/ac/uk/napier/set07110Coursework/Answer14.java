package ac.uk.napier.set07110Coursework;

import mapGui.MapGui;
import weather.WeatherData;
import java.util.ArrayList;
import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * Taylor Courtney
 * @author 40398643
 * QUESTION 14
 * 
 * If you decide to answer question 14 then the main method below should be used as the entry point for your application
 * You may use as many other classes as you feel necessary as long as all code is supplied 
 * 
 * Remember to add -Xmx1024m to the VM arguments using menu run --> run configurations in eclipse
 */
public class Answer14 {
	public static void main(String[] args) {
		System.out.println("Question 14");
		//Defines an ArrayList, iterating the Glasgow class, with the Glasgow postcodes
		ArrayList<Glasgow> postcodes = Glasgow.getGPostcodes();
		//Sets up an array list of type double to store the distance between every postcode and their closest neighbour
		ArrayList<Double> closestNeighbour = new ArrayList<>();
		//Iterates pc as the values in the ArrayList postcodes
		for(Glasgow pc : postcodes) {
			//Initialises the distanceToClosest variable with a large number as were doing an if is < comparison
			double distanceToClosest = 99999999;
			//Iterates pc2 as the values in the ArrayList postcodes, nested since we need to compare the position of each postcode to every other postcode to get the closest neighbour
			for(Glasgow pc2 : postcodes) {
				//Also makes sure we don't compare a postcode with itself since that would definitely be recorded as the closest
				if(WeatherData.getDistanceBetweenPoints(pc.getLat(), pc.getLon(), pc2.getLat(), pc2.getLon()) < distanceToClosest && pc != pc2) {
					distanceToClosest = WeatherData.getDistanceBetweenPoints(pc.getLat(), pc.getLon(), pc2.getLat(), pc2.getLon());
				}
			}
			//Stores the closest neighbour to the current postcode in the closestNeighbour ArrayList
			closestNeighbour.add(distanceToClosest);
		}
		double mostIsolatedDistance = 0;
		Glasgow mostIsolated = null;
		int index = 0;
		//Iterates pc as the values in the ArrayList postcodes
		for(Glasgow pc : postcodes) {
			//Calculates what the largest distance is (between each postcode and their closest neighbour) to get the most isolated
			if(closestNeighbour.get(index) > mostIsolatedDistance) {
				mostIsolatedDistance = closestNeighbour.get(index);
				mostIsolated = pc;
			}
			index++;
		}
		//Creates a new coordinate based on the longitude and latitude of the most isolated postcode
		Coordinate coord = new Coordinate(mostIsolated.getLat(), mostIsolated.getLon());
		MapGui.showMap(coord);
		System.out.println("The most isolated postcode is " + mostIsolated.getName() + " at " + mostIsolated.getLat() + ", " + mostIsolated.getLon());
	}
}