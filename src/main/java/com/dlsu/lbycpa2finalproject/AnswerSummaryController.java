package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
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
        try {
            if (currentScore < (MAX_LENGTH/2)) {
                //bg.setImage(new Image("Quiztify_fail.gif"));
                fail_message.setVisible(true);
            }
            if (currentScore > (MAX_LENGTH/2)) {
                //bg.setImage(new Image("Quiztify_pass.gif"));
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
