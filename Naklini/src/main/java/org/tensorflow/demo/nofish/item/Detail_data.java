package org.tensorflow.demo.nofish.item;
public class Detail_data {
    String title;
    String start_month;
    String start_day;
    String finish_month;
    String finish_day;

    public Detail_data(){

    }

    public Detail_data(String title, String start_month, String start_day, String finish_month, String finish_day) {
        this.title = title;
        this.start_month = start_month;
        this.start_day = start_day;
        this.finish_month = finish_month;
        this.finish_day = finish_day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_month() {
        return start_month;
    }

    public void setStart_month(String start_month) {
        this.start_month = start_month;
    }

    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getFinish_month() {
        return finish_month;
    }

    public void setFinish_month(String finish_month) {
        this.finish_month = finish_month;
    }

    public String getFinish_day() {
        return finish_day;
    }

    public void setFinish_day(String finish_day) {
        this.finish_day = finish_day;
    }
}
