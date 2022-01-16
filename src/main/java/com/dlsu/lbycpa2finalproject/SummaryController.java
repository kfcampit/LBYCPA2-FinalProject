package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryController extends CreateController implements Initializable {
    int questionNum = 0;
    @FXML
    private TextField displayChoice1;

    @FXML
    private TextArea displayQuestion;

    @FXML
    private TextField displayChoice2;

    @FXML
    private TextField displayChoice3;

    @FXML
    private TextField displayChoice4;

    @FXML
    private TextField displayAnswer;

    @FXML
    private TextArea displayTopic;

    @FXML
    void onClickMainMenu(ActionEvent event) {
        System.out.println("main");
    }

    @FXML
    void onClickNext(ActionEvent event) {
        displayInfo(questionNum);
        questionNum++;
        System.out.println("next");
    }

    @FXML
    void onClickPrevious(ActionEvent event) {
        displayInfo(questionNum);
        questionNum--;
        System.out.println("prev");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTopic.setText(topicStr);
        displayInfo(questionNum);
        questionNum++;
    }

    void displayInfo(int questionNum){
        displayChoice1.setText(qz.getQuestionList().get(questionNum).getChoices()[0]);
        displayChoice2.setText(qz.getQuestionList().get(questionNum).getChoices()[1]);
        displayChoice3.setText(qz.getQuestionList().get(questionNum).getChoices()[2]);
        displayChoice4.setText(qz.getQuestionList().get(questionNum).getChoices()[3]);
        displayQuestion.setText(qz.getQuestionList().get(questionNum).getQuestion());
        displayAnswer.setText(qz.getQuestionList().get(questionNum).getChoices()[qz.getQuestionList().get(questionNum).getAnswer()]);
    }
}
