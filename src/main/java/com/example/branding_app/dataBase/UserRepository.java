package com.example.branding_app.dataBase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    public Boolean saveUser(Map<String, Object> data) {
        Boolean response = false;
        try {
            Firestore db = FirebaseConnection.initFireStoreDb().getFireStoreDB();
            db.collection("user").document(data.get("id").toString()).set(data).get();
            response = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> getUserById(String id) throws IOException, ExecutionException, InterruptedException {
        Firestore db = FirebaseConnection.initFireStoreDb().getFireStoreDB();
        DocumentReference docRef = db.collection("user").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = (DocumentSnapshot) future.get();
        if (document.exists()) {
            Map<String, Object> user = document.getData();
            return user;
        }
        return null;
    }
}
