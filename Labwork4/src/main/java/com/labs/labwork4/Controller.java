package com.labs.labwork4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Controller {

    @FXML
    private TextField accuracyText;

    @FXML
    private MenuItem algorithmMenu;

    @FXML
    private Button buildFunction;

    @FXML
    private Button findRootsButton;

    @FXML
    private Menu infoMenu;

    @FXML
    private Pane infoPane;

    @FXML
    private MenuItem mainInfoMenu;

    @FXML
    private TextField maxBounds;

    @FXML
    private TextField minBounds;

    @FXML
    private Label result1;

    @FXML
    private Label result2;

    @FXML
    private Label result3;

    @FXML
    private MenuItem solveEquationMenu;

    @FXML
    private Menu taskMenu;

    @FXML
    private Pane taskPane;

    @FXML
    private Label textLabel;

    @FXML
    void onMainInfoMenuAction(ActionEvent event) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                Лабораторна робота_4
                з дисципліни Алгоритми та методи обчислень
                Виконала студентка групи ІО-02
                Кожемяко Ярослава Романівна
                Номер залікової книжки: 0215
                Варіант: 15""");
    }

    @FXML
    void onAlgorithmMenuAction(ActionEvent event) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                The secant method
                In numerical analysis, the secant method is a root-finding algorithm that uses a succession of roots of secant lines to better approximate a root of a function f.
                The secant method can be thought of as a finite-difference approximation of Newton's method.""");
    }

    @FXML
    void onSolveEquationMenuAction(ActionEvent event) {
        infoPane.setVisible(false);
        taskPane.setVisible(true);
    }

    @FXML
    void onFindRootsButtonAction(ActionEvent event) {
        try {
            double a = Double.parseDouble(minBounds.getText().replaceAll(",", "."));
            double b = Double.parseDouble(maxBounds.getText().replaceAll(",", "."));

            if (b <= a) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Wrong input");
                alert.setContentText("Variable b must be more than variable a.");

                alert.showAndWait();

            } else {
                double e = Double.parseDouble(accuracyText.getText().replaceAll(",", "."));

                Range range = new Range(a, b);
                String str = range.setRange();

                String[] labelText = new String[]{null, null, null};
                Label[] labels = {result1, result2, result3};

                for (Label label : labels) {
                    label.setText(null);
                }

                if (str.contains("1")) {
                    SecantMethod secantMethod = new SecantMethod(range.ranges[0][0], range.ranges[0][1], e);
                    String value = secantMethod.algorithm();
                    String result = "   no root, change range";
                    if (value != null) result = "   x = " + value + " k = " +secantMethod.k;
                    labelText[0] = "On range " + Arrays.toString(range.ranges[0]) + result;

                }
                if (str.contains("2")) {
                    SecantMethod secantMethod = new SecantMethod(range.ranges[1][0], range.ranges[1][1], e);
                    String value = secantMethod.algorithm();
                    String result = "   no root, change range";
                    if (value != null) result = "   x = " + value + " k = " +secantMethod.k;
                    labelText[1] = "On range " + Arrays.toString(range.ranges[1]) + result;

                }
                if (str.contains("3")) {
                    if (str.contains("1")) {
                        SecantMethod secantMethod = new SecantMethod(range.ranges[1][0], range.ranges[1][1], 0.0001);
                        String value = secantMethod.algorithm();
                        String result = "   no root, change range";
                        if (value != null) result = "   x = " + value + " k = " +secantMethod.k;
                        labelText[1] = "On range " + Arrays.toString(range.ranges[1]) + result;
                    }
                    SecantMethod secantMethod = new SecantMethod(range.ranges[2][0], range.ranges[2][1], e);
                    String value = secantMethod.algorithm();
                    String result = "   no root, change range";
                    if (value != null) result = "   x = " + value + " k = " +secantMethod.k;
                    labelText[2] = "On range " + Arrays.toString(range.ranges[2]) + result;
                }

                System.out.println(Arrays.toString(labelText));

                int index = 0;
                for (String s : labelText) {
                    if (s != null) {
                        labels[index].setText(s);
                        index++;
                    }
                }
            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Input should contain only numbers");

            alert.showAndWait();
        }
    }

    public String[] findRoots(double a, double b, double e) {
        Range range = new Range(a, b);
        String str = range.setRange();
        System.out.println(str);
        String[] x = new String[]{null, null, null};

        if (str.contains("1")) {
            SecantMethod secantMethod = new SecantMethod(range.ranges[0][0], range.ranges[0][1], e);
            String value = secantMethod.algorithm();
            if (value != null) {
                x[0] = value;
            }

        }
        if (str.contains("2")) {
            SecantMethod secantMethod = new SecantMethod(range.ranges[1][0], range.ranges[1][1], e);
            String value = secantMethod.algorithm();
            if (value != null) {
                x[1] = value;
            }

        }
        if (str.contains("3")) {
            if (str.contains("1")) {
                SecantMethod secantMethod = new SecantMethod(range.ranges[1][0], range.ranges[1][1], 0.0001);
                String value = secantMethod.algorithm();
                if (value != null) {
                    x[1] = value;
                }
            }
            SecantMethod secantMethod = new SecantMethod(range.ranges[2][0], range.ranges[2][1], e);
            String value = secantMethod.algorithm();
            if (value != null) {
                x[2] = value;
            }
        }
        System.out.println(Arrays.toString(x));
        return x;

    }


    @FXML
    void onBuildFunctionAction(ActionEvent event) {
        try {
            result1.setText(null);
            result2.setText(null);
            result3.setText(null);
            double a = Double.parseDouble(minBounds.getText().replaceAll(",", "."));
            double b = Double.parseDouble(maxBounds.getText().replaceAll(",", "."));
            double e = Double.parseDouble(accuracyText.getText().replaceAll(",", "."));

            if (b <= a) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Wrong input");
                alert.setContentText("Variable b must be more than variable a.");

                alert.showAndWait();

            } else buildChart(a, b, findRoots(a, b, e));


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Input should contain only numbers");

            alert.showAndWait();
        }
    }

    public void buildChart(double a, double b, String[] x) {
        Stage stage = new Stage();
        stage.setTitle("Chart of the function");
        final NumberAxis abscissa = new NumberAxis();
        final NumberAxis ordinate = new NumberAxis();
        abscissa.setLabel("x");
        ordinate.setLabel("y");
        final LineChart<Number, Number> sc = new LineChart<>(abscissa, ordinate);
        sc.setTitle("y = x^3 - 6x^2 + 9x - 3");

        XYChart.Series rootPoints = new XYChart.Series();
        rootPoints.setName("Roots");
        for (String s : x) {
            if (s != null) {
                rootPoints.getData().add(new XYChart.Data(Double.parseDouble(s), 0));
            }
        }

        XYChart.Series functionPoints = new XYChart.Series();
        functionPoints.setName("Function");
        double[] xFunction = new double[101];
        double[] yFunction = new double[101];
        xFunction = fillArrayX(xFunction, a, b);
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
