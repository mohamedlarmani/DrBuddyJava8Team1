package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Doctor;

public class DoctorResponseDto extends PersonResponseDto{

    private String city;
    private String address;

    public DoctorResponseDto(String city, String address) {

        this.city = city;
        this.address = address;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

