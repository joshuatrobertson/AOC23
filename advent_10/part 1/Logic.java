package com.company;

public class Logic {



    // Given the current position and the direction travelling, return the next position in the array
    public static int[] getNextPosition(int[] currentPosition, DIRECTION direction) {
        int[] newPosition = new int[2];
        if (direction == DIRECTION.NORTH) {
            newPosition[0] = currentPosition[0] - 1;
            newPosition[1] = currentPosition[1];
        } else if (direction == DIRECTION.SOUTH) {
            newPosition[0] = currentPosition[0] + 1;
            newPosition[1] = currentPosition[1];
        } else if (direction == DIRECTION.EAST) {
            newPosition[0] = currentPosition[0];
            newPosition[1] = currentPosition[1] + 1;
        } else if (direction == DIRECTION.WEST) {
            newPosition[0] = currentPosition[0];
            newPosition[1] = currentPosition[1] - 1;
        }
        return newPosition;
    }



    static PIPE returnCharToPipe(char c) {
        switch (c) {
            case '|':
                return PIPE.VERTICAL;
            case '-':
                return PIPE.HORIZONTAL;
            case 'L':
                return PIPE.NORTH_EAST_L;
            case 'J':
                return PIPE.NORTH_WEST_J;
            case '7':
                return PIPE.SOUTH_WEST_7;
            case 'F':
                return PIPE.SOUTH_EAST_F;
            case '.':
                return PIPE.GROUND;
            case 'S':
                return PIPE.STARTING_POSITION_S;
        }
        return null;
    }

}
