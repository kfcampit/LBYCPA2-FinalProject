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
    private Button EditQuiz;
    @FXML
    private Button Leaderboards;

    @FXML
    void onClickAddQuiz(ActionEvent event) throws IOException {
//        System.out.println("Add");
        setRoot("Create");
    }
    @FXML
    void onClickEditQuiz(ActionEvent event) throws IOException {
//        System.out.println("Delete");
        setRoot("EditQuizList");
    }
    @FXML
    void onClickLeaderboards(ActionEvent event) throws IOException {
//        System.out.println("Leader");
        setRoot("Leaderboard");
    }
    @FXML
    void onClickLoadQuiz(ActionEvent event) throws IOException {
//        System.out.println("Load");
        setRoot("QuizList");
    }
}