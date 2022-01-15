package com.dlsu.lbycpa2finalproject.backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuizObject implements QuizInterface {
    private String id;
    private String topic;
    private String imageURL;
    private List<QuestionObject> questions;
    private int numberQuestions;
    private int totalPoints;

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setQuestions(List<QuestionObject> questions) {
        this.questions = questions;
        numberQuestions = questions.size();
        totalPoints = 0;
        for (QuestionObject question: questions)
            totalPoints += question.getPointWeight();
    }

    @Override
    public QuestionObject getNextQuestion() {
        QuestionObject nextQ = questions.get(0);
        questions.remove(0);
        return nextQ;
    }

    @Override
    public QuestionObject getRandomQuestion() {
        Random rand = new Random();
        int randIndex = rand.nextInt(numberQuestions);
        QuestionObject nextQ = questions.get(randIndex);
        questions.remove(randIndex);
        return nextQ;
    }

    @Override
    public int getNumberQuestions() {
        return numberQuestions;
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }
}
