package com.example.branding_app.controller;


import com.example.branding_app.Utils.GlobalConstants;
import com.example.branding_app.conflig.ResponseManager;
import com.example.branding_app.model.User;
import com.example.branding_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = GlobalConstants.USER, method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@Validated @RequestBody User user) {
        Boolean flag = userService.saveUser(user);
        if (flag == true) {
            return ResponseManager.sendSuccessResponse();
        } else {
            return ResponseManager.sendErrorResponse();
        }
    }
}
