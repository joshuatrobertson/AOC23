package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.Logic.checkWinners;

public class Main {



    public static void main(String[] args) {
        String filePath = "/Users/josh/IdeaProjects/advent_4/src/com/company/codeText.txt";
        Integer runningTotal = 0;
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while (reader.readLine() != null) lines++;
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> copies = new ArrayList<Integer>();
        for (int i = 0; i < lines; i++) {
            copies.add(1);
        }
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText;

            int gameNumber = 0;
            List<Integer> gameNumbers;
            List<Integer> winningNumbers;


            while ((lineText = lineReader.readLine()) != null) {
                Integer timesWon = 0;
                gameNumbers = new ArrayList<Integer>();
                winningNumbers = new ArrayList<Integer>();
                Integer testNumber = Logic.getGameNumber(lineText);
                System.out.println("Extracted numeric values: " + testNumber);
                gameNumbers = Logic.getValuesFromString(lineText, Values.FIRST);
                winningNumbers = Logic.getValuesFromString(lineText, Values.LAST);
                timesWon = checkWinners(gameNumbers, winningNumbers);
                // Add to the next cards before check tests
                Integer valueInCurrentCard = copies.get(testNumber - 1);
                Integer numberOfCopies = valueInCurrentCard * timesWon;
                for (int i = 0; i < timesWon; i++) {
                    for (int j = 0; j < valueInCurrentCard; j++) {
                        if (i + testNumber < copies.size()) {
                            Integer newNumber = copies.get(testNumber + i);
                            copies.set(testNumber + i, newNumber + 1);
                        }
                    }
                }
            }

                for (int i = 0; i < copies.size(); i++) {
                    runningTotal = runningTotal + copies.get(i);
                }
                System.out.println("TOTAL: " + runningTotal);
                lineReader.close();
            } catch(IOException ex){
                System.err.println(ex);
            }
        }

}
