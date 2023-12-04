package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static ArrayList<ArrayList<Character>> list = new ArrayList<>();

    public static void main(String[] args) {

        int totalNumber = 0;
        String filePath = "/Users/josh/IdeaProjects/advent_3/src/com/company/codeTextTest.txt";


        getTextFile(filePath);

        StringBuilder number = new StringBuilder();

        // loop through array
        for (int i = 0; i < list.size()-1; i++) {
            boolean hasSpecialChar = false;

            for (int y = 0; y < list.get(i).size()-1; y++) {
                // find next character in row
                Character c = list.get(i).get(y);
                // check if it's a number
               if (Character.isDigit(c)) {
                   // add to the total number
                   number.append(Character.getNumericValue(c));

                   // check whether it has a special character already for previous char by passing through co-ords
                   if (!hasSpecialChar) {
                       hasSpecialChar=isSymbol(i, y);
                   }
                   else {
                       System.out.println("Special Char Found!");
                   }
               }
               // if not a number, then check whether we have a number, and if so and there's a match add to the total
               else {
                   // check number is not empty and there is a symbol
                   if (!number.isEmpty() && hasSpecialChar) {
                       totalNumber+=Integer.parseInt(number.toString().trim());
                   }
                   System.out.println("Current Number is: " + number);
                   // clear number
                   number = new StringBuilder();
               }
            }
        }
    }

    private static void getTextFile(String filePath) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;

            while ((lineText = lineReader.readLine()) != null) {
                ArrayList<Character> lineList = new ArrayList<>();
                for (char c : lineText.toCharArray()) {
                    lineList.add(c);
                }
                list.add(lineList);
            }

            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private static Boolean isSymbol(int firstArray, int secondArray) {
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                int firstPosition = Math.max(0, firstArray + i);
                int secondPosition = Math.max(0, secondArray + y);
                    Character c = list.get(firstPosition).get(secondPosition);
                    if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c) && c!='.') {
                            return true;
                        }
                    }
                }
        return false;
    }
}







