package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class EditController extends EditQuizListController implements Initializable {
    QuizObject qz;
    int qNumber = 0; /* question number */

    @FXML
    private ImageView imageView;

    @FXML
    private Label topic;

    @FXML
    private TextField choice1;

    @FXML
    private TextArea inputQuestion;

    @FXML
    private TextField choice2;

    @FXML
    private TextField choice3;

    @FXML
    private TextField choice4;

    @FXML
    private TextField correctAnswer;

    @FXML
    private Button next;

    @FXML
    private Button prev;

    @FXML
    private Button home;

    @FXML
    private TextField qID;

    @FXML
    private Button search;

    public EditController() throws ExecutionException, InterruptedException {
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        //System.out.println("back");
        setRoot("Main");
    }

    @FXML
    void onClickNext(ActionEvent event) {
        System.out.println("next");
    }

    @FXML
    void onClickPrev(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            qz = qc.getQuiz(clickedId);
//            topic.setText(qz.getTopic());
//            inputQuestion.setText(qz.getQuestionList().get(0).getQuestion());
//            choice1.setText(qz.getQuestionList().get(0).getChoices()[0]);
//            choice2.setText(qz.getQuestionList().get(0).getChoices()[1]);
//            choice3.setText(qz.getQuestionList().get(0).getChoices()[2]);
//            choice4.setText(qz.getQuestionList().get(0).getChoices()[3]);
//            int ansIndex = qz.getQuestionList().get(0).getAnswer();
//            correctAnswer.setText(qz.getQuestionList().get(0).getChoices()[ansIndex]);
//            try {
//                imageView.setImage(new Image(qz.getQuestionList().get(0).getImageURL()));
//                centerImage();
//            } catch (Exception ignore) {
//
//            }
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
