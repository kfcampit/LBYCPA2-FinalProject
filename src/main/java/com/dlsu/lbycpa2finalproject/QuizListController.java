package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuizController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    int topicNumber = 0;

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
    void onClickGo(ActionEvent event) throws IOException {
        setRoot("Answer");
    }

    @FXML
    void onClickPrev(ActionEvent event) throws ExecutionException, InterruptedException {
        topicNumber -= 5;
        for (int i = 0; i < 5; i++) {
            if(topicNumber < keyList.size()){
                switch (i) {
                    case 0 -> displayTopic(topic5, topicNumber);
                    case 1 -> displayTopic(topic4, topicNumber);
                    case 2 -> displayTopic(topic3, topicNumber);
                    case 3 -> displayTopic(topic2, topicNumber);
                    default -> displayTopic(topic1, topicNumber);
                }
            }
            else{
                switch (i) {
                    case 0 -> topic5.setText("");
                    case 1 -> topic4.setText("");
                    case 2 -> topic3.setText("");
                    case 3 -> topic2.setText("");
                    default -> topic1.setText("");
                }
            }
            topicNumber--;
        }
        System.out.println(topicNumber);
        if(topicNumber < 0){
            System.out.println("ERROR");
        }
    }

    @FXML
    void onClickNext(ActionEvent event) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            if(topicNumber < keyList.size()){
                switch (i) {
                    case 0 -> displayTopic(topic1, topicNumber);
                    case 1 -> displayTopic(topic2, topicNumber);
                    case 2 -> displayTopic(topic3, topicNumber);
                    case 3 -> displayTopic(topic4, topicNumber);
                    default -> displayTopic(topic5, topicNumber);
                }
            }
            else{
                switch (i) {
                    case 0 -> topic1.setText("");
                    case 1 -> topic2.setText("");
                    case 2 -> topic3.setText("");
                    case 3 -> topic4.setText("");
                    default -> topic5.setText("");
                }
            }
            topicNumber++;
        }
        System.out.println(topicNumber);
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
            displayTopic(topic2,1);
            displayTopic(topic3,2);
            displayTopic(topic4,3);
            displayTopic(topic5,4);
            topicNumber = 5;
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    void displayTopic(Label topic, int n) throws ExecutionException, InterruptedException {
        topic.setText(qc.getTopics().get(keyList.get(n)) + " (" + keyList.get(n) + ")");
    }
}
