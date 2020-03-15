package com.algo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Class that represent an data point that contains information
 * about a weather measure.
 * @author Daniel
 * @since 2020-03-14
 */
public class WeatherDataPoint {
    LocalDate date;
    LocalTime time;
    double temp = 0;
    boolean quality = false;

    /**
     * Constructor that creates an WeatherDataPoint() object with specified data
     * @param date date when measure was done
     * @param time time when measure was done
     * @param temp temp when measure was done
     * @param approvedValue if the measure is approved or is suspected to be aggregated or is to be suspect to be false in any way, g = approved.
     */
    public WeatherDataPoint(LocalDate date, LocalTime time, String temp, String approvedValue) {
        this.date = date;
        this.time = time;
        this.temp = Double.parseDouble(temp);
        if(approvedValue.equals("G"))
            this.quality = true;
    }

    public LocalTime getTime() {return time;}

    public void setTime(LocalTime time) {this.time = time;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public Double getTemp() {return temp;}

    public void setTemp(Double temp) {this.temp = temp;}

    public boolean getQuality() {return quality;}

    public void setQuality(boolean quality) {this.quality = quality;}
}
