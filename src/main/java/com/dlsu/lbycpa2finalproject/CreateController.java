package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateController extends Main {
    private static String quizID = "";

    QuizController qc = new QuizController();
    QuestionObject temp;
    List<QuestionObject> qn = new ArrayList<>();
    QuizObject qz = new QuizObject();
    Alert alert = new Alert (Alert.AlertType.NONE);

    @FXML
    private Label errorSimilar;

    @FXML
    private Label errorMsg;

    @FXML
    private TextField choice1;

    @FXML
    private TextArea inputQuestion;

    @FXML
    private TextArea topic;

    @FXML
    private TextField choice2;

    @FXML
    private TextField choice3;

    @FXML
    private TextField choice4;

    @FXML
    private TextField correctAnswer;

    public CreateController() {
    }

    @FXML
    public void onClickAdd(ActionEvent event) {
        QuestionObject temp = new QuestionObject();
        boolean canProceed = false; /* Kung pwede na iadd yung question */
        String[] choices = {choice1.getText(), choice2.getText(), choice3.getText(), choice4.getText()};
        temp.setChoices(choices);
        temp.setQuestion(inputQuestion.getText());
        temp.setPointWeight(1);
        for (int i = 0; i < choices.length; i++) { /* Iterate sa choices var and ich-check if equal sa value ng correctAnswer var */
            if(correctAnswer.getText().equals(choice1.getText()) || correctAnswer.getText().equals(choice2.getText()) || correctAnswer.getText().equals(choice3.getText()) || correctAnswer.getText().equals(choice4.getText())){
                canProceed = true;
                temp.setAnswer(i);
                continue;
            }
            else if(!correctAnswer.getText().equals(choice1.getText()) || !correctAnswer.getText().equals(choice2.getText()) || !correctAnswer.getText().equals(choice3.getText()) || !correctAnswer.getText().equals(choice4.getText())){
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Correct Answer Not in Choices.");
                alert.show();
                canProceed = false;
                //errorMsg.toFront();
            }
            for (int j = 0; j < choices.length; j++) { /* Compare each element kung may mag-repeat na choice */
                if(choices[i].equals(choices[j]) && i!=j) {
                    canProceed = false;
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("There Are Similar Choices.");
                    alert.show();
                    //errorSimilar.toFront();
                    break;
                }
            }

        }
        if(canProceed) {
            qn.add(temp);
            clearScene();
        }
    }

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        //System.out.println("back");
        setRoot("Main");
    }

    @FXML
    void onClickSubmit(ActionEvent event) throws IOException {
        QuestionObject temp = new QuestionObject();
        boolean found = false;
        String[] choices = {choice1.getText(), choice2.getText(), choice3.getText(), choice4.getText()};
        temp.setChoices(choices);
        temp.setQuestion(inputQuestion.getText());
        temp.setPointWeight(1);
        for (int i = 0; i < choices.length; i++) { /* Iterate sa choices var and ich-check if equal sa value ng correctAnswer var */
            if(choices[i].equals(correctAnswer.getText())) {
                temp.setAnswer(i);
                found = true;
            }
        }
        if(found) {
            qn.add(temp);
            clearScene();
        }
        else{
            errorMsg.toFront();
        }

        quizID = String.format("%04d", id) + "-" + topic.getText().replaceAll(" ", "-").toLowerCase();
        qz.setID(quizID);
        id++;
        qz.setTopic(topic.getText());
        qz.setQuestions(qn);
        qc.manageQuiz(qz);
        topic.clear();
        clearScene();



        setRoot("Summary");
    }

    void clearScene(){
        errorSimilar.toBack();
        errorMsg.toBack();
        choice1.clear();
        choice2.clear();
        choice3.clear();
        choice4.clear();
        correctAnswer.clear();
        inputQuestion.clear();
    }

    public static String getQuizID() {
        return quizID;
    }
}
