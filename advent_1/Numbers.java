package com.company;

import java.util.HashMap;

public class Numbers {


    public static String getValueFromString(String startingValue) {

        return startingValue.replaceAll("\\D+","");
    }

}
