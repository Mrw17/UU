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
		int pointerFromDate = modifiedBinarySearch(dateFrom.minusDays(1));
		int pointerToDate = modifiedBinarySearch(dateTo);

		//Couldnt find dates in list.
		if (pointerFromDate == -1 || pointerToDate == -1) {
			System.out.println("Could not find date");
			throw new Exception("Could not find date: " + pointerFromDate + " " + pointerToDate);
		}

		return findAverageTempEachDay(pointerFromDate,pointerToDate);
	}

	/**
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
	public List<String> missingValues(LocalDate dateFrom, LocalDate dateTo) throws Exception {
		int pointerFrom = modifiedBinarySearch(dateFrom.minusDays(1));
		int pointerTo = modifiedBinarySearch(dateTo);

		//Could not find dates in list.
		if (pointerFrom == -1 || pointerTo == -1) {
			System.out.println("Could not find date");
			throw new Exception("Could not find date: " + pointerFrom + " " + pointerTo);
		}

		return calcMissingValues(pointerFrom, pointerTo);
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
	public List<String> approvedValues(LocalDate dateFrom, LocalDate dateTo) throws Exception {
		int pointerFrom = modifiedBinarySearch(dateFrom.minusDays(1));
		int pointerTo = modifiedBinarySearch(dateTo);
		StringBuilder str = new StringBuilder();

		//Could not find dates in list.
		if (pointerFrom == -1 || pointerTo == -1) {
			System.out.println("Could not find date");
			throw new Exception("Could not find date: " + pointerFrom + " " + pointerTo);
		}

		double approvedValueProcent = calcApprovedValues(pointerFrom,pointerTo);

		//Fix output string
		str.append("Approved values between ");
		str.append(dateFrom);
		str.append(" and ");
		str.append(dateTo);
		str.append(": ");
		String approvedValueProcentFixDecimal = String.format("%.2f", approvedValueProcent);
		str.append(approvedValueProcentFixDecimal);
		str.append("%");

		List<String> approvedValus = new ArrayList<>();
		approvedValus.add(str.toString());

		return approvedValus;
	}

	/**
	 * Calculates how many measures that was approved in procent
	 * @param pointerFrom start point
	 * @param pointerTo end point
	 * @return how many measures that was approved in procent
	 */
	private double calcApprovedValues(int pointerFrom, int pointerTo) {
		int sumOfApproved = 0;
		int numberOfMeasures = 0;

		//Count how many measure points between from and to-pointers.
		//Count how many measure that was accepted between from and to-pointers.
		while(pointerFrom < pointerTo){
			boolean currDate = weatherDataPoints.get(pointerFrom).getQuality();
			numberOfMeasures++;
			if(currDate)
				sumOfApproved++;
			pointerFrom++;
		}

		return  ((double) sumOfApproved / (double) numberOfMeasures) * 100;
	}

	/**
	 * Modifed BinarySearch that returns location of the last occur of the date that it is looking for
	 * @param date that should be looked for.
	 * @return pointer to the date i the list.
	 */
	public int modifiedBinarySearch(LocalDate date) {
		DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm");
		int start = 0;
		int end = weatherDataPoints.size() - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			//found date
			if (date.compareTo(weatherDataPoints.get(mid).getDate()) == 0) {

				//Will find any the reading that has the same day that we are looking for.
				//Will increase pointer untill we reach day + 1. Then we have found all reading values for searched day.
				while(date.compareTo(weatherDataPoints.get(mid).getDate()) == 0 && mid < weatherDataPoints.size() -1 ) {
					mid++;
				}
				return mid;
			}

			//Decide if it should keep looking to left or right in list.
			if (date.compareTo(weatherDataPoints.get(mid).getDate()) < 0) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		//Special case if its first index
		if(date.plusDays(1).compareTo(weatherDataPoints.get(0).getDate()) == 0)
			return 0;
		//didnt find element
		else
			return -1;
	}

	/**
	 * Calculate missing values from given pointers
	 * @param pointerFrom start pointer
	 * @param pointerTo end pointer
	 * @return an list with missing values
	 */
	private List<String> calcMissingValues(int pointerFrom, int pointerTo) {
		List<String> missingValues = new ArrayList<>();

		while(pointerFrom < pointerTo){
			LocalDate currDate = weatherDataPoints.get(pointerFrom).getDate();
			int counter = 23;
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(currDate.toString()).append(" missing: ");

			//Compere current date with next day in the list
			//If it is same, it will decrease counter and get points from current date.
			while(currDate.compareTo(weatherDataPoints.get(pointerFrom+1).getDate()) == 0){
				counter--;
				pointerFrom++;
				currDate = weatherDataPoints.get(pointerFrom).getDate();

				//Prevent overflow
				if(pointerFrom == weatherDataPoints.size()-1)
					break;
			}

			pointerFrom++;

			strBuilder.append(counter).append(" measurements");
			missingValues.add(strBuilder.toString());
		}
		return missingValues;
	}

	/**
	 * Find average temp for each day between to given points
	 * @param pointerFromDate start point
	 * @param pointerToDate end points
	 * @return average temp for each day
	 */
	private ArrayList<String> findAverageTempEachDay(int pointerFromDate, int pointerToDate) {
		ArrayList<String> averageTempEachDay = new ArrayList<>();

		//Goes through all points
		while(pointerFromDate < pointerToDate){
			WeatherDataPoint currDate = weatherDataPoints.get(pointerFromDate);
			double totSumOfTemp = 0;
			int numOfMesures = 0;

			//Creates string for each day
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(currDate.getDate()).append(" average temperature: ");

			//Comperes current day with next day, if it is the same it saves data.
			while(currDate.getDate().compareTo(weatherDataPoints.get(pointerFromDate+1).getDate()) == 0){
				numOfMesures++;
				totSumOfTemp += currDate.getTemp();
				pointerFromDate++;
				currDate = weatherDataPoints.get(pointerFromDate);

				//Prevent overflow
				if(pointerFromDate == weatherDataPoints.size() - 1)
					break;
			}

			//Add data from last point of that day
			numOfMesures++;
			totSumOfTemp += currDate.getTemp();
			pointerFromDate++;

			double averageTemp = totSumOfTemp / numOfMesures;

			String averageTempstr = String.format("%.2f", averageTemp);

			strBuilder.append(averageTempstr).append(" degrees Celcius");
			averageTempEachDay.add(strBuilder.toString());
		}

		return averageTempEachDay;
	}

	/**
	 * Converts an List<String> to datapoints and add it to weatherDatapoints
	 * @param fileData data to be converted
	 */
	private void convertFromListWithDataToListArray(List<String> fileData){
		String dayPattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dayPattern);
		DateTimeFormatter test = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter test2 = DateTimeFormatter.ofPattern("HH:mm:ss");

		for (String str: fileData) {
			String [] words = str.split(";", 4);

			LocalDate date = LocalDate.parse(words[0], test);
			LocalTime time = LocalTime.parse(words[1],test2);

			WeatherDataPoint weatherDataPoint = new WeatherDataPoint(date, time, words[2], words[3]);
			weatherDataPoints.add(weatherDataPoint);
		}
	}
}