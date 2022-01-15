package com.dlsu.lbycpa2finalproject.backend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuizController {
    private Firestore db;

    public QuizController() {
        db = FirestoreClient.getFirestore();
    }

    public Hashtable<String, String> getTopics() throws ExecutionException, InterruptedException {
        Hashtable<String, String> hashtable = new Hashtable<>();

        ApiFuture<QuerySnapshot> query = db.collection("quizzes").get();
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document: documents) {
            hashtable.put(document.getId(), document.getString("topic"));
        }

        return hashtable;
    }

    public QuizObject getQuiz(String id) throws ExecutionException, InterruptedException {
        QuizObject quizObject = new QuizObject();
        List<QuestionObject> questionObjects = new ArrayList<>();
        String[] tempArray;
        QuestionObject temp;

        // Gets the topic and id of the quiz
        DocumentReference topicRef = db.collection("quizzes").document(id);
        ApiFuture<DocumentSnapshot> futureTopic = topicRef.get();
        DocumentSnapshot topic = futureTopic.get();
        quizObject.setTopic(topic.getString("topic"));
        quizObject.setId(topic.getId());

        // Gets the questions and the contents of each question as a QuestionObject
        ApiFuture<QuerySnapshot> query = db.collection("quizzes").document(id).collection("questions").get();
        QuerySnapshot questionQuery = query.get();
        List<QueryDocumentSnapshot> questions = questionQuery.getDocuments();

        for (QueryDocumentSnapshot question: questions) {
            temp = new QuestionObject();
            tempArray = new String[4];

            for(int i = 0; i < 4; i++) {
                tempArray[i] =  ((ArrayList<String>) question.get("choices")).get(i);
            }

            temp.setQuestion(question.getString("question"));
            temp.setChoices(tempArray);
            temp.setAnswer(Math.toIntExact(question.getLong("answer")));
            temp.setPointWeight(Math.toIntExact(question.getLong("weight")));
            temp.setImageURL(question.getString("imageURL"));

            questionObjects.add(temp);
        }

        quizObject.setQuestions(questionObjects);
        return quizObject;
    }

    public void addQuiz(QuizObject quiz) {

    }

}
