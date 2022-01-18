package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class AnswerController extends QuizListController implements Initializable {
    QuizObject qz;
    private int item = 0;

    @FXML
    private Button redOption, blueOption, yellowOption, greenOption;

    @FXML
    private ImageView imageView;

    @FXML
    private Label userQuestion, score, topic; /* dito lalabas yung question, score, at topic ng user */

    @FXML
    void onClickQuit(ActionEvent event) throws IOException {
        setRoot("Main");
    }

    public void changeQuestion (int item) {
        try {
            qz = qc.getQuiz(clickedId);
            String correctAnswer = String.valueOf(qz.getQuestionList().get(item).getAnswer());
            userQuestion.setText(qz.getQuestionList().get(item).getQuestion());
            topic.setText(qz.getTopic());
            String[] choices = qz.getQuestionList().get(item).getChoices();
            redOption.setText(choices[0]);
            blueOption.setText(choices[1]);
            yellowOption.setText(choices[2]);
            greenOption.setText(choices[3]);
            try {
                imageView.setImage(new Image(qz.getQuestionList().get(item).getImageURL()));
                centerImage();
            } catch (Exception ignore) {}
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickRedOption(ActionEvent event) {
        try {
            qz = qc.getQuiz(clickedId);
            int currentScore = 0, weight = qz.getQuestionList().get(item).getPointWeight();
            String correctAnswer = String.valueOf(qz.getQuestionList().get(item).getAnswer());
            if (correctAnswer.equals("0")) {
                item++;
                changeQuestion(item);
                score.setText(String.valueOf(currentScore+weight));
            }
            else {
                System.out.println("Wrong");
                item++;
                changeQuestion(item);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickBlueOption(ActionEvent event) {
        try {
            qz = qc.getQuiz(clickedId);
            int currentScore = 0, weight = qz.getQuestionList().get(item).getPointWeight();
            String correctAnswer = String.valueOf(qz.getQuestionList().get(item).getAnswer());
            if (correctAnswer.equals("1")) {
                item++;
                changeQuestion(item);
                score.setText(String.valueOf(currentScore+weight));
            }
            else {
                System.out.println("Wrong");
                item++;
                changeQuestion(item);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickYellowOption(ActionEvent event) {
        try {
            qz = qc.getQuiz(clickedId);
            int currentScore = 0, weight = qz.getQuestionList().get(item).getPointWeight();
            String correctAnswer = String.valueOf(qz.getQuestionList().get(item).getAnswer());
            if (correctAnswer.equals("1")) {
                item++;
                changeQuestion(item);
                score.setText(String.valueOf(currentScore+weight));
            }
            else {
                System.out.println("Wrong");
                item++;
                changeQuestion(item);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickGreenOption(ActionEvent event) {
        try {
            qz = qc.getQuiz(clickedId);
            int currentScore = 0, weight = qz.getQuestionList().get(item).getPointWeight();
            String correctAnswer = String.valueOf(qz.getQuestionList().get(item).getAnswer());
            if (correctAnswer.equals("3")) {
                item++;
                changeQuestion(item);
                score.setText(String.valueOf(currentScore+weight));
            }
            else {
                System.out.println("Wrong");
                item++;
                changeQuestion(item);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            qz = qc.getQuiz(clickedId);
            userQuestion.setText(qz.getQuestionList().get(0).getQuestion());
            topic.setText(qz.getTopic());
            String[] choices = qz.getQuestionList().get(0).getChoices();
            redOption.setText(choices[0]);
            blueOption.setText(choices[1]);
            yellowOption.setText(choices[2]);
            greenOption.setText(choices[3]);
            try {
                imageView.setImage(new Image(qz.getQuestionList().get(0).getImageURL()));
                centerImage();
            } catch (Exception ignore) {

            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
}
