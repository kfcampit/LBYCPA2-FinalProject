package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateController extends Main {
    public String topicStr;

    QuizController qc = new QuizController();
    QuestionObject temp = new QuestionObject();
    List<QuestionObject> qn = new ArrayList<>();
    QuizObject qz = new QuizObject();

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
    void onClickAdd(ActionEvent event) {
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
    }

    @FXML
    void onClickBack(ActionEvent event) {
        System.out.println("back");
    }

    @FXML
    void onClickSubmit(ActionEvent event) throws IOException {
        topicStr = topic.getText();
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

        qz.setID(String.format("%04d", id) + "-" + topic.getText().replaceAll(" ", "-").toLowerCase());
        id++;
        qz.setTopic(topic.getText());
        qz.setQuestions(qn);
        qc.manageQuiz(qz);
        topic.clear();
        clearScene();

        setRoot("Summary");
    }

    void clearScene(){
        errorMsg.toBack();
        choice1.clear();
        choice2.clear();
        choice3.clear();
        choice4.clear();
        correctAnswer.clear();
        inputQuestion.clear();
    }
}
