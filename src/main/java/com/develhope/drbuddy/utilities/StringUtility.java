package com.develhope.drbuddy.utilities;

import com.develhope.drbuddy.entities.Doctor;

import java.util.Random;

public class StringUtility {

    /**
     * Generates a random string of specified length.
     * @param length the length of the random string to generate
     * @return a random string of the specified length
     */
    public static String generateRandomString(Integer length) {
        String abc = "abcdefghilmnoprstuvz";
        StringBuilder str = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < length; i++) {
            str.append(abc.charAt(ran.nextInt(abc.length())));
        }
        return str.toString();
    }

}
