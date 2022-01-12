package com.dlsu.lbycpa2finalproject.backend;

public interface QuestionInterface {
    void setQuestion(String question);
    String getQuestion();

    void setChoices(String[] choices);
    String[] getChoices();

    void setAnswer(int index);
    int getAnswer();

    void setPointWeight(int pointWeight);
    int getPointWeight();
}
