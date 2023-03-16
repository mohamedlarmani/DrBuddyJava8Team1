package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Secretary;

public class SecretaryResponseDto {

    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private String email;

    public SecretaryResponseDto(String firstname, String lastname, String telephoneNumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    public static SecretaryResponseDto Secretary(Secretary secretary) {
        return new SecretaryResponseDto(
                secretary.getFirstname(),
                secretary.getLastname(),
                secretary.getTelephoneNumber(),
                secretary.getEmail());
    }
}
