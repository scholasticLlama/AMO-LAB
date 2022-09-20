package com.labs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Temp {
    public static void main(String[] args) {
        int[][] temp = new int[10][10];
        ArrayList<Integer> ships = new ArrayList<>();

        Arrays.stream(temp).forEach(a -> Arrays.fill(a, 0));
        Collections.addAll(ships, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);

        boolean isSet;
        int index;
        int numberOfDesks;
        int shift = 0;
        int count;
        int maxCount;
        int maxIndex;
        int minIndex;
        int counterL;
        int counterH;
        boolean isFree;
        int iter = 0;


        do {
            for (int i = 0; i < temp.length; i++) {
                isSet = Math.random() < 0.5;
                if (isSet) {
                    count = 0;
                    maxCount = 0;
                    maxIndex = 0;
                    for (int j = 0; j < temp[i].length; j++) {
                        if (temp[i][j] == 0) {
                            count++;
                        } else {
                            count = 0;
                        }
                        if (count > maxCount) {
                            maxCount = count;
                            maxIndex = j;
                        }
                    }
                    if (ships.size() > 0) {
                        do {
                            index = (int) (Math.random() * (ships.size() - 1));
                        } while (ships.get(index) > maxCount);
                        numberOfDesks = ships.get(index);

                        shift = (int) (Math.random() * 5);

                        if (shift + numberOfDesks <= maxCount) {
                            shift = 0;
                        }
                        minIndex = maxIndex - maxCount + shift;
                        counterL = 0;
                        counterH = 0;
                        isFree = false;
                        for (int l = minIndex; l < minIndex + numberOfDesks; l++) {
                            if ((i > 0 && l > 0) && (temp[i - 1][l] == 0 || temp[i - 1][l] == -1)) {
                                counterH++;
                            }
                            if ((i < 9 && l > 0) && (temp[i + 1][l] == 0 || temp[i + 1][l] == -1)) {
                                counterL++;
                            }
                        }
                        if ((counterL == numberOfDesks || i == 0) && (counterH == numberOfDesks || i == 9)) {
                            if ((minIndex > 0 && i > 0 && (temp[i - 1][minIndex] == 0 || temp[i - 1][minIndex] == -1)) || minIndex == 0) {
                                if ((minIndex + numberOfDesks + 1 < 9 && i > 0 && (temp[i - 1][minIndex + numberOfDesks + 1] == 0 || temp[i - 1][minIndex + numberOfDesks + 1] == -1)) || minIndex + numberOfDesks + 1 == 9) {
                                    if ((minIndex > 0 && i < 9 && (temp[i + 1][minIndex] == 0 || temp[i + 1][minIndex] == -1)) || minIndex == 0) {
                                        if ((minIndex + numberOfDesks + 1 < 9 && i < 9 && (temp[i + 1][minIndex + numberOfDesks + 1] == 0 || temp[i + 1][minIndex + numberOfDesks + 1] == -1)) || minIndex + numberOfDesks + 1 == 9) {
                                            isFree = true;
                                        }
                                    }
                                }
                            }
                        }
                        if (isFree) {
                            ships.remove(index);
                            for (int k = 0; k < numberOfDesks; k++) {
                                temp[i][minIndex + k + 1] = numberOfDesks;
                                if (i > 0) {
                                    temp[i - 1][minIndex + k + 1] = -1;
                                }
                                if (i < 9) {
                                    temp[i + 1][minIndex + k + 1] = -1;
                                }
                            }
                            if (minIndex >= 0) {
                                temp[i][minIndex] = -1;
                                if (i > 0) {
                                    temp[i - 1][minIndex] = -1;
                                }
                                if (i < 9) {
                                    temp[i + 1][minIndex] = -1;
                                }
                            }
                            if (maxIndex < 10) {
                                temp[i][minIndex + numberOfDesks + 1] = -1;
                                if (i > 0) {
                                    temp[i - 1][minIndex + numberOfDesks + 1] = -1;
                                }
                                if (i < 9) {
                                    temp[i + 1][minIndex + numberOfDesks + 1] = -1;
                                }
                            }
                        }

                    }
                }
            }
            iter++;
        } while (ships.size() > 0);


        for (int[] ints : temp) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

//package com.labs;
//
//        import java.util.ArrayList;
//        import java.util.Arrays;
//        import java.util.Collections;
//
//public class RandomSetting {
//    int[][] field = new int[10][10];
//    ArrayList<Integer> ships = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 3, 4));
//
//
//    void fillArraysWithDefault() {
//        Arrays.stream(field).forEach(a -> Arrays.fill(a, 0));
//    }
//
//    void setShips() {
//        boolean isSettableRow;
//        int freeSpaceMaxCount, freeSpaceMaxIndex, numberOfDesks, shift;
//        int[] result;
//        for (int i = 0; i < field.length; i++) {
//            isSettableRow = Math.random() < 0.5;
//            if (isSettableRow && ships.size() != 0) {
//                result = findMaxFreeSpaces(i);
//                freeSpaceMaxCount = result[0];
//                freeSpaceMaxIndex = result[1];
////                System.out.println(Arrays.toString(result));
//                int index = randomizeNumberOfDesks(freeSpaceMaxCount);
//                numberOfDesks = ships.get(index);
////                System.out.println(numberOfDesks);
//                setShipInARow(numberOfDesks, i, freeSpaceMaxCount, freeSpaceMaxIndex - freeSpaceMaxCount);
//                ships.remove(index);
////                System.out.println(Arrays.toString(field[i]));
//
//            }
//        }
//    }
//
//    void setSpaceOver(int desks, int row, int count, int minIndex, int shift){
//        if (row > 0 ){
//            for (int i = 0; i < desks; i++) {
//                field[row - 1][minIndex + shift + i + 1] = -1;
//            }
//        }
//    }
//
//    void setSpaceUnder(int desks, int row, int count, int minIndex, int shift){
//        if (row < 9 ){
//            for (int i = 0; i < desks; i++) {
//                field[row + 1][minIndex + shift + i + 1] = -1;
//            }
//        }
//    }
//
//    boolean isFreeSpaceOver(int desks, int row, int count, int minIndex, int shift){
//        if (row == 0) return true;
//        for (int i = 0; i < desks; i++) {
//            int elem = field[row - 1][minIndex + shift + i + 1];
//            if (!(elem == -1 || elem == 0)) return false;
//        }
//        return true;
//    }
//
//    boolean isFreeSpaceUnder(int desks, int row, int count, int minIndex, int shift){
//        if (row == 9) return true;
//        for (int i = 0; i < desks; i++) {
//            int elem = field[row + 1][minIndex + shift + i + 1];
//            if (!(elem == -1 || elem == 0)) return false;
//        }
//        return true;
//    }
//
//
//
//    boolean isFreeSpaceAround(int desks, int row, int count, int minIndex, int shift){
//        return isFreeSpaceOver(desks, row, count, minIndex, shift) && isFreeSpaceUnder(desks, row, count, minIndex, shift);
//    }
//
//    void setShipInARow(int desks, int row, int count, int minIndex) {
//        int shift = (int) (Math.random() * 5);
//        if (shift + desks > count) {
//            shift = 0;
//        }
//        if (isFreeSpaceAround(desks, row, count, minIndex, shift)){
//            for (int i = 0; i < desks; i++) {
//                field[row][minIndex + shift + i + 1] = desks;
//            }
//            setSpaceOver(desks, row, count, minIndex, shift);
//            setSpaceUnder(desks, row, count, minIndex, shift);
//        }
//    }
//
//    int randomizeNumberOfDesks(int counter) {
//        int index = 0;
//        do {
//            index = (int) (Math.random() * ships.size());
//        } while (counter < ships.get(index));
//        return index;
//    }
//
//    int[] findMaxFreeSpaces(int i) {
//        int counter = 0, maxCounter = 0, maxIndex = 0;
//        for (int j = 0; j < field[i].length; j++) {
//            if (field[i][j] == 0) counter++;
//            else counter = 0;
//            if (counter > maxCounter) {
//                maxCounter = counter;
//                maxIndex = j;
//            }
//        }
//        return new int[]{maxCounter, maxIndex};
//    }
//
//    public static void main(String[] args) {
//        RandomSetting randomSetting = new RandomSetting();
//        randomSetting.fillArraysWithDefault();
//        do {
//            randomSetting.setShips();
//        } while (randomSetting.ships.size() > 0);
//        for (int[] row : randomSetting.field) {
//            System.out.println(Arrays.toString(row));
//        }
//
//    }
//}
