package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class SummaryController extends CreateController implements Initializable {
    private int questionNum = 0;
    private String quizID;
    private QuizObject qz;
    private QuizController qc = new QuizController();
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
    void onClickNext(ActionEvent event) throws ExecutionException, InterruptedException {
        displayInfo(questionNum);
        questionNum++;
        System.out.println("next");
    }

    @FXML
    void onClickPrevious(ActionEvent event) throws ExecutionException, InterruptedException {
        displayInfo(questionNum);
        questionNum--;
        System.out.println("prev");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizID = CreateController.getQuizID();
        displayTopic.setText(topicStr);
        try {
            displayInfo(questionNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        questionNum++;
    }

    void displayInfo(int questionNum) throws ExecutionException, InterruptedException {
        qz = qc.getQuiz(quizID);
        displayChoice1.setText(qz.getQuestionList().get(questionNum).getChoices()[0]);
        displayChoice2.setText(qz.getQuestionList().get(questionNum).getChoices()[1]);
        displayChoice3.setText(qz.getQuestionList().get(questionNum).getChoices()[2]);
        displayChoice4.setText(qz.getQuestionList().get(questionNum).getChoices()[3]);
        displayQuestion.setText(qz.getQuestionList().get(questionNum).getQuestion());
        displayAnswer.setText(qz.getQuestionList().get(questionNum).getChoices()[qz.getQuestionList().get(questionNum).getAnswer()]);
    }
}
