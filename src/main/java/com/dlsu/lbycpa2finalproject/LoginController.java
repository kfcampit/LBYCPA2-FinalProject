package com.dlsu.lbycpa2finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    public TextField username, password_text;
    @FXML
    public CheckBox showPassword;
    @FXML
    public PasswordField password_hidden;

    @FXML
    public void onLoginButtonClick() {

    }

    @FXML
    public void togglevisiblePassword(ActionEvent event) {
        if (showPassword.isSelected()) {
            password_text.setText(password_hidden.getText());
            password_text.setVisible(true);
            password_hidden.setVisible(false);
            return;
        }
        password_hidden.setText(password_text.getText());
        password_hidden.setVisible(true);
        password_text.setVisible(false);
    }
}
