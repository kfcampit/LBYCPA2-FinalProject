package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.ImageController;
import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class EditController extends EditQuizListController implements Initializable {
    QuizObject qz;
    QuestionObject temp;
    List<QuestionObject> qn = new ArrayList<>();
    ImageController ic = new ImageController();
    File selectedFile;
    int qNumber = 0; /* question number */

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea topic;

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
    private int MAX_LENGTH;

    public EditController() throws ExecutionException, InterruptedException {
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        setRoot("Main");
    }

    @FXML
    void onClickNext(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        if (getInfo()) {
            qn.add(temp);
            saveEdit(qNumber);
            if (!Objects.equals(topic.getText(), ""))
                qz.setTopic(topic.getText());
        }

        qNumber++;
        if(qNumber < MAX_LENGTH-1) displayQuestion(qNumber);
        else if(qNumber == MAX_LENGTH-1){
            next.setText("Done");
            displayQuestion(qNumber);
        }
        else if(qNumber >= MAX_LENGTH){
            setRoot("Main");
        }
    }

    @FXML
    void onClickPrev(ActionEvent event) throws ExecutionException, InterruptedException {
        next.setText("Next");
        qNumber--;
        if(qNumber<0) {
            //errorPrev.toFront();
            Alert alert = new Alert (Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("ERROR! This is the First Question.");
            alert.show();
            qNumber = 0;
        }
        else displayQuestion(qNumber);
    }

    public void onAddImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        selectedFile = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());

        imageView.setImage(new Image(selectedFile.toURI().toString()));
        centerImage();

        System.out.println("addImage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayQuestion(qNumber);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void displayQuestion(int questionNumber) throws ExecutionException, InterruptedException {
        qz = qc.getQuiz(clickedId);
        topic.setText(qz.getTopic());
        inputQuestion.setText(qz.getQuestionList().get(questionNumber).getQuestion());
        choice1.setText(qz.getQuestionList().get(questionNumber).getChoices()[0]);
        choice2.setText(qz.getQuestionList().get(questionNumber).getChoices()[1]);
        choice3.setText(qz.getQuestionList().get(questionNumber).getChoices()[2]);
        choice4.setText(qz.getQuestionList().get(questionNumber).getChoices()[3]);
        int ansIndex = qz.getQuestionList().get(questionNumber).getAnswer();
        correctAnswer.setText(qz.getQuestionList().get(questionNumber).getChoices()[ansIndex]);
        try {
            imageView.setImage(new Image(qz.getQuestionList().get(questionNumber).getImageURL()));
            centerImage();
        } catch (Exception ignore) {

        }
        MAX_LENGTH = qz.getNumberQuestions();
    }

    void saveEdit(int questionNumber){
        qz.getQuestionList().get(questionNumber).setQuestion(temp.getQuestion());
        qz.getQuestionList().get(questionNumber).setChoices(temp.getChoices());
        qz.getQuestionList().get(questionNumber).setAnswer(temp.getAnswer());
        qz.getQuestionList().get(questionNumber).setPointWeight(temp.getPointWeight());
        qz.getQuestionList().get(questionNumber).setImageURL(temp.getImageURL());
    }

    public boolean getInfo() {
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

    public static String getQuizID() {
        return clickedId;
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
                temp.setAnswer(i);
                return true;
            }
        }
        return true;
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
