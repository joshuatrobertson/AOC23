package com.company;

public class Logic {

    public static Integer getGameNumber(String startingValue) {
        return Integer.valueOf(startingValue.split(":")[0].replaceAll("\\D+",""));
    }

    public static String getAllColours(String startingValue) {
        return startingValue.substring(startingValue.indexOf(":") + 2);
    }
}
