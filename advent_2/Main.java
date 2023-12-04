package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        int totalNumber = 0;
        int secondTotalNumber = 0;
        String filePath = "/Users/josh/IdeaProjects/advent_2/src/com/company/codeText.txt";

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText;

            int gameNumber = 0;
            HashMap<String, Integer> colourMap = new HashMap<String, Integer>();
            HashMap<String, Integer> minimumMap = new HashMap<String, Integer>();
            String pattern = "(\\d+)\\s+(\\w+)";
            Pattern regex = Pattern.compile(pattern);

            while ((lineText = lineReader.readLine()) != null) {

                // initialise HashMap
                colourMap.put("red", 0);
                colourMap.put("blue", 0);
                colourMap.put("green", 0);

                // find number
                gameNumber = Logic.getGameNumber(lineText);

                // remove game number
                String colourInput = Logic.getAllColours(lineText);

                // split by number, word
                Matcher matcher = regex.matcher(colourInput);
                while (matcher.find()) {

                    int number = Integer.parseInt(matcher.group(1));
                    String colour = matcher.group(2).toLowerCase(); // Convert to lowercase for case-insensitive matching
                    if (colourMap.get(colour) < number) {
                        colourMap.put(colour, number);
                    }
                }
                int cubePower = colourMap.get("red") * colourMap.get("blue") * colourMap.get("green");
                System.out.println("Game: " + gameNumber + " Power: " + cubePower);
                secondTotalNumber += cubePower;

                if ((colourMap.get("red") <= 12) && (colourMap.get("green") <= 13) && (colourMap.get("blue") <= 14)){
                    totalNumber += gameNumber;
                    System.out.println("Game number = " + gameNumber);
                    System.out.println("Red: " + colourMap.get("red") + " :: Blue: " + colourMap.get("blue") + " :: Green: " + colourMap.get("green"));
                    System.out.println("Running Total = " + totalNumber);
                }

            }

            System.out.println("Total for game = " + totalNumber);
            System.out.println("Total for game power = " + secondTotalNumber);
            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


}
