package com.labs.labwork5;

import java.util.Arrays;

public class JacobiMethod {
    int n;
    double[][] alfa;
    double[] beta;
    double e;
    int k;

    public JacobiMethod(int n, double[][] A, double[] B, double e) {
        this.e = e;
        this.n = n;
        alfa = new double[n][n];
        beta = new double[n];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (i != j){
                    alfa[i][j] = - A[i][j] / A[i][i];
                } else alfa[i][j] = 0;
            }
        }

        System.out.println("alfa");
        for (double[] doubles : alfa) {
            System.out.println(Arrays.toString(doubles));
        }

        for (int i = 0; i < B.length; i++) {
            beta[i] = B[i] / A[i][i];
        }
        System.out.println("beta " + Arrays.toString(beta));
    }

    public boolean firstCanonicalNormIsFulfilled() {
        double maxRowSum = 0;
        double currentRowSum;
        for (double[] array : alfa) {
            currentRowSum = 0;
            for (double elem : array) {
                currentRowSum += Math.abs(elem);
            }
            if (currentRowSum > maxRowSum) {
                maxRowSum = currentRowSum;
            }
        }
        return maxRowSum < 1;
    }

    public boolean secondCanonicalNormIsFulfilled() {
        double maxColumnSum = 0;
        double currentColumnSum;
        for (int i = 0; i < alfa[0].length; i++) {
            currentColumnSum = 0;
            for (double[] doubles : alfa) {
                currentColumnSum += Math.abs(doubles[i]);
            }
            if (currentColumnSum > maxColumnSum) {
                maxColumnSum = currentColumnSum;
            }
        }
        return maxColumnSum < 1;
    }

    public boolean thirdCanonicalNormIsFulfilled() {
        double result = 0;
        for (double[] array : alfa) {
            for (double elem : array) {
                result += Math.abs(elem);
            }
        }
        return Math.sqrt(result) < 1;
    }

    public boolean hasDominantMainDiagonal() {
        double sumInRowWithoutDiagonalElem;
        int amount = 0;
        for (int i = 0; i < alfa.length; i++) {
            sumInRowWithoutDiagonalElem = 0;
            for (int j = 0; j < alfa[i].length; j++) {
                if (i != j)
                    sumInRowWithoutDiagonalElem += Math.abs(alfa[i][j]);
            }
            if (1 >= sumInRowWithoutDiagonalElem){
                amount++;
            }
        }
        return amount == alfa.length;
    }

    public boolean isConvergent() {
        return firstCanonicalNormIsFulfilled() || secondCanonicalNormIsFulfilled() || thirdCanonicalNormIsFulfilled() || hasDominantMainDiagonal();
    }

    public double[] jacobi() {
        double[] x = new double[n];
        Arrays.fill(x, 0);
        double multiply;
        double[] currentX = new double[n];
        int amountOfMatch;
        k = 0;
        do {
            amountOfMatch = 0;
            for (int i = 0; i < x.length; i++) {
                multiply = 0;
                for (int j = 0; j < alfa[i].length; j++) {
                    multiply += alfa[i][j] * x[j];
                }
                currentX[i] = beta[i] + multiply;

            }
            for (int i = 0; i < x.length; i++) {
                if (Math.abs(currentX[i] - x[i]) < e) {
                    amountOfMatch++;
                }
                x[i] = currentX[i];
            }
            k++;
        } while (amountOfMatch != x.length);
        return x;
    }
}
