package com.example.demo2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override public void start(Stage stage) {
        stage.setTitle("Chart of the function");

        final NumberAxis abscissa = new NumberAxis();
        final NumberAxis ordinate = new NumberAxis();
        abscissa.setLabel("x");
        ordinate.setLabel("y");

        final LineChart<Number, Number> sc = new LineChart<>(abscissa, ordinate);

        sc.setTitle("y = x^3 - 6x^2 + 9x - 3");

        XYChart.Series rootPoints = new XYChart.Series();
        rootPoints.setName("Roots");

//        for (String s : x) {
//            if (s != null) {
//                rootPoints.getData().add(new XYChart.Data(Double.parseDouble(s), 0));
//            }
//        }
        rootPoints.getData().add(new XYChart.Data(0.4, 0));
        rootPoints.getData().add(new XYChart.Data(1.65, 0));


        XYChart.Series functionPoints = new XYChart.Series();
        functionPoints.setName("function");
        double[] xFunction = new double[101];
        double[] yFunction = new double[101];

        xFunction = fillArrayX(xFunction, 0, 3);
        yFunction = fillArrayY(yFunction, xFunction);

        for (int i = 0; i < xFunction.length; i++) {
            functionPoints.getData().add(new XYChart.Data(xFunction[i], yFunction[i]));
        }

        sc.setAnimated(false);
        sc.setCreateSymbols(true);

        sc.getData().addAll(rootPoints, functionPoints);

        Scene scene = new Scene(sc, 500, 400);

        scene.getStylesheets().add("root.css");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    double[] fillArrayX(double[] array, double a, double b) {
        double h = (b - a) / array.length;
        for (int i = 0; i < array.length; i++) {
            array[i] = a + h * i;
        }
        return array;
    }

    double[] fillArrayY(double[] arrayOfValues, double[] arrayOfVariables) {
        for (int i = 0; i < arrayOfValues.length; i++) {
            arrayOfValues[i] = Math.pow(arrayOfVariables[i], 3) - 6 * Math.pow(arrayOfVariables[i], 2) + 9 * arrayOfVariables[i] - 3;
        }
        return arrayOfValues;
    }



}