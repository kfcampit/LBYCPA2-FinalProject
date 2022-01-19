package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.AccountController;
import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class AnswerSummaryController extends AnswerController {
    QuestionObject qz;

    @FXML
    private Label finalScore, pass_message, fail_message;

    @FXML
    private ImageView bg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String userID = AccountController.getUserID();
        System.out.println(userID);

        AccountController ac = new AccountController();
        Hashtable<String, Object> userStats = new Hashtable<>();

        System.out.println(userStats);

        try {
            userStats = ac.getCurrentUserData(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(userStats);

        userStats.put("answersCorrect", (int) userStats.get("answersCorrect") + currentScore);
        userStats.put("answersIncorrect", (int) userStats.get("answersIncorrect") + (MAX_LENGTH - currentScore));
        userStats.put("quizzesAnswered", (int) userStats.get("quizzesAnswered") + 1);

        ac.updateCurrentUserData(userID, userStats);

        try {
            if (currentScore < (MAX_LENGTH/2.0)) {
                Image image = new Image ("file:src/main/resources/com/dlsu/lbycpa2finalproject/Quiztify_fail.gif");
                bg.setImage(image);
                fail_message.setVisible(true);
            }
            else {
                Image image = new Image ("file:src/main/resources/com/dlsu/lbycpa2finalproject/Quiztify_pass.gif");
                bg.setImage(image);
                pass_message.setVisible(true);
            }
            finalScore.setText(String.valueOf(currentScore));
            currentScore = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        setRoot("Main");
    }

}
