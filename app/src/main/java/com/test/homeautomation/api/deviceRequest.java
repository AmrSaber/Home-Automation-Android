package com.test.homeautomation.api;

public class deviceRequest {

    private int id;
    private boolean state;
    private int pin;
    private String name;

    public deviceRequest() {
        this.id = id;
        this.state = state;
        this.pin = pin;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }
}
