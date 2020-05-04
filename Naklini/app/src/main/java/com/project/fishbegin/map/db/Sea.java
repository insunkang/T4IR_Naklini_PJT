package com.project.fishbegin.map.db;

public class Sea {
    String name;
    String address;
    String fish;

    public Sea(){

    }

    public Sea(String name, String address, String fish) {
        this.name = name;
        this.address = address;
        this.fish = fish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFish() {
        return fish;
    }

    public void setFish(String fish) {
        this.fish = fish;
    }

    @Override
    public String toString() {
        return "Sea{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", fish='" + fish + '\'' +
                '}';
    }
}
