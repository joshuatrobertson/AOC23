package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Logic {

    public static Integer getGameNumber(String startingValue) {

        return Integer.valueOf(startingValue.split(":")[0].replaceAll("\\D+",""));
    }

    public static List<Integer> getValuesFromString(String values, Enum position) {
        List<Integer> valuesInList = new ArrayList<Integer>();
        if (position == Values.FIRST) {
            String removedString = values.replaceAll(".*?:", "").replaceAll("\\|.*", "");
            String[] numbersArrayString = removedString.split(" ");
            for (int i = 0; i < numbersArrayString.length; i++) {
                if (!numbersArrayString[i].isEmpty()) {
                    valuesInList.add(Integer.parseInt(numbersArrayString[i].trim()));
                }
            }
        } else  {
            String removedString = values.replaceAll(".*?\\|", "");
            String[] numbersArrayString = removedString.split(" ");
            for (int i = 0; i < numbersArrayString.length; i++) {
                if (!numbersArrayString[i].isEmpty()) {
                    valuesInList.add(Integer.parseInt(numbersArrayString[i].trim()));
                }
            }
        }
        return valuesInList;
    }

    public static Integer checkWinners(List<Integer> gameNumbers, final List<Integer> winningNumbers) {
        gameNumbers.retainAll(winningNumbers);
        System.out.println("Winning numbers are: " + gameNumbers.toString());

        return gameNumbers.size();
    }

    private static Integer getWinningTotal(Integer gamesWon) {
        Integer result = 0;
        if (gamesWon == 0){
            result = 0;
        }
        if (gamesWon == 1) {
            result = 1;
        }
        if(gamesWon > 1) {
            result = 2 * getWinningTotal(gamesWon - 1);
        }
        return result;
    }
}

