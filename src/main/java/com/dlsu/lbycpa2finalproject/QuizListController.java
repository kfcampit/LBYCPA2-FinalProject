package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class QuizListController extends Main implements Initializable {
    ArrayList<String> keyList = new ArrayList<>(); /* Dito is-store mga keys na nasa database */
    QuizController qc = new QuizController();
    Alert alert = new Alert (Alert.AlertType.NONE);
    int topicNumber = 0;
    public String id1, id2, id3, id4 = "", id5, clickedId;

    @FXML
    private Label topic1;

    @FXML
    private Label topic2;

    @FXML
    private Label topic3;

    @FXML
    private Label topic4;

    @FXML
    private Label topic5;

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        setRoot("Main");
    }
    @FXML
    void onClickGo1(ActionEvent event) throws IOException {
        clickedId = id1;
        System.out.println(clickedId);
        setRoot("Answer");
    }

    @FXML
    void onClickGo2(ActionEvent event) throws IOException {
        clickedId = id2;
        System.out.println(clickedId);
        setRoot("Answer");
    }

    @FXML
    void onClickGo3(ActionEvent event) throws IOException {
        clickedId = id3;
        System.out.println(clickedId);
        setRoot("Answer");
    }

    @FXML
    void onClickGo4(ActionEvent event) throws IOException {
        clickedId = id4;
        System.out.println(clickedId);
        setRoot("Answer");
    }

    @FXML
    void onClickGo5(ActionEvent event) throws IOException {
        clickedId = id5;
        System.out.println(clickedId);
        setRoot("Answer");
    }

    @FXML
    void onClickPrev(ActionEvent event) throws ExecutionException, InterruptedException {
        int temp = topicNumber; /* Pangcheck lang to for error */
        temp -= 10;
        if(temp < 0){
            //System.out.println("ERROR! This is the First Set of Quizzes");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("This is the Last Set of Quizzes.");
            alert.show();
            return;
        }

        topicNumber -= 10;
        for (int i = 0; i < 5; i++) {
            if(topicNumber < keyList.size()){
                switch (i) {
                    case 0 -> {
                        displayTopic(topic1, topicNumber);
                        id1 = keyList.get(topicNumber);
                    }
                    case 1 -> {
                        displayTopic(topic2, topicNumber);
                        id2 = keyList.get(topicNumber);
                    }
                    case 2 -> {
                        displayTopic(topic3, topicNumber);
                        id3 = keyList.get(topicNumber);
                    }
                    case 3 -> {
                        displayTopic(topic4, topicNumber);
                        id4 = keyList.get(topicNumber);
                    }
                    default -> {
                        displayTopic(topic5, topicNumber);
                        id5 = keyList.get(topicNumber);
                    }
                }
            }
            else{
                switch (i) {
                    case 0 -> {
                        topic1.setText("");
                        id1 = "";
                    }
                    case 1 -> {
                        topic2.setText("");
                        id2 = "";
                    }
                    case 2 -> {
                        topic3.setText("");
                        id3 = "";
                    }
                    case 3 -> {
                        topic4.setText("");
                        id4 = "";
                    }
                    default -> {
                        topic5.setText("");
                        id5 = "";
                    }
                }
            }
            topicNumber++;
        }
    }

    @FXML
    void onClickNext(ActionEvent event) throws ExecutionException, InterruptedException {
        if(topicNumber > keyList.size()){
            //System.out.println("ERROR! This is the Last Set of Quizzes");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("This is the Last Set of Quizzes.");
            alert.show();
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (topicNumber < keyList.size()) {
                switch (i) {
                    case 0 -> {
                        displayTopic(topic1, topicNumber);
                        id1 = keyList.get(topicNumber);
                    }
                    case 1 -> {
                        displayTopic(topic2, topicNumber);
                        id2 = keyList.get(topicNumber);
                    }
                    case 2 -> {
                        displayTopic(topic3, topicNumber);
                        id3 = keyList.get(topicNumber);
                    }
                    case 3 -> {
                        displayTopic(topic4, topicNumber);
                        id4 = keyList.get(topicNumber);
                    }
                    default -> {
                        displayTopic(topic5, topicNumber);
                        id5 = keyList.get(topicNumber);
                    }
                }
            } else {
                switch (i) {
                    case 0 -> {
                        topic1.setText("");
                        id1 = "";
                    }
                    case 1 -> {
                        topic2.setText("");
                        id2 = "";
                    }
                    case 2 -> {
                        topic3.setText("");
                        id3 = "";
                    }
                    case 3 -> {
                        topic4.setText("");
                        id4 = "";
                    }
                    default -> {
                        topic5.setText("");
                        id5 = "";
                    }
                }
            }
            topicNumber++;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Enumeration<String> e = qc.getTopics().keys(); /* Ilalagay yung enumeration ng keys sa e var */
            while(e.hasMoreElements()){ /* Ilalagay yung bawat element ng enumeration sa arrayList para mas madali maaccess */
                keyList.add(e.nextElement());
            }
            Collections.sort(keyList); /* Pang-sort lang ng arrayList since random yung order ng elements */

            /* Ididisplay na sa GUI yung topics at id */
            displayTopic(topic1,0);
            id1 = keyList.get(0);
            displayTopic(topic2,1);
            id2 = keyList.get(1);
            displayTopic(topic3,2);
            id3 = keyList.get(2);
            displayTopic(topic4,3);
            id4 = keyList.get(3);
            displayTopic(topic5,4);
            id5 = keyList.get(4);
            topicNumber = 5;
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    void displayTopic(Label topic, int n) throws ExecutionException, InterruptedException {
        topic.setText(qc.getTopics().get(keyList.get(n)) + " (" + keyList.get(n) + ")");
    }
}
