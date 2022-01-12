package com.dlsu.lbycpa2finalproject.backend;

public class QuestionObject implements QuestionInterface{
    private String question;
    private String[] choices;
    private int answerIndex;


    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    @Override
    public String[] getChoices() {
        return choices;
    }

    @Override
    public void setAnswer(int index) {
        answerIndex = index;
    }

    @Override
    public int getAnswer() {
        return answerIndex;
    }
}
