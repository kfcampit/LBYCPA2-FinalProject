package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class AnswerController extends QuizListController implements Initializable {
    QuizObject qz;

    @FXML
    private Button redOption;

    @FXML
    private Button blueOption;

    @FXML
    private Button yellowOption;

    @FXML
    private Button greenOption;

    @FXML
    private Label userQuestion; /* dito lalabas yung question ng user */

    @FXML
    void onClickBlueOption(ActionEvent event) {

    }

    @FXML
    void onClickGreenOption(ActionEvent event) {

    }

    @FXML
    void onClickRedOption(ActionEvent event) {

    }

    @FXML
    void onClickYellowOption(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            qz = qc.getQuiz(clickedId);
            userQuestion.setText(qz.getQuestionList().get(0).getQuestion());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
