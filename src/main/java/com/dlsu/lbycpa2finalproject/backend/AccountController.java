package com.dlsu.lbycpa2finalproject.backend;

import com.dlsu.lbycpa2finalproject.LoginController;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class AccountController {
    private Firestore db;
    private int numAccounts;
    private static String userID;

    public AccountController() {
        db = FirestoreClient.getFirestore();
    }

    public int checkDetails(String user, String pass, boolean forLogin) throws ExecutionException, InterruptedException {
        Hashtable<String, String> hashtable = new Hashtable<>();
        ApiFuture<QuerySnapshot> query = db.collection("accounts").get();
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document: documents) {
            hashtable.put(document.getString("username"), document.getString("password"));
        }

        numAccounts = hashtable.size();

        if (forLogin && hashtable.containsKey(user) && hashtable.containsValue(pass)) {
            for (QueryDocumentSnapshot document: documents) {
                if (Objects.equals(document.getString("username"), user))
                    setUserID(document.getId());
            }
            return 1; //Affirm Login
        }
        if (hashtable.containsKey(user))
            return -1; //User already exists
        return 0; //User does not exist
    }

    public void registerAccount(String user, String pass) {

        Map<String, Object> account = new HashMap<>();
        account.put("username", user);
        account.put("password", pass);
        account.put("answersCorrect", 0);
        account.put("answersIncorrect", 0);
        account.put("quizzesAnswered", 0);

        String accountID = String.format("%04d", numAccounts + 1) + "-" + user.toLowerCase();
        db.collection("accounts").document(accountID).set(account);
        setUserID(accountID);
    }

    public Hashtable<String, Hashtable<String, Object>> getPlayerStatistics() throws ExecutionException, InterruptedException {
        Hashtable<String, Hashtable<String, Object>> hashtable = new Hashtable<>();
        Hashtable<String, Object> temp;
        ApiFuture<QuerySnapshot> query = db.collection("accounts").get();
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document: documents) {
            temp = new Hashtable<>();
            temp.put("username", document.getString("username"));
            temp.put("password", document.getString("password"));
            temp.put("answersCorrect", Math.toIntExact(document.getLong("answersCorrect")));
            temp.put("answersIncorrect", Math.toIntExact(document.getLong("answersIncorrect")));
            temp.put("quizzesAnswered", Math.toIntExact(document.getLong("quizzesAnswered")));

            hashtable.put(document.getId(), temp);
        }
        return hashtable;
    }

    public Hashtable<String, Object> getCurrentUserData(String userID) throws ExecutionException, InterruptedException {
        System.out.println(userID);
        return getPlayerStatistics().get(userID);
    }

    public void updateCurrentUserData(String userID, Hashtable<String, Object> data) {
        db.collection("accounts").document(userID).set(data);
    }

    private void setUserID(String userID) {
        AccountController.userID = userID;
    }

    public static String getUserID() {
        return userID;
    }

}
