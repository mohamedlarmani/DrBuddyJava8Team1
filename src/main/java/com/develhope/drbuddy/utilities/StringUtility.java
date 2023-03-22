package com.develhope.drbuddy.utilities;

import java.util.Random;

public class StringUtility {

    public static String generateRandomString(Integer lenght){
        String abc= "abcdefghilmnoprstuvz";
        StringBuilder str= new StringBuilder();
        Random ran = new Random();
        for (int i=0; i<lenght; i++){
            str.append(abc.charAt(ran.nextInt(abc.length())));
        }
        return str.toString();
    }


}
