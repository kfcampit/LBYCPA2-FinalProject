package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class AnswerSummaryController extends AnswerController {
    QuestionObject qz;

    @FXML
    private Label finalScore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            finalScore.setText(String.valueOf(currentScore));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        setRoot("Main");
    }

}
