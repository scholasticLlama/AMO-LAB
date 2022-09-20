package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField arrayText;

    @FXML
    private Button downloadButton;

    @FXML
    private Menu infoMenu;

    @FXML
    private Pane infoPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextArea resultText;

    @FXML
    private Button sortButton;

    @FXML
    private Menu taskMenu;

    @FXML
    private Pane taskPane;

    @FXML
    public Button generateButton;

    @FXML
    private Label textLabel;

    @FXML
    private Label timeLabel;

    private int iter = 0;

    @FXML
    void onInfoMenuClick(ActionEvent event) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("""
                Лабораторна робота_2
                з дисципліни Алгоритми та методи обчислень
                Виконала студентка групи ІО-02
                Кожемяко Ярослава Романівна
                Номер залікової книжки: 0215
                Варіант: 15

                **Додаткова інформація про формат вводу даних**
                - Елементи масивів потрібно записувати через пробіл
                - Можливий ввід цілих та дійсних чисел            \s
                Приклад: n = 4,     a[1,n] = 1 2.4 -3 4
                """);
    }

    @FXML
    void onSortButtonClick(ActionEvent event) {
        resultText.setText(null);
        timeLabel.setText(null);
        double[] array = strToDouble();
        if (array != null && array.length != 0) {
            resultText.setText(Arrays.toString(shaker(array, array.length)));
            timeLabel.setText("Amount of operations = " + iter + ",     amount of elements = " + array.length);
        }
    }

    @FXML
    void onTaskAlgorithmMenuClick(ActionEvent event) {
        taskPane.setVisible(false);
        infoPane.setVisible(true);
        textLabel.setText("Cocktail shaker sort is an extension of bubble sort. The algorithm extends bubble sort by operating in two directions. While it improves on bubble sort by more quickly moving items to the beginning of the list, it provides only marginal performance improvements.");
    }

    @FXML
    void onTaskSortMenuClick(ActionEvent event) {
        infoPane.setVisible(false);
        taskPane.setVisible(true);
    }

    @FXML
    void onDownloadButtonClick(ActionEvent event) {
        uploadValueFromFile(arrayText);
    }

    @FXML
    void onGenerateButtonClick(ActionEvent event) {
        arrayText.setText(generateRandomArray());
    }

    private void uploadValueFromFile(TextField text) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(stage);
        try {
            FileReader reader = new FileReader(file);
            Scanner scan = new Scanner(reader);
            text.setText(scan.nextLine());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private String generateRandomArray() {
        int n = (int) (Math.random() * 145 + 5);
        StringBuilder array = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if ((i + n) % 3 == 0) array.append(String.format("%.0f", Math.random() * 655 - 255)).append(" ");
            else array.append(String.format("%.2f", Math.random() * 655 - 255)).append(" ");

        }
        return array.toString().replaceAll(",", ".");
    }

    private double[] strToDouble() {
        try {
            String[] arrayInString = arrayText.getCharacters().toString().replaceAll(",", ".").split("[ ]+");
            double[] array = new double[arrayInString.length];
            for (int i = 0; i < arrayInString.length; i++) {
                array[i] = Double.parseDouble(arrayInString[i]);
            }
            System.out.println(Arrays.toString(array));
            return array;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Input should contain only numbers");

            alert.showAndWait();
            return null;
        }
    }

    private double[] shaker(double[] array, int count) {
        int left = 0;
        int right = count - 1;
        int lastReplacedElemIndex = right;
        double extraElement;
        iter = 0;

        do {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    extraElement = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = extraElement;
                    lastReplacedElemIndex = i;
                    iter ++;
                }
            }
            right = lastReplacedElemIndex;

            for (int i = right; i > left; i--) {
                if (array[i - 1] > array[i]) {
                    extraElement = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = extraElement;
                    lastReplacedElemIndex = i;
                    iter ++;
                }
            }
            left = lastReplacedElemIndex;


        } while (left < right);

        return array;
    }

}
