package com.revature.models;

public class Donuts {
    private int donut_id;
    private String donut_name;
    private String coating;
    private String topping;
    private String filling;

    public Donuts(){

    };
    public Donuts(int donut_id, String donut_name, String coating, String topping, String filling) {
        this.donut_id = donut_id;
        this.donut_name = donut_name;
        this.coating = coating;
        this.topping = topping;
        this.filling = filling;
    }

    public int getDonut_id() {
        return donut_id;
    }

    public void setDonut_id(int donut_id) {
        this.donut_id = donut_id;
    }

    public String getDonut_name() {
        return donut_name;
    }

    public void setDonut_name(String donut_name) {
        this.donut_name = donut_name;
    }

    public String getCoating() {
        return coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    @Override
    public String toString() {
        return "Donuts{" +
                "donut_id=" + donut_id +
                ", donut_name='" + donut_name + '\'' +
                ", coating='" + coating + '\'' +
                ", topping='" + topping + '\'' +
                ", filling='" + filling + '\'' +
                '}';
    }
}
