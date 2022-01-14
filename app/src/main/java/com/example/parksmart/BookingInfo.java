package com.example.parksmart;

public class BookingInfo {


    private String email;


    private String carNumber;


    private String timer;

    public BookingInfo() {

    }


    // created getter and setter methods
    // for all our variables.
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarNumber() {
        return carNumber;
    }
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTime() {
        return timer;
    }

    public void setTime(String timer) {
        this.timer = timer;
    }
}
