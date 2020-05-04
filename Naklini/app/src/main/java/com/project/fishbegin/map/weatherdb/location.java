package com.project.fishbegin.map.weatherdb;

public class location {
    String si;
    String gu;
    String dong;
    String x;
    String y;

    public location(){

    }

    public location(String si, String gu, String dong, String x, String y) {
        this.si = si;
        this.gu = gu;
        this.dong = dong;
        this.x = x;
        this.y = y;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "location{" +
                "si='" + si + '\'' +
                ", gu='" + gu + '\'' +
                ", dong='" + dong + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
