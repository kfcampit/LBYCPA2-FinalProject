package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController extends Main{

    @FXML
    private Button AddQuiz;
    @FXML
    private Button LoadQuiz;
    @FXML
    private Button DeleteQuiz;
    @FXML
    private Button Leaderboards;

    @FXML
    void onClickAddQuiz(ActionEvent event) {
//        System.out.println("Add");
    }
    @FXML
    void onClickDeleteQuiz(ActionEvent event) {
//        System.out.println("Delete");
    }
    @FXML
    void onClickLeaderboards(ActionEvent event) {
//        System.out.println("Leader");
    }
    @FXML
    void onClickLoadQuiz(ActionEvent event) throws IOException {
//        System.out.println("Load");
        setRoot("Answer");
    }
}