package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class QuizListController extends Main {
    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        setRoot("Main");
    }
    @FXML
    void onClickGo(ActionEvent event) throws IOException {
        setRoot("Answer");
    }
}
