package com.example.branding_app.dataBase;

import com.example.branding_app.Utils.UserModelMapping;
import com.example.branding_app.model.User;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    public Boolean saveUser(User user) {
        Boolean response = false;
        try {
            Firestore db = FirebaseConnection.initFireStoreDb().getFireStoreDB();
            Map<String, Object> data = UserModelMapping.userMapping(user);
            db.collection("user").document(data.get("id").toString()).set(data).get();
            response = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return response;
    }
}
