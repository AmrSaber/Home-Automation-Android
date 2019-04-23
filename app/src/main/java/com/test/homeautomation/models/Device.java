package com.test.homeautomation.models;

@SuppressWarnings("WeakerAccess")
public class Device {

    public String name;
    public int state;
    public int id;
    public int pin;

    public Device(){}

    public Device(String name ,int state ,int id){
        this.name = name;
        this.state = state;
        this.id = id;
    }

    public String getName() { return name; }

    public int getState() { return state; }

    public int getId() {  return id;}

}
