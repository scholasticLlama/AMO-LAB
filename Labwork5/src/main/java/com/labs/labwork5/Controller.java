package com.labs.labwork5;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField accuracyText;

    @FXML
    private MenuItem algorithmMenu;

    @FXML
    private Pane infoPane;

    @FXML
    private MenuItem mainInfoMenu;

    @FXML
    private ChoiceBox<Integer> sizeBox;

    @FXML
    private MenuItem solveSystemMenu;

    @FXML
    private Pane systemPane;

    @FXML
    private VBox systemVBox;

    @FXML
    private Pane taskPane;

    @FXML
    private Label textLabel;

    @FXML
    private Button solveButton;

    private int n;
    private TextField[][] A;
    private TextField[] B;
    private VBox resultBox;

    @FXML
    void onAlgorithmMenuAction(ActionEvent event) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                                        The Jacobi method
                In numerical linear algebra, the Jacobi method is an iterative algorithm for determining the solutions of a strictly diagonally dominant system of linear equations. Each diagonal element is solved for, and an approximate value is plugged in. The process is then iterated until it converges.""");
    }

    @FXML
    void onMainInfoMenuAction(ActionEvent event) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                                      Лабораторна робота_5
                             з дисципліни Алгоритми та методи обчислень
                                 Виконала студентка групи ІО-02
                                   Кожемяко Ярослава Романівна
                                   Номер залікової книжки: 0215
                                           Варіант: 15
                                
                               **Додаткова інформація про ввід даних**
                  Ітераційний процес пошуку розв’язку системи лінійних алгебраїчних рівнянь виду наближеними методами збігається, якщо будь-яка канонічна норма матриці менша одиниці. Канонічна норма це:
                - максимальна з сум модулів елементів матриці коефіцієнтів α по рядках;
                - це максимальна з сум модулів елементів матриці коефіцієнтів α по стовпцях;
                - корінь квадратний з сум квадратів модулів всіх елементів матриці коефіцієнтів α;""");
    }

    @FXML
    void onSolveSystemMenuAction(ActionEvent event) {
        infoPane.setVisible(false);
        taskPane.setVisible(true);
    }

    private double[][] fillA(){
        double[][] aValues = new double[n][n];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                aValues[i][j] = Double.parseDouble(A[i][j].getCharacters().toString().replaceAll(",", "."));
            }
        }
        return aValues;
    }

    private double[] fillB(){
        double[] bValues = new double[n];
        for (int i = 0; i < B.length; i++) {
            bValues[i] = Double.parseDouble(B[i].getCharacters().toString().replaceAll(",", "."));
        }
        return bValues;
    }

    private String[] fillX(double[] x){
        String[] resultText = new String[n];
        int maxLength = 0;
        for (int i = 0; i < x.length; i++) {
            resultText[i] = "x" + (i + 1) + " = " + x[i];
            if(resultText[i].length() > maxLength)
                maxLength = resultText[i].length();
        }
        for (int i = 0; i < resultText.length; i++) {
            resultText[i] += "0".repeat(maxLength - resultText[i].length());
        }
        return resultText;
    }

    @FXML
    public void onSolveButtonAction(ActionEvent event){
        try {
            double e = Double.parseDouble(accuracyText.getCharacters().toString().replaceAll(",", "."));
            JacobiMethod jacobiMethod = new JacobiMethod(n, fillA(), fillB(), e);
            if(jacobiMethod.isConvergent()){
                String[] resultText = fillX(jacobiMethod.jacobi());
                resultBox.getChildren().clear();
                resultBox.getChildren().add(new Label("Amount of the iterations " + jacobiMethod.k));
                for (int i = 0; i < Math.ceil((double) n / 3); i++) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(10);
                    for (int j = 0; j < 3; j++) {
                        if (j + i * 3 < n)
                            hbox.getChildren().add(new Label(resultText[j + i * 3]));
                    }
                    resultBox.getChildren().add(hbox);
                }
            } else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Wrong input");
                alert.setContentText("Matrix is not convergent. Change values.");

                alert.showAndWait();
            }

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Input should contain only numbers");

            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sizeBox.getItems().addAll(2, 3, 4, 5);
        sizeBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                n = newValue.intValue() + 2;
                System.out.println(n);
                systemVBox.getChildren().clear();
                A = new TextField[n][n];
                B = new TextField[n];
                Font font = Font.font("System", FontPosture.ITALIC, 16);
                for (int i = 0; i < n; i++) {
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER);
                    for (int j = 0; j < n; j++) {
                        A[i][j] = new TextField();
                        A[i][j].setMaxSize(50, 10);
                        Label label = new Label("x" + (j + 1) + " + ");
                        label.setMaxSize(60, 10);
                        label.setFont(font);
                        if(j >= n - 1){
                            label.setText("x" + (j + 1) + " = ");
                        }
                        hbox.getChildren().addAll(A[i][j], label);
                    }
                    B[i] = new TextField();
                    B[i].setMaxSize(50, 10);
                    hbox.getChildren().add(B[i]);
                    hbox.setSpacing(5);
                    systemVBox.getChildren().add(hbox);
                    systemVBox.setSpacing(10);
                }
                solveButton = new Button("Solve system");
                solveButton.setOnAction(event -> onSolveButtonAction(event));
                systemVBox.getChildren().add(solveButton);
                VBox.setMargin(solveButton, new Insets(10, 0, 10, 0));
                resultBox = new VBox();
                resultBox.setSpacing(10);
                systemVBox.getChildren().add(resultBox);
                systemVBox.setAlignment(Pos.CENTER);
            }
        });
    }
}
