package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateController {

    @FXML
    private TextField choice1;

    @FXML
    private TextArea inputQuestion;

    @FXML
    private TextField choice2;

    @FXML
    private TextField choice3;

    @FXML
    private TextField choice4;

    @FXML
    private TextField correctAnswer;

    @FXML
    private Button submit;

    @FXML
    private Button back;

    @FXML
    private Button add;

    @FXML
    void onClickAdd(ActionEvent event) {

    }

    @FXML
    void onClickBack(ActionEvent event) {
        System.out.println("back");
    }

    @FXML
    void onClickSubmit(ActionEvent event) {
        System.out.println("submit");
    }

}
