package com.algo;

/**
 * Simple application for retrieving and presenting temperature 
 * data from a weather station file.
 */
public class WeatherDataMain {

	/**
	 * Program entry point.
	 * 
	 * @param args optional argument for weather data file
	 */
	public static void main(String[] args) {
		WeatherDataHandler weatherData = new WeatherDataHandler();
		String fileName = "smhi-opendata.csv";
		if(args.length > 0) {
			fileName = args[0];
		}
		
		try {				
			weatherData.loadData(fileName);	
			new WeatherDataUI(weatherData).startUI();
		} catch (Exception e) {
			System.out.println("Problem while reading weather data: " + e.getMessage());
			System.out.println("Closing program ...");
		}		
	}
}