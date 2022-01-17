package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class SummaryController extends CreateController implements Initializable {
    private int questionNum = 0;
    private int MAX_LENGTH;
    private String quizID;
    private QuizObject qz;
    private QuizController qc = new QuizController();
    @FXML
    private Label errorPrev;

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
    private Button nextBtn;

    @FXML
    void onClickMainMenu(ActionEvent event) throws IOException {
        setRoot("Main");
    }

    @FXML
    void onClickNext(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        questionNum++;
        //errorPrev.toBack();
        if(questionNum < MAX_LENGTH-1) displayInfo(questionNum);
        else if(questionNum == MAX_LENGTH-1){
            nextBtn.setText("Done");
            displayInfo(questionNum);
        }
        else if(questionNum >= MAX_LENGTH){
            setRoot("Main");
        }
    }

    @FXML
    void onClickPrevious(ActionEvent event) throws ExecutionException, InterruptedException {
        nextBtn.setText("Next");
        questionNum--;
        if(questionNum<0) {
            //errorPrev.toFront();
            Alert alert = new Alert (Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("ERROR! This is the First Question.");
            alert.show();
            questionNum = 0;
        }
        else displayInfo(questionNum);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizID = CreateController.getQuizID();
        try {
            displayInfo(questionNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void displayInfo(int questionNum) throws ExecutionException, InterruptedException {
        qz = qc.getQuiz(quizID);
        displayTopic.setText(qz.getTopic());
        displayChoice1.setText(qz.getQuestionList().get(questionNum).getChoices()[0]);
        displayChoice2.setText(qz.getQuestionList().get(questionNum).getChoices()[1]);
        displayChoice3.setText(qz.getQuestionList().get(questionNum).getChoices()[2]);
        displayChoice4.setText(qz.getQuestionList().get(questionNum).getChoices()[3]);
        displayQuestion.setText(qz.getQuestionList().get(questionNum).getQuestion());
        displayAnswer.setText(qz.getQuestionList().get(questionNum).getChoices()[qz.getQuestionList().get(questionNum).getAnswer()]);
        MAX_LENGTH = qz.getNumberQuestions();
    }
}
