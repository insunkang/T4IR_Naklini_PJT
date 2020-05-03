package org.tensorflow.demo.nofish.item;

public class Detail_data {
    String title;
    String start_date;
    String finish_date;

    public Detail_data(){

    }
    public Detail_data(String title, String start_date, String finish_date) {
        this.title = title;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    @Override
    public String toString() {
        return "Detail_data{" +
                "title='" + title + '\'' +
                ", start_date='" + start_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                '}';
    }
}
