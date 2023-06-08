package com.revature.models;

public class Customer {

    private int order_number;

    private String first_name;

    private String last_name;

    private int order_size;

    private int donut_id_fk;

    private Donuts donut;

    public Customer (){

    };

    public Customer(int order_number, String first_name, String last_name, int order_size, Donuts donut) {
        this.order_number= order_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.order_size = order_size;
        this.donut = donut;
    }
    public Customer(int order_number, String first_name, String last_name, int order_size, int donut_id_fk) {
        this.order_number = order_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.order_size = order_size;
        this.donut_id_fk = donut_id_fk;
    }

//    public Customer(String first_name, String last_name, int order_size, int donut_id_fk) {
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.order_size = order_size;
//        this.donut_id_fk = donut_id_fk;
//    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getOrder_size() {
        return order_size;
    }

    public void setOrder_size(int order_size) {
        this.order_size = order_size;
    }

    public int getDonut_id_fk() {
        return donut_id_fk;
    }

    public void setDonut_id_fk(int donut_id_fk) {
        this.donut_id_fk = donut_id_fk;
    }

    public Donuts getDonut() {
        return donut;
    }

    public void setDonut(Donuts donut) {
        this.donut = donut;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "order_number=" + order_number +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", order_size=" + order_size +
//                ", donut_id_fk=" + donut_id_fk +
                ", donut=" + donut +
                '}';
    }
}

