package com.algo;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateAndMissingMeasure {
    LocalDate date;
    LocalTime time;
    int missingMeasure = 0;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getMissingMeasure() {
        return missingMeasure;
    }

    public void setMissingMeasure(int missingMeasure) {
        this.missingMeasure = missingMeasure;
    }

}
