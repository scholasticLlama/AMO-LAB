package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class HelloController {
    public TextField arrayInString;
    public Label arrayLabel;
    public Button downloadButton;
    public MenuBar menu;
    public Menu mainInfo;
    public Menu task;
    public Pane pane;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("lol1");
    }

    @FXML
    protected void onMainInfoMenuClick() {
        //pane.getChildren().removeAll();
        System.out.println("lol2");
    }

    @FXML
    protected void onTaskMenuClick() {
        System.out.println("lol3");
    }
}