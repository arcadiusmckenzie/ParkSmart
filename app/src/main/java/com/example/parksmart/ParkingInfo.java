package com.example.parksmart;

public class ParkingInfo {



        // string variable for
        // storing email.
        private String email;

        // string variable for storing
        //  car number
        private String carNumber;

        // string variable for storing
        // time
        private String timer;



        // an empty constructor is
        // required when using
        // Firebase Realtime Database.
        public ParkingInfo() {

        }

        // created getter and setter methods
        // for all our variables.
        public String getBookingEmail() {
            return email;
        }

        public void setBookingEmail(String email) {
            this.email = email;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }



    }

