package ac.uk.napier.set07110Coursework;

import java.util.HashMap;
import weather.WeatherData;

/**
 * Taylor Courtney
 * @author 40398643
 * Gathers the data of each weather station including their weather readings
 */

public class WeatherStationData {
	private static HashMap<Integer, WeatherStation> weatherStations = null;
	public static HashMap<Integer, WeatherStation> getWeatherStations() {
		if(weatherStations == null) {
			weatherStations = new HashMap<>();
			//Gathers all the weather stations data and stores it into the data array to be sorted
			String[] data = WeatherData.getData();
			//Loops for each weather station
			for(int i = 1; i < data.length; i++){
				String line = data[i];
				//Splits the values in line (the current line of the values gathered) and stores them in the fields array
				String[] fields = line.split(",");
				//Stores the values in the current line in respective variables
				int id = Integer.parseInt(fields[0]);
				String name = fields[1];
				double lat = Double.parseDouble(fields[2]);
				double lon = Double.parseDouble(fields[3]);
				int year = Integer.parseInt(fields[4]);
				int month = Integer.parseInt(fields[5]);
				int date = Integer.parseInt(fields[6]);
				int hour = Integer.parseInt(fields[7]);
				int windSpeed = Integer.parseInt(fields[8]);
				double temperature = Double.parseDouble(fields[9]);
				WeatherStation ws = null;
				//If the current station is not already stored in the hashmap of WeatherStation objects, store it
				if(!weatherStations.containsKey(id)) {
					ws = new WeatherStation(id, name, lat, lon);
					weatherStations.put(id, ws);
				} else {
					ws = weatherStations.get(id);
				}
				//Adds the values of weather readings
				WeatherReading wr = new WeatherReading(ws, year, month, date, hour, windSpeed, temperature);
				ws.getWeatherReadings().add(wr);
			}
		}
		return weatherStations;
	}
}