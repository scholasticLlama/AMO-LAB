package com.labs.labwork3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller implements Initializable {

    @FXML
    private MenuItem algorithm;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button analysisErrorButton;

    @FXML
    private Button buildButton;

    @FXML
    private ChoiceBox<String> functionsBox;

    @FXML
    private Menu infoMenu;

    @FXML
    private Pane infoPane;

    @FXML
    private MenuItem interpolateMenu;

    @FXML
    private MenuItem mainInfoMenu;

    @FXML
    private TextField maxBounds;

    @FXML
    private TextField minBounds;

    @FXML
    private Menu taskMenu;

    @FXML
    private Pane taskPane;

    @FXML
    private Label textLabel;

    public void onMainInfoMenuAction(ActionEvent actionEvent) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                Лабораторна робота_3
                з дисципліни Алгоритми та методи обчислень
                Виконала студентка групи ІО-02
                Кожемяко Ярослава Романівна
                Номер залікової книжки: 0215
                Варіант: 15""");
    }

    public void onAlgorithmAction(ActionEvent actionEvent) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                Interpolation: Newton Method
                Newton's divided difference interpolation formula is a interpolation technique used when the interval difference is not same for all sequence of values. Divided differences are symmetric with respect to the arguments i.e independent of the order of arguments.""");
    }

    public void onInterpolateMenuAction(ActionEvent actionEvent) {
        infoPane.setVisible(false);
        taskPane.setVisible(true);
    }

    public void onBuildButtonAction(ActionEvent actionEvent) {
        try {
            String function = functionsBox.getValue();
            double minX = Double.parseDouble(minBounds.getCharacters().toString().replaceAll(",", "."));
            double maxX = Double.parseDouble(maxBounds.getCharacters().toString().replaceAll(",", "."));
            switch (function) {
                case "sin(x)" -> {
                    FillArrays fillArraysBasic = new FillArrays(minX, maxX, 0);
                    System.out.println(Arrays.toString(fillArraysBasic.interpolationPointY));
                    buildChart(fillArraysBasic);
                }
                case "exp(-(x + 1/x))" -> {
                    FillArrays fillArraysVariant = new FillArrays(minX, maxX, 1);
                    System.out.println(Arrays.toString(fillArraysVariant.interpolationPointY));
                    buildChart(fillArraysVariant);
                }
                default -> {

                }
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Input should contain only numbers");

            alert.showAndWait();
        }


    }

    public void buildChart(FillArrays fillArrays){
        Stage stage = new Stage();
        stage.setTitle("Charts of the function");

        final NumberAxis abscissa = new NumberAxis();
        final NumberAxis ordinate = new NumberAxis();
        abscissa.setLabel("x");
        ordinate.setLabel("y");

        final LineChart<Number,Number> sc = new LineChart<>(abscissa, ordinate);

        sc.setTitle("y = " + functionsBox.getValue());

        XYChart.Series startPoints = new XYChart.Series();
        startPoints.setName("Before interpolation");
        double[] x = fillArrays.startPointX;
        double[] y = fillArrays.startPointY;

        for (int i = 0; i < x.length; i++) {
            startPoints.getData().add(new XYChart.Data(x[i], y[i]));
        }



        XYChart.Series interpolationPoints = new XYChart.Series();
        interpolationPoints.setName("After interpolation");
        double[] xInterp = fillArrays.interpolationPointX;
        double[] yInterp = fillArrays.interpolationPointY;
//        double[] yDif = fillArrays.functionPointY;
//        System.out.println("x start " + Arrays.toString(fillArrays.startPointX));
//        System.out.println("y start " + Arrays.toString(fillArrays.startPointY));
//
//        System.out.println("many x " + Arrays.toString(xInterp));
//        System.out.println("y func " + Arrays.toString(yInterp));
//        System.out.println("y interpolation  " + Arrays.toString(yDif));
        for (int i = 0; i < xInterp.length; i++) {
            interpolationPoints.getData().add(new XYChart.Data(xInterp[i], yInterp[i]));
        }

        sc.setAnimated(false);
        sc.setCreateSymbols(true);

        sc.getData().addAll(startPoints, interpolationPoints);

        Scene scene  = new Scene(sc, 500, 400);

        scene.getStylesheets().add("root.css");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onAnalysisButtonAction(ActionEvent event) {
        try {
            String function = functionsBox.getValue();
            double minX = Double.parseDouble(minBounds.getCharacters().toString().replaceAll(",", "."));
            double maxX = Double.parseDouble(maxBounds.getCharacters().toString().replaceAll(",", "."));
            switch (function) {
                case "sin(x)" -> {
                    FillArrays fillArraysBasic = new FillArrays(minX, maxX, 0);
                    buildErrorChart(fillArraysBasic);
                }
                case "exp(-(x + 1/x))" -> {
                    FillArrays fillArraysVariant = new FillArrays(minX, maxX, 1);
                    buildErrorChart(fillArraysVariant);
                }
                default -> {

                }
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Input should contain only numbers. Make sure you select function to explore and make correct input.");

            alert.showAndWait();
        }
    }

    public void buildErrorChart(FillArrays fillArrays){
        Stage stage = new Stage();
        TableView<Error> table;
        stage.setTitle("Chart of the estimation of interpolation error");

        final NumberAxis abscissa = new NumberAxis();
        final NumberAxis ordinate = new NumberAxis();
        abscissa.setLabel("x");
        ordinate.setLabel("y");

        final LineChart<Number,Number> sc = new LineChart<>(abscissa, ordinate);
        sc.setTitle("Estimation of interpolation error");

        XYChart.Series errorPoints = new XYChart.Series();
        errorPoints.setName("Errors line");
        double[] xInterp = fillArrays.interpolationPointX;
        double[] yInterp = fillArrays.interpolationPointY;
        double[] yFunc = fillArrays.functionPointY;

        for (int i = 0; i < xInterp.length; i++) {
            errorPoints.getData().add(new XYChart.Data(xInterp[i], Math.abs(yInterp[i] - yFunc[i])));
        }

        sc.setAnimated(false);
        sc.setCreateSymbols(true);
        sc.getData().addAll(errorPoints);

        TableColumn<Error, Integer> nColumn = new TableColumn<>("n");
        nColumn.setMinWidth(50);
        nColumn.setCellValueFactory(new PropertyValueFactory<>("n"));

        TableColumn<Error, Double> deltaNColumn = new TableColumn<>("Delta n");
        deltaNColumn.setMinWidth(150);
        deltaNColumn.setCellValueFactory(new PropertyValueFactory<>("deltaN"));

        TableColumn<Error, Double> deltaExactNColumn = new TableColumn<>("Delta exact n");
        deltaExactNColumn.setMinWidth(150);
        deltaExactNColumn.setCellValueFactory(new PropertyValueFactory<>("deltaExactN"));

        TableColumn<Error, Double> kColumn = new TableColumn<>("k");
        kColumn.setMinWidth(150);
        kColumn.setCellValueFactory(new PropertyValueFactory<>("k"));

        table = new TableView<>();
        table.setItems(getError(xInterp, yInterp, yFunc, fillArrays.startPointX[0], fillArrays.startPointX[1], fillArrays.functionNumber, fillArrays));
        table.getColumns().addAll(nColumn, deltaNColumn, deltaExactNColumn, kColumn);

        table.setMaxSize(500, 290);

        HBox hBox = new HBox(15);
        VBox vBox = new VBox(15);
        Label label = new Label("Table 1. Calculation results for the point located between the nodes");
        double x = (fillArrays.startPointX[2] - fillArrays.startPointX[1]) / 2;
        double y;
        double yReal = fillArrays.interpolation.interpolation(x);

        double deltaN = Math.abs((x - fillArrays.startPointX[1]) * (x - fillArrays.startPointX[2])) / 2;
        if (fillArrays.functionNumber == 0) {
            y = Math.sin(x);
            deltaN *= y * (-1);
        }
        else {
            deltaN *= (Math.pow((1 - 1/Math.pow(x,2)),2) - 2/Math.pow(x,3))*Math.exp(-x - 1/x);
            y = Math.exp(- (x + 1/ x));
        }

        double deltaExactN = Math.abs(y - yReal);
        vBox.getChildren().addAll(label, table, new Label("x = " + x + "   y = " + y), new Label("Interpolation error = " + deltaN), new Label("Blurred error = " + (1 - deltaExactN / deltaN)));
        VBox.setMargin(label, new Insets(15, 15, 0, 10));
        hBox.getChildren().addAll(sc, vBox);


        Scene scene  = new Scene(hBox, 1050, 450);
        scene.getStylesheets().add("rootError.css");
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Error> getError(double[] xInterp, double[] yInterp, double[] yFunc, double x0, double x1, int functionNumber, FillArrays fillArrays){
        ObservableList<Error> errors = FXCollections.observableArrayList();
        double deltaExactN;
        double deltaN;
        double k;
        double yReal;
        double y;
        for (int i = 1; i <= 10; i++) {
            double x = (fillArrays.startPointX[i] - fillArrays.startPointX[i - 1]) / 2;
            deltaN = Math.abs((x - fillArrays.startPointX[i - 1]) * (x - fillArrays.startPointX[i])) / 2;

            if (functionNumber == 0) {
                y = Math.sin(x);
                deltaN *= y * (-1);
            }
            else {
                y = Math.exp(- (x + 1/ x));
                deltaN *= (Math.pow((1 - 1/Math.pow(x,2)),2) - 2/Math.pow(x,3))*Math.exp(-x - 1/x);
            }

            yReal = fillArrays.interpolation.interpolation(x);

            deltaExactN = y - yReal;
            k = 1 - deltaExactN / deltaN;

            errors.add(new Error(i, deltaN, deltaExactN, k));
        }
        return errors;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        functionsBox.getItems().addAll("sin(x)", "exp(-(x + 1/x))");
    }
}