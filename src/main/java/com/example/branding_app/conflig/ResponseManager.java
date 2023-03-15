package com.example.branding_app.conflig;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseManager {
    public static ResponseEntity<String> sendSuccessResponse(Object o) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "success");
        jsonObject.add("data", gson.toJsonTree(o));
        return new ResponseEntity<>(jsonObject.toString(), headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> sendErrorResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "error");
        return new ResponseEntity<>(jsonObject.toString(), headers, HttpStatus.BAD_REQUEST);
    }
}
