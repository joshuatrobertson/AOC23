package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {



    public static void main(String[] args) {


        int totalNumber = 0;

        String filePath = "/Users/josh/IdeaProjects/advent_1/src/com/company/codeText.txt";
        HashMap<Integer, String> stringToNumbers = new HashMap<Integer, String>();

        stringToNumbers.put(1, "one");
        stringToNumbers.put(2, "two");
        stringToNumbers.put(3, "three");
        stringToNumbers.put(4, "four");
        stringToNumbers.put(5, "five");
        stringToNumbers.put(6, "six");
        stringToNumbers.put(7, "seven");
        stringToNumbers.put(8, "eight");
        stringToNumbers.put(9, "nine");

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;

            while ((lineText = lineReader.readLine()) != null) {



                // extract string numbers
                List<Integer> numericValues = extractNumericValues(lineText);
                System.out.println("Extracted numeric values: " + numericValues);

                int firstNumber = numericValues.get(0);
                int secondNumber = numericValues.get(numericValues.size() - 1);
                int finalNumber = (firstNumber * 10) + secondNumber;

                totalNumber += finalNumber;

            }
            System.out.println("Total = " + totalNumber);
            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private static List<Integer> extractNumericValues(String input) {
        ArrayList<Integer> numericValues = new ArrayList<Integer>();
        System.out.println("Input = " + input);
        // Define a regular expression pattern to match written numbers
        String regex = "(?=(one|two|three|four|five|six|seven|eight|nine|\\d))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        // Find and convert written numbers to numeric values
        while (m.find()) {
            String matchedWord = m.group(1);
            int numericValue = convertWrittenNumberToNumeric(matchedWord);
            numericValues.add(numericValue);
        }

        return numericValues;
    }

    private static int convertWrittenNumberToNumeric(String writtenNumber) {
        String capitalizedWord = writtenNumber.toLowerCase(); // Capitalize the first letter
        if ("one".equals(capitalizedWord)) {
            return 1;
        } else if ("two".equals(capitalizedWord)) {
            return 2;
        } else if ("three".equals(capitalizedWord)) {
            return 3;
        } else if ("four".equals(capitalizedWord)) {
            return 4;
        } else if ("five".equals(capitalizedWord)) {
            return 5;
        } else if ("six".equals(capitalizedWord)) {
            return 6;
        } else if ("seven".equals(capitalizedWord)) {
            return 7;
        } else if ("eight".equals(capitalizedWord)) {
            return 8;
        } else if ("nine".equals(capitalizedWord)) {
            return 9;
            // Add more cases as needed
        }
        return Integer.parseInt(writtenNumber);  // Default to 0 for unknown words
    }

}
