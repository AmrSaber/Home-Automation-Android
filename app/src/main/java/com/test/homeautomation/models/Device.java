package com.test.homeautomation.models;

public class Device {

    private String name;
    private Boolean state;
    private int id;

    public Device(String name ,Boolean state ,int id){
        this.name = name;
        this.state = state;
        this.id = id;
    }

    public String getName() { return name; }

    public Boolean getState() { return state; }

    public int getId() {  return id;}

}
