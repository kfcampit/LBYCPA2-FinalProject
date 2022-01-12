package com.dlsu.lbycpa2finalproject.backend;

import java.util.LinkedList;

public interface QuizInterface {
    void setQuestions(LinkedList<QuestionObject> questions);
    QuestionObject getNextQuestion();
    QuestionObject getRandomQuestion();
    int getNumberQuestions();
    int getTotalPoints();
}
