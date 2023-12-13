package com.company;

public enum PIPE {
    VERTICAL('|'),
    HORIZONTAL('-'),
    NORTH_EAST_L('L'),
    NORTH_WEST_J('J'),
    SOUTH_WEST_7('7'),
    SOUTH_EAST_F('F'),
    GROUND('.'),
    STARTING_POSITION_S('S');

    private final char value;

    PIPE(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}


/*
public enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(1), QUEEN(12), KING(13), ACE(14);
    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

 */
