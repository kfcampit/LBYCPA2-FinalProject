package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.AccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class LeaderboardController extends Main{
    private AccountController ac = new AccountController();

    @FXML
    private Label name1;

    @FXML
    private Button home;

    @FXML
    private Label score1;

    @FXML
    private Label name2;

    @FXML
    private Label score2;

    @FXML
    private Label name3;

    @FXML
    private Label score3;

    @FXML
    private Label name4;

    @FXML
    private Label score4;

    @FXML
    private Label name5;

    @FXML
    private Label score5;

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        //System.out.println("Back");
        setRoot("Main");
    }

    private List<String> sortHash(Hashtable<String, Hashtable<String, Object>> hashtable) {
        Hashtable<String, Integer> temp = new Hashtable<>();
        List<String> sorted = new ArrayList<>();
        String curr;

        Iterator<String> stringIterator = hashtable.keys().asIterator();

        while (stringIterator.hasNext()) {
            curr = stringIterator.next();
            System.out.println(curr);
            temp.put(curr, (Integer) hashtable.get(curr).get("answersCorrect"));
        }

        return sortValue(temp);
    }

    public ArrayList<String> sortValue(Hashtable<String, Integer> hash){
        ArrayList<String> sortedKeys = new ArrayList<>();

        //Transfer as List and sort it
        ArrayList<Map.Entry<String, Integer>> sorted = new ArrayList(hash.entrySet());
        Collections.sort(sorted, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }});

        for (int i = sorted.size() - 1; i >= 0; i--) {
            sortedKeys.add(sorted.get(i).getKey());
        }

        return sortedKeys;
    }

    public void initialize() throws ExecutionException, InterruptedException {
        Hashtable<String, Hashtable<String, Object>> playerStatistics = ac.getPlayerStatistics();
        System.out.println(playerStatistics);
        List<String> sorted = sortHash(playerStatistics);
        System.out.println(sorted.size());

        name1.setText(playerStatistics.get(sorted.get(0)).get("username").toString());
        name2.setText(playerStatistics.get(sorted.get(1)).get("username").toString());
        name3.setText(playerStatistics.get(sorted.get(2)).get("username").toString());
        name4.setText(playerStatistics.get(sorted.get(3)).get("username").toString());
        name5.setText(playerStatistics.get(sorted.get(4)).get("username").toString());

        score1.setText(playerStatistics.get(sorted.get(0)).get("answersCorrect").toString() + "/" + ((Integer) playerStatistics.get(sorted.get(0)).get("answersCorrect") + (Integer) playerStatistics.get(sorted.get(0)).get("answersIncorrect")) + " questions answered over " + playerStatistics.get(sorted.get(0)).get("quizzesAnswered").toString() + " quizzes.");
        score2.setText(playerStatistics.get(sorted.get(1)).get("answersCorrect").toString() + "/" + ((Integer) playerStatistics.get(sorted.get(1)).get("answersCorrect") + (Integer) playerStatistics.get(sorted.get(1)).get("answersIncorrect")) + " questions answered over " + playerStatistics.get(sorted.get(1)).get("quizzesAnswered").toString() + " quizzes.");
        score3.setText(playerStatistics.get(sorted.get(2)).get("answersCorrect").toString() + "/" + ((Integer) playerStatistics.get(sorted.get(2)).get("answersCorrect") + (Integer) playerStatistics.get(sorted.get(2)).get("answersIncorrect")) + " questions answered over " + playerStatistics.get(sorted.get(2)).get("quizzesAnswered").toString() + " quizzes.");
        score4.setText(playerStatistics.get(sorted.get(3)).get("answersCorrect").toString() + "/" + ((Integer) playerStatistics.get(sorted.get(3)).get("answersCorrect") + (Integer) playerStatistics.get(sorted.get(3)).get("answersIncorrect")) + " questions answered over " + playerStatistics.get(sorted.get(3)).get("quizzesAnswered").toString() + " quizzes.");
        score5.setText(playerStatistics.get(sorted.get(4)).get("answersCorrect").toString() + "/" + ((Integer) playerStatistics.get(sorted.get(4)).get("answersCorrect") + (Integer) playerStatistics.get(sorted.get(4)).get("answersIncorrect")) + " questions answered over " + playerStatistics.get(sorted.get(4)).get("quizzesAnswered").toString() + " quizzes.");
    }

}
