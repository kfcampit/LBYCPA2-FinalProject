package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.DatabaseController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        //launch();
        DatabaseController dc = new DatabaseController();
        QuizObject quizObject = dc.loadDatabase("sample-topic.csv");

        System.out.println(quizObject.getRandomQuestion().getQuestion());
        System.out.println(quizObject.getRandomQuestion().getQuestion());
    }
}