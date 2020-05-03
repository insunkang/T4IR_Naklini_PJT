package org.tensorflow.demo.nofish.item;

public class Calendar_data {
    private String year;
    private String month;
    private String day;
    private String event;
    private String key;


    public Calendar_data(){

    }

    public Calendar_data(String year, String month, String day, String event, String key) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.event = event;
        this.key = key;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
