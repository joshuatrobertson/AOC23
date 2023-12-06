package com.company;

import java.util.HashMap;

abstract class Gardening {
    Long destinationRange;
    Long sourceRange;
    Long rangeLength;
    Item type;
    HashMap<Long, Long> hashMap = new HashMap();
    Long[] destinationRangeList = new Long[100];
    Long[] sourceRangeList = new Long[100];

    Gardening(Long destinationRange, Long sourceRange, Long rangeLength, Item type) {
        this.destinationRange = destinationRange;
        this.sourceRange = sourceRange;
        this.rangeLength = rangeLength;

        this.type = type;
        //initialiseMap();
        //TODO can probably remove type from here
    }

    private void initialiseMap() {
        for (int i = 0; i < rangeLength; i++ ) {
            hashMap.put(sourceRange + i, destinationRange + i);
        }
    }


    public Item returnType() {
        return this.type;
    }

    public Long getDestinationRange() {
        return null;
    }

    public Long findMap(Long seedNumber) {
        if (hashMap.get(seedNumber) != null) {
            return hashMap.get(seedNumber);
        }
        return seedNumber;
    }

    public Long findMatch(Long seedNumber) {
        //find if out of range (i.e. is not within range start)
        if (seedNumber < sourceRange) {
            return seedNumber;
        }

        // find if out of top range
        if (seedNumber > sourceRange + rangeLength) {
            return seedNumber;
        }

        return (seedNumber - sourceRange) + destinationRange;
    }


    public Boolean isFound(Long seedNumber) {
        //find if out of range (i.e. is not within range start)
        if (seedNumber < sourceRange) {
            return false;
        }

        // find if out of top range
        if (seedNumber > sourceRange + rangeLength) {
            return false;
        }

        return true;
    }
}
