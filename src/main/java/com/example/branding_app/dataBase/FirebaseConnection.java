package com.example.branding_app.dataBase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConnection {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConnection.class);

    private static Firestore db = null;

    private static FirebaseConnection fireStoreDB = null;

    public FirebaseConnection() {
    }

    public static FirebaseConnection initFireStoreDb() {

        if (fireStoreDB == null) {
            fireStoreDB = new FirebaseConnection();
        }
        return fireStoreDB;
    }

    public Firestore getFireStoreDB() throws IOException {

        if (FirebaseConnection.db == null) {

            String PROJECT_ID = "branding-app-144c5";
            String FIRESTORE_CREDS = "branding_app.json";
            FileInputStream serviceAccount = new FileInputStream(FIRESTORE_CREDS);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirestoreOptions options = FirestoreOptions.newBuilder().setCredentials(credentials).setProjectId(PROJECT_ID).build();

            FirebaseConnection.db = options.getService();
            logger.info("FireStore Initiated!");

            serviceAccount.close();
        }
        return FirebaseConnection.db;
    }
}

