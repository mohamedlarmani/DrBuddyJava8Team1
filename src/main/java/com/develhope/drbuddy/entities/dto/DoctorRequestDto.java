package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Secretary;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DoctorRequestDto extends PersonRequestDto{

    @NotNull
    @Size(max = 60)
    private String city;

    @NotNull
    @Size(max = 100)
    private String address;

    private Secretary secretary;

    public DoctorRequestDto() {}

    public DoctorRequestDto( String city, String address, Secretary secretary) {
        this.city = city;
        this.address = address;
        this.secretary = secretary;
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

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }
}
