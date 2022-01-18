package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.ImageController;
import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageController ic = new ImageController();

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
    private ImageView imageView;

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
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
        try {
            imageView.setImage(new Image(qz.getQuestionList().get(questionNum).getImageURL()));
            centerImage();
        } catch (Exception ignore) {
            imageView.setImage(null);
        }

        MAX_LENGTH = qz.getNumberQuestions();
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
