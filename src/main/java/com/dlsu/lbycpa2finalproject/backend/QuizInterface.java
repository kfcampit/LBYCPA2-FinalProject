package com.dlsu.lbycpa2finalproject.backend;

import java.util.LinkedList;
import java.util.List;

public interface QuizInterface {
    String getTopic();
    void setTopic(String topic);
    String getID();
    void setID(String id);
    void setQuestions(List<QuestionObject> questions);
    QuestionObject getNextQuestion();
    QuestionObject getRandomQuestion();
    List<QuestionObject> getQuestionList();
    int getNumberQuestions();
    int getTotalPoints();
}
