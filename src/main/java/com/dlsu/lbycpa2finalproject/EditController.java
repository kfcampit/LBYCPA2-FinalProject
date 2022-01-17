package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditController extends Main{

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
    private Button edit;

    @FXML
    private Button back;

    @FXML
    private TextField qID;

    @FXML
    private Button search;

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        //System.out.println("back");
        setRoot("Main");
    }

    @FXML
    void onClickEdit(ActionEvent event) {
        System.out.println("edit");
    }

    @FXML
    void onClickSearch(ActionEvent event) {
        System.out.println("search");
    }

}
