package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static String input = "/Users/josh/IdeaProjects/advent_6/src/com/company/input.txt";
    static ArrayList<Long> totalTimes = new ArrayList<Long>();
    static ArrayList<Long> totalDistances = new ArrayList<Long>();
    static ArrayList<Long> totalTimesTogether = new ArrayList<Long>();

    public static void main(String[] args) {
        try {
            String lineText;
            BufferedReader lineReader = new BufferedReader(new FileReader(input));

            while ((lineText = lineReader.readLine()) != null) {
                System.out.println(lineText);
                parseInput(lineText);
                for (int i = 0; i < totalDistances.size(); i++) {
                    System.out.println(calculate(totalDistances.get(i), totalTimes.get(i)));
                }
                int product = 1;
                for (long number : totalTimesTogether) {
                    product *= number;
                }
                System.out.println("product: " + product);
            }

            lineReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void parseInput(String input) {
        String replaced = input.replaceAll(":.*|\\d+", "").trim();
        if ("Time".equals(replaced)) {
                addTimes(input, TIMES.TIME);
        } else if ("Distance".equals(replaced)) {
                addTimes(input, TIMES.DISTANCE);
        }
    }

    public static void parseTimes() {

    }

    public static void parseDistance() {

    }

    public static void addTimes(String input, TIMES times) {
        String inputRemoved = input.replaceAll(".*:", "").trim();
        inputRemoved = inputRemoved.replaceAll(" + ", "");
        String[] convertedString = inputRemoved.split(" ");
        for (String number : convertedString) {
            if (times == TIMES.TIME) {
                totalTimes.add(Long.parseLong((number)));
            }
            else {
                totalDistances.add(Long.parseLong((number)));
            }
        }
    }

    public static Long calculate(long bestDistance, long timeLasts) {
        long newBestDistance = bestDistance;
        long amountBeaten = 0;
        for (int i = 1; i < timeLasts; i++) {
            if (i * (timeLasts - i) > bestDistance) {
                System.out.println("Hold button for: " + i);
                newBestDistance = (i * (timeLasts - i));
                amountBeaten++;

            }
        }
        System.out.println("Amount beaten for " + timeLasts + ": " + amountBeaten);
        totalTimesTogether.add(amountBeaten);
        return amountBeaten;
    }


}

