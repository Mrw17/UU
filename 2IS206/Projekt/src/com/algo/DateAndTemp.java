package com.algo;

import java.time.LocalDate;
import java.util.Date;

public class DateAndTemp {

    LocalDate date;
    int counter = 0;
    double averageTemp = 0;

    public DateAndTemp(LocalDate date, double averageTemp){
        this.date = date;
        this.averageTemp = averageTemp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAverageTemp() {
        return averageTemp/counter;
    }

    public void setAverageTemp(double averageTemp) {
        this.averageTemp = averageTemp;
    }

    public void addToTotalTempThatDay(Double temp){
        counter++;
        averageTemp += temp;
    }

    @Override
    public String toString(){
        String output = String.format(date + " average temperature: " + "%.2f" + " degrees Celcius", getAverageTemp());
        return output;
    }
}
