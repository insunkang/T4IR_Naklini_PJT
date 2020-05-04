package com.project.fishbegin.pointdetail;

public class Fish_Detail_InfoList {
    int num; //번호
    String fish_name; //국명
    String distribution; //분포
    String habitat; //서식지
    String img;

    public Fish_Detail_InfoList(){

    }

    public Fish_Detail_InfoList(int num, String fish_name, String distribution, String habitat, String img) {
        this.num = num;
        this.fish_name = fish_name;
        this.distribution = distribution;
        this.habitat = habitat;
        this.img = img;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFish_name() {
        return fish_name;
    }

    public void setFish_name(String fish_name) {
        this.fish_name = fish_name;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}


