package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static Enum currentGardenItem = Item.NONE;
    static String filePath = "/Users/josh/IdeaProjects/advent_5/src/com/company/inputTest.txt";
    static List<Long> seeds = new ArrayList<Long>();
    static List<Soil> soilList = new ArrayList<Soil>();
    static List<Fertilizer> fertilizerList = new ArrayList<Fertilizer>();
    static List<Water> waterList = new ArrayList<Water>();
    static List<Light> lightList = new ArrayList<Light>();
    static List<Temperature> temperatureList = new ArrayList<Temperature>();
    static List<Humidity> humidityList = new ArrayList<Humidity>();
    static List<Location> locationList = new ArrayList<Location>();
    static Long lowestNumber = 0L;
    static Long total = 999999999999999L;
    static long count = 0;
    static Boolean isTestStart = true;

    public static void main(String[] args) {
        try {
            String lineText;
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            while ((lineText = lineReader.readLine()) != null) {
                checkSeeds(lineText);
                currentGardenItem = checkGardenItems(lineText, currentGardenItem);
                checkItems(lineText);
            }
            runTest();
            System.out.println("Lowest Number: " + total);
            lineReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkItems(String input) {
        if (input.matches("[\\d\\s]+")) {

            String inputRemoved = input.replaceAll(".*:", "").trim();
            inputRemoved = inputRemoved.replaceAll(" + ", "");
            String[] convertedString = inputRemoved.split(" ");
            List<Long> convertedInts = new ArrayList<Long>();
            for (String number : convertedString) {
                convertedInts.add(Long.parseLong(number.trim()));
            }
            addGardenItems((Item) currentGardenItem, convertedInts);
        }
    }

    public static void checkSeeds(String input) {
        String replaced = input.replaceAll(":.*|\\d+", "");
        if ("seeds".equals(replaced)) {
            addTimes(input);
        }

    }

    public static void addTimes(String input) {
        String inputRemoved = input.replaceAll(".*:", "").trim();
        inputRemoved = inputRemoved.replaceAll(" + ", "");
        String[] convertedString = inputRemoved.split(" ");
        for (String number : convertedString) {
            seeds.add(Long.parseLong(number.trim()));
        }
    }

    public static Enum checkGardenItems(String input, Enum currentGardenItem) {
        String replaced = input.replaceAll(":.*|\\d+", "");
        if (!replaced.isEmpty() && replaced.trim().length() > 0) {
            if ("seed-to-soil map".equals(replaced)) {
                return Item.SOIL;
            } else if ("soil-to-fertilizer map".equals(replaced)) {
                return Item.FERTILIZER;
            } else if ("fertilizer-to-water map".equals(replaced)) {
                return Item.WATER;
            } else if ("water-to-light map".equals(replaced)) {
                return Item.LIGHT;
            } else if ("light-to-temperature map".equals(replaced)) {
                return Item.TEMPERATURE;
            } else if ("temperature-to-humidity map".equals(replaced)) {
                return Item.HUMIDITY;
            } else if ("humidity-to-location map".equals(replaced)) {
                return Item.LOCATION;
            } else {
                return currentGardenItem;
            }
        }
        return currentGardenItem;
    }

    static Gardening addGardenItems(Item item,List<Long> numbers) {
        switch (item) {
            case SOIL:
                soilList.add(new Soil(numbers.get(0), numbers.get(1), numbers.get(2), Item.SOIL));
                break;
            case FERTILIZER:
                fertilizerList.add(new Fertilizer(numbers.get(0), numbers.get(1), numbers.get(2), Item.FERTILIZER));
                break;
            case WATER:
                waterList.add(new Water(numbers.get(0), numbers.get(1), numbers.get(2), Item.WATER));
                break;
            case LIGHT:
                lightList.add(new Light(numbers.get(0), numbers.get(1), numbers.get(2), Item.LIGHT));
                break;
            case TEMPERATURE:
                temperatureList.add(new Temperature(numbers.get(0), numbers.get(1), numbers.get(2), Item.TEMPERATURE));
                break;
            case HUMIDITY:
                humidityList.add(new Humidity(numbers.get(0), numbers.get(1), numbers.get(2), Item.HUMIDITY));
                break;
            case LOCATION:
                locationList.add(new Location(numbers.get(0), numbers.get(1), numbers.get(2), Item.LOCATION));
                break;
            case NONE:
        }
        return null;
    }

    private static void runTest() {
        // Loop through seeds
        for (long firstIndex = 0; firstIndex < seeds.size(); firstIndex+=2) {
            long seed;
            for (seed = seeds.get((int) firstIndex); seed < seeds.get((int) (firstIndex + 1)) + seeds.get((int) firstIndex); seed++) {
                findLowestNumber(soilList, seed);

                findLowestNumber(fertilizerList, lowestNumber);

                findLowestNumber(waterList, lowestNumber);

                findLowestNumber(lightList, lowestNumber);

                findLowestNumber(temperatureList, lowestNumber);

                findLowestNumber(humidityList, lowestNumber);

                findLowestNumber(locationList, lowestNumber);


                if (lowestNumber < total) {
                    total = lowestNumber;
                }


                count++;

            }

            System.out.println("HERE");
        }

    }

    // From input of items and seed, find the lowest number
    // findMap returns map or if not available then seed
    private static void findLowestNumber(List<? extends Gardening> gardeningItems, Long seed) {
        long current = 0;
        Boolean isFound = false;

        for (Gardening gardeningItem : gardeningItems) {
            // get current map item (may be null)
            long test = gardeningItem.findMatch(seed);

            // if has been found then loop through
            if (gardeningItem.isFound(seed)) {
                if (isFound) {
                    if (gardeningItem.findMatch(seed) < test) {
                        current = test;
                    }
                }
                else {
                    current = test;
                    isFound = true;
                }
            }

        }
        // number is found, so take the lowest
        if (isFound) {
            lowestNumber = current;
        }
        else {
            lowestNumber = seed;
        }


    }

    private static void addLists() {

    }
}
