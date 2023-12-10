package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static String input = "/Users/josh/IdeaProjects/advent_9/src/com/company/input.txt";
    static int total = 0;

    public static void main(String[] args) {
        try {
            String lineText;
            BufferedReader lineReader = new BufferedReader(new FileReader(input));

            while ((lineText = lineReader.readLine()) != null) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                findNextSequence(turnStringIntoIntArray(lineText), list);
                int num = 0;
                for (int listItem : list) {
                    num+=listItem;
                }
                total+=num;
            }

            System.out.println("Total = " + total);

            lineReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    private static void findNextSequence(int[] input, ArrayList<Integer> list) {
        int[] newNumbers = new int[input.length - 1];
        list.add(input[input.length-1]);
            for (int i = 0; i < newNumbers.length; i++) {
                newNumbers[i] = findDifference(input[i], input[i + 1]);
            }
            input = newNumbers;

        if (!isArrayFullOfZeros(input)) {
            findNextSequence(newNumbers, list);
        }
    }

    private static boolean isArrayFullOfZeros(int[] input) {
        for (int i : input) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] turnStringIntoIntArray(String input) {
        String[] numberStrings = input.split("\\s+");

        int[] intArray = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            intArray[i] = Integer.parseInt(numberStrings[i]);
        }
        return intArray;
    }

    private static int findDifference(int firstNumber, int secondNumber) {
        return secondNumber - firstNumber;
    }
}
