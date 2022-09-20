package com.labs.labwork3;

import java.util.Arrays;

public class FillArrays {
    double a;
    double b;
    int length = 11;
    int functionNumber;
    double[] startPointX = new double[length];
    double[] startPointY = new double[length];
    double[] interpolationPointX = new double[101];
    double[] functionPointY = new double[101];
    double[] interpolationPointY = new double[101];
    Interpolation interpolation;

    public FillArrays(double a, double b, int functionNumber) {
        this.a = a;
        this.b = b;
        this.functionNumber = functionNumber;
        startPointX = fillArrayX(startPointX);
        interpolationPointX = fillArrayX(interpolationPointX);
        startPointY = fillArrayY(startPointY, startPointX);
        functionPointY = fillArrayY(functionPointY, interpolationPointX);
        interpolation = new Interpolation(startPointX, startPointY);
        interpolationPointY = fillArrayWithInterpolation(interpolationPointY);
    }

    double[] fillArrayX(double[] array){
        double h = (b - a) / array.length;
        for (int i = 0; i < array.length; i++) {
            array[i] = a + h * i;
        }
        return array;
    }

    double[] fillArrayY(double[] arrayOfValues, double[] arrayOfVariables){
        if (functionNumber == 0){
            for (int i = 0; i < arrayOfValues.length; i++) {
                arrayOfValues[i] = Math.sin(arrayOfVariables[i]);
            }
        } else{
            for (int i = 0; i < arrayOfValues.length; i++) {
                arrayOfValues[i] = Math.exp(-(arrayOfVariables[i] + 1 / arrayOfVariables[i]));
            }
        }
        return arrayOfValues;
    }

    double[] fillArrayWithInterpolation(double[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = interpolation.interpolation(interpolationPointX[i]);
        }
        return array;
    }

    

    public double[] getStartPointX() {
        return startPointX;
    }

    public double[] getStartPointY() {
        return startPointY;
    }
}
