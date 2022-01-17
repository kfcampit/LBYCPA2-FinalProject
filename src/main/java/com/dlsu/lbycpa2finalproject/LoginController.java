package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.AccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class LoginController {
    private AccountController ac = new AccountController();

    @FXML
    public TextField username, password_text;
    @FXML
    public CheckBox showPassword;
    @FXML
    public PasswordField password_hidden;
    @FXML
    public Text errorText;

    @FXML
    public void onLoginButtonClick() throws ExecutionException, InterruptedException {
        String pass = "";
        if (Objects.equals(username.getText(), "") || (Objects.equals(password_hidden.getText(), "") && Objects.equals(password_text.getText(), ""))) {
            errorText.setText("Please complete all the details");
            errorText.setVisible(true);
            return;
        } else if (Objects.equals(password_hidden.getText(), "")) {
            pass = password_text.getText();
        } else if (Objects.equals(password_text.getText(), "")) {
            pass = password_hidden.getText();
        }
        if (ac.checkDetails(username.getText(), pass, true) == 1) {
            System.out.println("Login Successful!");
        } else {
            errorText.setText("Wrong username or password");
            errorText.setVisible(true);
            password_text.clear();
        }
    }

    @FXML
    public void onRegisterButtonClick() throws ExecutionException, InterruptedException {
        String pass = "";
        if (Objects.equals(username.getText(), "") || (Objects.equals(password_hidden.getText(), "") && Objects.equals(password_text.getText(), ""))) {
            errorText.setText("Please complete all the details");
            errorText.setVisible(true);
            return;
        } else if (Objects.equals(password_hidden.getText(), "")) {
            pass = password_text.getText();
        } else if (Objects.equals(password_text.getText(), "")) {
            pass = password_hidden.getText();
        }
        if (ac.checkDetails(username.getText(), pass, false) == -1) {
            errorText.setText("Account already exists");
            errorText.setVisible(true);
            password_text.clear();
        } else {
            ac.registerAccount(username.getText(), password_hidden.getText());
            System.out.println("Registration Successful!");
        }
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
