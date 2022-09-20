package com.labs.labwork4;

public class SecantMethod {
    double a;
    double b;
    double e;
    double x;
    double duplicationOfA;
    int k;

    public SecantMethod(double a, double b, double e) {
        this.a = a;
        this.b = b;
        this.e = e;
    }

    public String algorithm() {
        if (productOfFunctionOnEndsLessThanZero(a, b)) {
            k = 0;
            if (productOfDerivativesMoreThanZero(a)) {
                System.out.println("true");
                duplicationOfA = a;
                a = b;
                b = duplicationOfA;
            }
            do {
                x = a - (function(a) * (b - a)) / (function(b) - function(a));
                k += 1;
                if (Math.abs(x - a) < e) {
                    return String.valueOf(x);
                } else {
                    a = x;
                }
            } while (true);
        } else {
            return null;
        }
    }

    public double function(double x) {

        return Math.pow(x, 3) - 6 * Math.pow(x, 2) + 9 * x - 3;
    }

    public double secondDerivativeOfFunction(double x) {
        return 6 * x - 12;
    }

    public boolean productOfFunctionOnEndsLessThanZero(double a, double b) {
        return (function(a) * function(b)) < 0;
    }

    public boolean productOfDerivativesMoreThanZero(double x) {
        return (function(x) * secondDerivativeOfFunction(x)) > 0;
    }

    public static void main(String[] args) {
        SecantMethod secantMethod = new SecantMethod(0, 1, 0.0001);
        secantMethod.algorithm();
        System.out.println(secantMethod.k);
    }

}
