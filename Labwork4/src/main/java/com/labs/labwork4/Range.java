package com.labs.labwork4;

import java.util.Arrays;

public class Range {
    double[][] ranges = new double[3][2];
    double a;
    double b;

    public Range(double a, double b) {
        this.a = a;
        this.b = b;
    }

    void setDefaultRanges(){
        double a = 3;
        double b = -12;
        double c = 9 ;
        double discriminant = b * b - 4 * a *c;
        double x1 = (-b - Math.sqrt(discriminant))/(2 * a);
        double x2 = (-b + Math.sqrt(discriminant))/(2 * a);

        ranges[0][1] = x1;
        ranges[1][0] = x1;
        ranges[1][1] = x2;
        ranges[2][0] = x2;
    }

    String setRange(){
        setDefaultRanges();
        String row = "";
        if(a < 1){
            ranges[0][0] = a;
            row += "1";
        } else{
            if(a >= 1 && a < 3){
                ranges[1][0] = a;
                row += "2";
            } else {
                if (a >= 3){
                    ranges[2][0] = a;
                    row += "3";
                }
            }
        }

        if(b > 3){
            ranges[2][1] = b;
            row += "3";
        } else{
            if(b <= 3 && b > 1){
                ranges[1][1] = b;
                row += "2";
            } else {
                if (b <= 1){
                    ranges[0][1] = b;
                    row += "1";
                }
            }
        }

        return row;
    }

    public static void main(String[] args) {
        Range range = new Range(0, 1.65964);
        String str = range.setRange();

        System.out.println(Arrays.deepToString(range.ranges));
        System.out.println(str);

        if(str.contains("1")){
            SecantMethod secantMethod = new SecantMethod(range.ranges[0][0], range.ranges[0][1], 0.0001);
            secantMethod.algorithm();
            System.out.println("1 - " + (range.ranges[0][0]) + " " + (range.ranges[0][1]));

        } if(str.contains("2") && !str.contains("3")){
            SecantMethod secantMethod = new SecantMethod(range.ranges[1][0], range.ranges[1][1], 0.0001);
            secantMethod.algorithm();
            System.out.println("2 -" + Arrays.toString(range.ranges[1]));
        } if(str.contains("3")){
            SecantMethod secantMethod = new SecantMethod(range.ranges[1][0], range.ranges[1][1], 0.0001);
            secantMethod.algorithm();
            System.out.println("2 -" + Arrays.toString(range.ranges[1]));
            SecantMethod secantMethod1 = new SecantMethod(range.ranges[2][0], range.ranges[2][1], 0.0001);
            secantMethod1.algorithm();
            System.out.println("3 -" + Arrays.toString(range.ranges[2]));
        }

    }
}
