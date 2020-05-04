package com.project.fishbegin.map.db;

public class Onboard {
    String name;
    String point_nm;
    String latitude;
    String longitude;

    public Onboard(){

    }
    public Onboard(String name, String point_nm, String latitude, String longitude) {
        this.name = name;
        this.point_nm = point_nm;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint_nm() {
        return point_nm;
    }

    public void setPoint_nm(String point_nm) {
        this.point_nm = point_nm;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Onboard{" +
                "name='" + name + '\'' +
                ", point_nm='" + point_nm + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
