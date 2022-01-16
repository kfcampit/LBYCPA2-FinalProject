package com.dlsu.lbycpa2finalproject;

import com.dlsu.lbycpa2finalproject.backend.FirebaseInitializer;
import com.dlsu.lbycpa2finalproject.backend.QuestionObject;
import com.dlsu.lbycpa2finalproject.backend.QuizController;
import com.dlsu.lbycpa2finalproject.backend.QuizObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main extends Application {
    private static Scene s;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Create.fxml"));
        s = new Scene(fxmlLoader.load(), 643, 689);
        stage.setTitle("Welcome to Quiztify!");
        stage.setScene(s);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException{ /* Eto gagamitin pang-change scene */
        s.setRoot(loadFXML(fxml));
    }
    public static void closeRoot() throws IOException { /* Para magclose yung window */
        Platform.exit();
    }
    private static Parent loadFXML(String fxml) throws IOException{ /* Eto magl-load ng fxml para mabuksan */
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        new FirebaseInitializer();

        /*
        QuizController qc = new QuizController();       //Outputs:
        System.out.println(qc.getTopics());             //{0001-basic-math=Basic Mathematics, 0002-basic-physics=Basic Physics}

        QuizObject quiz = qc.getQuiz("0001-basic-math");
        quiz.setTopic("Basic Math");

        qc.manageQuiz(quiz);

        QuizObject qo;
        qo = qc.getQuiz("0001-basic-math");

        QuestionObject nextQ = qo.getNextQuestion();

        System.out.println(qo.getTopic());              //Basic Mathematics
        System.out.println(nextQ.getQuestion());        //What is 1+1?
        System.out.println(nextQ.getChoices()[0]);      //One
        System.out.println(nextQ.getChoices()[1]);      //Three
        System.out.println(nextQ.getChoices()[2]);      //Four
        System.out.println(nextQ.getChoices()[3]);      //Two
        System.out.println(nextQ.getAnswer());          //3 - Index of Correct Answer
        System.out.println(nextQ.getPointWeight());     //5 - Points for the question
        */


        launch();
    }
}