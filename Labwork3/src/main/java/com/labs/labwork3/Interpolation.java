package com.labs.labwork3;

import java.util.Arrays;

public class Interpolation {
    double[] startPointX;
    double[] startPointY;
    int length;
    double[] separatedDifference;
    double[] denominatorOfSeparatedDifference;

    public Interpolation(double[] startPointX, double[] startPointY) {
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        length = startPointX.length;
        separatedDifference = new double[length];
        denominatorOfSeparatedDifference = new double[length];

    }

    void multiply(int index){
        for (int i = 0; i < length; i++){
            if (index != i){
                denominatorOfSeparatedDifference[i] *= startPointX[i] - startPointX[index];
            }
        }
    }

    void fillArrays() {
        Arrays.fill(denominatorOfSeparatedDifference, 1);
        double currentDifference;
        for (int i = 0; i < length; i++) {
            multiply(i);
            currentDifference = 0;
            for (int j = 0; j < i + 1; j++) {
                currentDifference += startPointY[j]/denominatorOfSeparatedDifference[j];
            }
            separatedDifference[i] = currentDifference;
        }

    }

    double interpolation(double x){
        fillArrays();
        double result = 0;
        double degreeOfX = 1;

        for (int i = 0; i < length; i++) {
            result += separatedDifference[i] * degreeOfX;
            degreeOfX *= x - startPointX[i];
        }
        return result;
    }

}
