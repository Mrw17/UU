package com.algo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to retrieve temperature data from a weather station file.    
 */
public class WeatherDataHandler {
	ArrayList<WeatherDataPoint> weatherDataPoints = new ArrayList<>();

	/**
	 * Load weather data from file.
	 * 
	 * @param filePath path to file with weather data
	 * @throws IOException if there is a problem while reading the file
	 */
	public void loadData(String filePath) throws IOException {
		List<String> fileData = Files.readAllLines(Paths.get(filePath));
		convertFromListWithDataToListArray(fileData);
	}
	/**
	 * Search for average temperature for all dates between the two dates (inclusive).
	 * Result is sorted by date. When researching from 2000-01-01 to 2000-01-03 the 
	 * result might be:
	 * 2000-01-01 average temperature: 0.42 degrees Celcius
	 * 2000-01-02 average temperature: 2.26 degrees Celcius
	 * 2000-01-03 average temperature: 2.78 degrees Celcius
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return average temperature for each date, sorted by date  
	 */
	public List<String> avarageTemperatures(LocalDate dateFrom, LocalDate dateTo) throws Exception {

		int pointerFromDate = binarySearch(dateFrom.minusDays(1));
		int pointerToDate = binarySearch(dateTo);

		//Couldnt find dates in list.
		if (pointerFromDate == -1 || pointerToDate == -1) {
			System.out.println("Could not find date");
			throw new Exception("Could not find date: " + pointerFromDate + " " + pointerToDate);
		}

		System.out.println("Could not find date: " + pointerFromDate + " " + pointerToDate);

		ArrayList<DateAndTemp> averageTempEachDay = findAverageTempEachDay(pointerFromDate, pointerToDate);
		List<String> output = convertDateAndTimeListToStringList(averageTempEachDay);

		return output;
	}

	/**
	 * Helper class that converts an ArrayList with DateAndTemp objects to an ArrayList with only strings.
	 * @param averageTempEachDay Arraylist with all data to be transformed
	 * @return an ArrayList with average temp for each day
	 */
	private List<String> convertDateAndTimeListToStringList(ArrayList<DateAndTemp> averageTempEachDay) {
		List<String> output = new ArrayList<>();
		for (DateAndTemp data : averageTempEachDay)
			output.add(data.toString());

		return output;
	}

	private ArrayList<DateAndTemp> findAverageTempEachDay(int pointerFromDate, int pointerToDate) {
		ArrayList<DateAndTemp> averageTempEachDay = new ArrayList<>();
		int pointerInAvergeTempEachDay = 0;


		DateAndTemp currentDate = new DateAndTemp(weatherDataPoints.get(pointerFromDate).getDate(), 0);
		currentDate.addToTotalTempThatDay(weatherDataPoints.get(pointerFromDate).getTemp());
		averageTempEachDay.add(currentDate);
		pointerFromDate++;
		while (pointerFromDate < pointerToDate) {

			if (currentDate.getDate().compareTo(averageTempEachDay.get(pointerInAvergeTempEachDay).getDate()) == 0) {
				averageTempEachDay.get(pointerInAvergeTempEachDay).addToTotalTempThatDay(weatherDataPoints.get(pointerFromDate).getTemp());
			} else {
				averageTempEachDay.add(currentDate);
				pointerInAvergeTempEachDay++;
			}

			pointerFromDate++;
			currentDate = new DateAndTemp(weatherDataPoints.get(pointerFromDate).getDate(), 0);
			currentDate.addToTotalTempThatDay(weatherDataPoints.get(pointerFromDate).getTemp());
		}


		return averageTempEachDay;
	}

	public int binarySearch(LocalDate date) {
		DateTimeFormatter test2 = DateTimeFormatter.ofPattern("HH:mm");

		//LocalTime time = LocalTime.parse("00:00", test2);
		//System.out.println(time);
		//inputArr.get(11).getTime().compareTo(time);
		int start = 0;
		int end = weatherDataPoints.size() - 1;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (date.compareTo(weatherDataPoints.get(mid).getDate()) == 0) {

				//Will find any the reading that has the same day that we are looking for.
				//Will increase pointer untill we reach day + 1. Then we have found all reading values for searched day.
				while(date.compareTo(weatherDataPoints.get(mid).getDate()) == 0 && mid < weatherDataPoints.size() -1 ) {
					mid++;
					System.out.println(mid);
				}

				return mid;
			}

			if (date.compareTo(weatherDataPoints.get(mid).getDate()) < 0) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		//Special case if its first index
		if(date.plusDays(1).compareTo(weatherDataPoints.get(0).getDate()) == 0)
			return 0;
		else
			return -1;
	}


	/**
	 * Todo
	 * Search for missing values between the two dates (inclusive) assuming there 
	 * should be 24 measurement values for each day (once every hour). Result is
	 * sorted by date. When researching from 2000-01-01 to 2000-01-03 the 
	 * result might be:
	 * 2000-01-01 missing no measurements
	 * 2000-01-02 missing 1 measurements
	 * 2000-01-03 missing 1 measurements
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return dates with missing values together with number of missing values for each date, sorted by date
	 */
	public List<String> missingValues(LocalDate dateFrom, LocalDate dateTo) {
		int pointerFrom = binarySearch(dateFrom.minusDays(1));
		int pointerTo = binarySearch(dateFrom);

		while(pointerFrom < pointerTo){
			int missing = 0;

			//while(dataPoints.get(pointerFrom).getDate().compareTo())

		}
		return null;
	}
	/**
	 * Search for percentage of approved values between the two dates (inclusive).
	 * When researching from 2000-01-01 to 2000-01-03 the 
	 * result might be:
	 * Approved values between 2000-01-01 and 2000-01-03: 32.86 %
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return period and percentage of approved values for the period  
	 */
	public List<String> approvedValues(LocalDate dateFrom, LocalDate dateTo) {
		/**
		 * TODO: Implement method.		
		 */
		return null;
	}

	private void convertFromListWithDataToListArray(List<String> fileData){
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		DateTimeFormatter test = DateTimeFormatter.ofPattern(pattern);
		DateTimeFormatter test2 = DateTimeFormatter.ofPattern("HH:mm:ss");

		for (String str: fileData) {
			String [] words = str.split(";", 4);

			//Date date = simpleDateFormat.parse(words[0] + " " + words[1]);
			LocalDate date = LocalDate.parse(words[0], test);
			LocalTime time = LocalTime.parse(words[1],test2);


			WeatherDataPoint weatherDataPoint = new WeatherDataPoint(date, time, words[2], words[3]);
			weatherDataPoints.add(weatherDataPoint);

		}
		for(WeatherDataPoint d : weatherDataPoints){
			String output = d.getDate().toString() + " " + d.getTime();
			System.out.println(output);
		}
	}

}