package com.example.branding_app.controller;


import com.example.branding_app.Utils.GlobalConstants;
import com.example.branding_app.Utils.Response;
import com.example.branding_app.conflig.ResponseManager;
import com.example.branding_app.dto.UserDto;
import com.example.branding_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = GlobalConstants.USER, method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@Validated @RequestBody UserDto user) {
        Response<UserDto> response = userService.saveUser(user);
        if (response.getCode().getState()) {
            return ResponseManager.sendSuccessResponse(response.getData());
        } else {
            return ResponseManager.sendErrorResponse();
        }
    }

    @RequestMapping(value = GlobalConstants.USER, method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@Validated @RequestBody UserDto userDto) throws IOException, ExecutionException, InterruptedException {
        Response<UserDto> response = userService.updateUser(userDto);
        if (response.getCode().getState()) {
            return ResponseManager.sendSuccessResponse(response.getCode());
        } else {
            return ResponseManager.sendErrorResponse();
        }
    }
}
