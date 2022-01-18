package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.ImageController;
import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateController extends Main {
    private static String quizID = "";

    QuizController qc = new QuizController();
    ImageController ic = new ImageController();
    QuestionObject temp;
    List<QuestionObject> qn = new ArrayList<>();
    QuizObject qz = new QuizObject();
    Alert alert = new Alert (Alert.AlertType.NONE);
    File selectedFile;

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

    @FXML
    private ImageView imageView;

    public boolean getInfo() {
        quizID = String.format("%04d", id) + "-" + topic.getText().replaceAll(" ", "-").toLowerCase();
        temp = new QuestionObject();
        String[] choices = {choice1.getText(), choice2.getText(), choice3.getText(), choice4.getText()};

        if (checkValidity(choices)) {
            temp.setChoices(choices);
            temp.setQuestion(inputQuestion.getText());
            temp.setPointWeight(1);

            //System.out.println("ImageView: " + imageView.getImage().getUrl().replaceAll("file:/", "").replaceAll("/", "\\"));
            System.out.println("Selected File: " + selectedFile);

            try {
                ic.saveImage(selectedFile, getQuizID() + "-q" + String.format("%04d", qn.size() + 1));
                temp.setImageURL(ic.loadImageURL(getQuizID() + "-q" + String.format("%04d", qn.size() + 1)));
            } catch (RuntimeException | IOException e) {
                temp.setImageURL("");
            }

            selectedFile = null;
            return true;
        }

        selectedFile = null;
        return false;
    }

    public boolean checkValidity(String[] choices) {
        List<String> choicesList = Arrays.asList(choices);

        if (!choicesList.contains(correctAnswer.getText())){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Correct Answer Not in Choices.");
            alert.show();
            return false;
            //errorMsg.toFront();
        }

        for (int i = 0; i < choices.length; i++) { /* Iterate sa choices var and ich-check if equal sa value ng correctAnswer var */
            if(correctAnswer.getText().equals(choices[i])){
                for (int j = 0; j < choices.length; j++) { /* Compare each element kung may mag-repeat na choice */
                    if (choices[i].equals(choices[j]) && i != j) {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("There Are Similar Choices.");
                        alert.show();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @FXML
    public void onClickAdd(ActionEvent event) throws IOException {
        if (getInfo()) {
            qn.add(temp);

            if (!Objects.equals(topic.getText(), ""))
                qz.setTopic(topic.getText());
            clearScene();
        }
    }

    @FXML
    void onClickSubmit(ActionEvent event) throws IOException {
        if(getInfo()) {
            qn.add(temp);
            qz.setID(quizID);
            id++;

            if (!Objects.equals(topic.getText(), ""))
                qz.setTopic(topic.getText());

            qz.setQuestions(qn);
            qc.manageQuiz(qz);
            topic.clear();
            clearScene();
            setRoot("Summary");
        }

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
        imageView.setImage(null);
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        setRoot("Main");
    }

    public static String getQuizID() {
        return quizID;
    }

    public void onAddImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        selectedFile = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());

        imageView.setImage(new Image(selectedFile.toURI().toString()));
        centerImage();

        System.out.println("addImage");
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
