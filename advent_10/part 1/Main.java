package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Logic.getNextPosition;
import static com.company.Logic.returnCharToPipe;

public class Main {

    static String input = "/Users/josh/IdeaProjects/advent_10/src/com/company/input.txt";
    static int count = 0;
    static ArrayList<ArrayList<PIPE>> pipes = new ArrayList<ArrayList<PIPE>>();
    static int[] startingPosition = new int[2];
    static PIPE nextPipe;
    static int[] nextPosition;

    public static void main(String[] args) {
        try {
            String lineText;
            BufferedReader lineReader = new BufferedReader(new FileReader(input));

            while ((lineText = lineReader.readLine()) != null) {
                addPipes(lineText);
            }
            findStartingPosition();
            nextPipe = PIPE.STARTING_POSITION_S;
            traverseMap(nextPipe, DIRECTION.NORTH, startingPosition);
            lineReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void addPipes(String input) {
        ArrayList<PIPE> pipeRow = new ArrayList<PIPE>();
        for (char c : input.toCharArray()) {
            pipeRow.add(returnCharToPipe(c));
        }
        pipes.add(pipeRow);
    }

    private static PIPE getPipe(int[] position) {
        return pipes.get(position[0]).get(position[1]);
    }

    private static void findStartingPosition() {
        for (int i = 0; i < pipes.size(); i++) {
            for (int y = 0; y < pipes.get(i).size(); y++) {
                if (pipes.get(i).get(y) == PIPE.STARTING_POSITION_S) {
                    System.out.println("Starting position = " + i + ", " + y);
                    startingPosition = new int[]{i, y};
                    return;
                }
            }
        }
    }

    private static void traverseMap(PIPE pipe, DIRECTION initialDirection, int[] initialPosition) {
        count++;
        System.out.println("Count: " + count);

        if (count == 1) {
            int[] nextPosition = getNextPosition(initialPosition, initialDirection);
            pipe = getPipe(nextPosition);
        }

        while (pipe != PIPE.STARTING_POSITION_S || count == 1) {
            count++;
            if (pipe == PIPE.STARTING_POSITION_S && count > 1) {
                break;
            }

            if (pipe == PIPE.GROUND) {
                System.out.println("ERROR FOUND '.'");
            }



            if (pipe != PIPE.GROUND) {
                if (pipe == PIPE.NORTH_EAST_L) {
                    if (initialDirection == DIRECTION.SOUTH) {
                        initialDirection = DIRECTION.EAST;
                    } else {
                        initialDirection = DIRECTION.NORTH;
                    }
                } else if (pipe == PIPE.SOUTH_WEST_7) {
                    if (initialDirection == DIRECTION.NORTH) {
                        initialDirection = DIRECTION.WEST;
                    } else {
                        initialDirection = DIRECTION.SOUTH;
                    }
                } else if (pipe == PIPE.SOUTH_EAST_F) {
                    if (initialDirection == DIRECTION.WEST) {
                        initialDirection = DIRECTION.SOUTH;
                    } else {
                        initialDirection = DIRECTION.EAST;
                    }
                } else if (pipe == PIPE.NORTH_WEST_J) {
                    if (initialDirection == DIRECTION.EAST) {
                        initialDirection = DIRECTION.NORTH;
                    } else {
                        initialDirection = DIRECTION.WEST;
                    }
                }
                nextPosition = getNextPosition(initialPosition, initialDirection);
                nextPipe = getPipe(nextPosition);
                initialPosition = nextPosition;
                pipe = nextPipe;
                System.out.println("Count: " + count);
            }
        }

        System.out.println("Final Count = " + ((count-1) / 2));
    }

}
