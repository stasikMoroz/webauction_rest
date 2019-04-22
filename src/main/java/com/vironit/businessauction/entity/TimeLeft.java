package com.vironit.businessauction.entity;

public class TimeLeft {
    long seconds;
    long minutes;
    long hours;
    long days;

    public TimeLeft(long millis) {
        this.seconds = millis / 1000;
        this.minutes = seconds / 60;
        this.hours = minutes / 60;
        this.days = hours / 24;
    }

    @Override
    public String toString() {
         return days + " day(s)" + ":" + hours % 24 + " hour(s)" + ":" + minutes % 60 + " minute(s)" + ":" + seconds % 60 + " second(s)";
    }
}
