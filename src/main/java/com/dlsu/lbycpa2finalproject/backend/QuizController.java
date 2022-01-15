package com.dlsu.lbycpa2finalproject.backend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.util.*;
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

        // Gets the topic of the quiz
        DocumentReference topicRef = db.collection("quizzes").document(id);
        ApiFuture<DocumentSnapshot> futureTopic = topicRef.get();
        DocumentSnapshot topic = futureTopic.get();
        quizObject.setTopic(topic.getString("topic"));
        quizObject.setID(topic.getId());

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

    public void manageQuiz(QuizObject quizObject) {
        String quizID = quizObject.getID();
        System.out.println(quizID);
        removeQuiz(quizID);


        List<QuestionObject> quizQuestions = quizObject.getQuestionList();
        Map<String, Object> questions;
        List<Map<String, Object>> listQuestions = new ArrayList<>();
        for (QuestionObject question: quizQuestions) {
            questions = new HashMap<>();
            questions.put("question", question.getQuestion());
            questions.put("choices", Arrays.asList(question.getChoices()));
            questions.put("answer", question.getAnswer());
            questions.put("weight", question.getPointWeight());
            questions.put("imageURL", question.getImageURL());
            listQuestions.add(questions);
        }

        Map<String, Object> quizTopic = new HashMap<>();
        quizTopic.put("topic",quizObject.getTopic());

        db.collection("quizzes").document(quizID).set(quizTopic);

        int i = 1;
        for (Map<String, Object> question: listQuestions) {
            db.collection("quizzes").document(quizID).collection("questions").document("q" + String.format("%04d", i)).set(question);
            i++;
        }
    }

    public void removeQuiz(String quizID) {
        deleteCollection(db.collection("quizzes").document(quizID).collection("questions"));
        db.collection("quizzes").document(quizID).delete();
    }

    void deleteCollection(CollectionReference collection) {
        try {
            ApiFuture<QuerySnapshot> future = collection.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
            }
        } catch (Exception e) {
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }

}
