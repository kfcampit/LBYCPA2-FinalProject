package com.dlsu.lbycpa2finalproject.backend;

import java.util.LinkedList;
import java.util.Random;

public class QuizObject implements QuizInterface{
    private LinkedList<QuestionObject> questions;
    private int numberQuestions;
    private int totalPoints;

    @Override
    public void setQuestions(LinkedList<QuestionObject> questions) {
        this.questions = questions;
        numberQuestions = questions.size();
        totalPoints = 0;
        for (QuestionObject question: questions)
            totalPoints += question.getPointWeight();
    }

    @Override
    public QuestionObject getNextQuestion() {
        QuestionObject nextQ = questions.getFirst();
        questions.removeFirst();
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
