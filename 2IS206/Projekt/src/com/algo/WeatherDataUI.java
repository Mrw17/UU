package com.algo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Command based UI for a simple Weather Data application.
 */
public class WeatherDataUI {
	private WeatherDataHandler weatherData = null;
	private Scanner scanner;
	/**
	 * Construct a Weather Data UI.
	 * 
	 * @param weatherData logic for weather data
	 */
	public WeatherDataUI(WeatherDataHandler weatherData) {
		this.weatherData = weatherData;
	}
	/**
	 * Start Weather Data UI.
	 */
	public void startUI() {
		scanner = new Scanner(System.in);
        int input;
        boolean quit = false;

        System.out.println("** Weather Data **");

        while (!quit) {
            input = getNumberInput(scanner, 1, 4, getMainMenu());

            switch (input) {
                case 1:
                	avarageTemperature();
                    break;
                case 2:
                	missingValues();
                    break;
                case 3:
                	approvedValues();
                    break;
                case 4:               	
                    quit = true;
            }
        }
        //Close scanner to free resources
        scanner.close();
	}
	/**
     * Get input and translate it to a valid number.
     *
     * @param scanner the Scanner we use to get input
     * @param min the lowest correct number
     * @param max the highest correct number
     * @param message message to user
     * @return the chosen menu number
     */
    private int getNumberInput(Scanner scanner, int min, int max, String message) {
        int input = -1;

        while (input < 0) {
            System.out.println(message);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException nfe) {
                input = Integer.MIN_VALUE;
            }
            if (input < min || input > max) {
                System.out.println("Invalid input. Enter a number between " + min + " and " + max);
            }
        }
        return input;
    }
    /**
     * Get date from user formatted as YYYY-MM-DD.
     * 
     * @return date provided by user
     */
    private LocalDate getDateInput() {
    	String input = null;
    	LocalDate date = null;
    	boolean isCorrect = false;

        while (!isCorrect) {
            System.out.println("Enter date (YYYY-MM-DD): ");
            input = scanner.nextLine().trim();
            
            try {
            	date = LocalDate.parse(input);
            	isCorrect = true;
            } catch (DateTimeParseException e) {
            	System.out.println("Invalid date");
            }            
        }
        return date;    	
    }
    /**
     * Query user for two dates and present the average temperature for all dates
     * in the period, sorted by date. 
     */
    private void avarageTemperature() {
        System.out.println("Calculate average temperature for dates");
    	System.out.print("Start date (will be included)\n");
        LocalDate dateFrom = getDateInput();
        System.out.print("End date (will be included)\n");
        LocalDate dateTo = getDateInput();

        try {
            presentResult(weatherData.avarageTemperatures(dateFrom, dateTo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Query user for two dates and present the dates within the period
     * where there are missing values, sorted by date. 
     */
    private void missingValues() {
        System.out.println("List dates with missing values between two dates");
    	System.out.print("Start date (will be included)\n");
    	LocalDate dateFrom = getDateInput();
        System.out.print("End date (will be included)\n");
        LocalDate dateTo = getDateInput();

        try {
            presentResult(weatherData.missingValues(dateFrom, dateTo));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }   
    /**
     * Query user for two dates and present the percentage of approved values 
     * between the two dates. 
     */
    private void approvedValues() {
        System.out.println("Calculate percentage of approved values between the two dates");
    	System.out.print("Start date (will be included)\n");
    	LocalDate dateFrom = getDateInput();
        System.out.print("End date (will be included)\n");
        LocalDate dateTo = getDateInput();

        try {
            presentResult(weatherData.approvedValues(dateFrom, dateTo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    /**
     * Present search result.
     * 
     * @param result the result to present
     */
    private void presentResult(List<String> result) {
    	if(result.size() == 0) {
    		System.out.println("No matching values for the provided query.");
    	}    	
    	for(String s : result) {
    		System.out.println(s);
    	}
    }
    /**
     * Return the main menu text.
     *
     * @return the main menu text
     */
    private String getMainMenu() {
        return "-------------------\n"
                + "1. Average temperatures\n"
                + "2. Missing values\n"
                + "3. Approved values\n"                
                + "-------------------\n"
                + "4. Quit";
    }
}