package ac.uk.napier.set07110Coursework;

import java.util.ArrayList;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import mapGui.MapGui;
import weather.WeatherData;

/**
 * Taylor Courtney
 * @author 40398643
 * QUESTION 15
 * 
 * If you decide to answer question 15 then the main method below should be used as the entry point for your application
 * You may use as many other classes as you feel necessary as long as all code is supplied 
 * 
 * Remember to add -Xmx1024m to the VM arguments using menu run --> run configurations in eclipse
 */
public class Answer15 {
	public static void main(String[] args) {
		System.out.println("Question 15");
		//Defines an ArrayList, iterating the Glasgow class, with the Glasgow postcodes
		ArrayList<Glasgow> postcodes = Glasgow.getGPostcodes();
		int amountOfNeighbours = 0;
		Glasgow mostPopulated = null;
		//Iterates pc as the values in the postcodes ArrayList
		for(Glasgow pc : postcodes) {
			int counter = 0;
			//Iterates pc2 as the values in the postcodes ArrayList, nested since we need to compare the position of each postcode to every other postcode to find out which of them are <= 0.3 KM away
			for(Glasgow pc2 : postcodes) {
				//Also makes sure we don't compare a postcode with itself since that would definitely be within 0.3 KM
				if(WeatherData.getDistanceBetweenPoints(pc.getLat(), pc.getLon(), pc2.getLat(), pc2.getLon()) <= 0.3 && pc != pc2)
					counter++;
			}
			//If counter (amount of postcodes within 0.3 KM around the current one) > the amount of the previous neighbours then updates the stored postcode with the most
			if(counter > amountOfNeighbours) {
				amountOfNeighbours = counter;
				mostPopulated = pc;
			}
		}
		//Creates a new coordinate based on the longitude and latitude of the most populated postcode
		Coordinate coord = new Coordinate(mostPopulated.getLat(), mostPopulated.getLon());
		MapGui.showMap(coord);
		System.out.println("The most densly populated postcode is " + mostPopulated.getName() + " with " + amountOfNeighbours + " neighbouring postcodes");
	}	
}