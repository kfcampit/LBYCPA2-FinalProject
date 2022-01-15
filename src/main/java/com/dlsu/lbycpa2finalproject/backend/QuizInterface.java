package com.dlsu.lbycpa2finalproject.backend;

import java.util.LinkedList;
import java.util.List;

public interface QuizInterface {
    String getTopic();
    void setTopic(String topic);
    void setQuestions(List<QuestionObject> questions);
    QuestionObject getNextQuestion();
    QuestionObject getRandomQuestion();
    int getNumberQuestions();
    int getTotalPoints();
}
