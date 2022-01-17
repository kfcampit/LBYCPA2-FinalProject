package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class LeaderboardController extends Main{

    @FXML
    private Label name1;

    @FXML
    private Button back;

    @FXML
    private Label score1;

    @FXML
    private Label name2;

    @FXML
    private Label score2;

    @FXML
    private Label name3;

    @FXML
    private Label score3;

    @FXML
    private Label name4;

    @FXML
    private Label score4;

    @FXML
    private Label name5;

    @FXML
    private Label score5;

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        //System.out.println("Back");
        setRoot("Main");
    }

}
