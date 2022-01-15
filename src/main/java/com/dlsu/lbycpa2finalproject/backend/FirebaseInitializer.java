package com.dlsu.lbycpa2finalproject.backend;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class FirebaseInitializer {
    public FirebaseInitializer() throws IOException, ExecutionException, InterruptedException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("ServiceAccountKey.json")))
                .build();

        FirebaseApp.initializeApp(options);

        /*
        ApiFuture<QuerySnapshot> query = db.collection("quizzes").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document: documents) {
            System.out.println(document.getId());
            System.out.println(document.getString("topic"));
        }


        DocumentReference docRef = db.collection("quizzes").document("0001-basic-math").collection("questions").document("q001");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        System.out.println(document.getString("question"));
        */
    }
}
