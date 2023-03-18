package com.example.branding_app.controller;


import com.example.branding_app.Utils.GlobalConstants;
import com.example.branding_app.Utils.Response;
import com.example.branding_app.conflig.ResponseManager;
import com.example.branding_app.dto.UserDto;
import com.example.branding_app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = GlobalConstants.USER, method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@Validated @RequestBody UserDto user) {
        logger.info("Post request received!");
        Response<UserDto> response = userService.saveUser(user);
        if (response.getCode().getState()) {
            return ResponseManager.sendSuccessResponse(response.getData());
        } else {
            return ResponseManager.sendErrorResponse();
        }
    }

    @RequestMapping(value = GlobalConstants.USER, method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@Validated @RequestBody UserDto userDto) throws IOException, ExecutionException, InterruptedException {
        logger.info("Update request received for userId: " + userDto.getId());
        Response<UserDto> response = userService.updateUser(userDto);
        if (response.getCode().getState()) {
            return ResponseManager.sendSuccessResponse(response.getData());
        } else {
            return ResponseManager.sendErrorResponse(response.getMessage());
        }
    }

    @RequestMapping(value = GlobalConstants.USER, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto) throws Exception {
        logger.info("Delete request received for userId: " + userDto.getId());
        Response<UserDto> response = this.userService.deleteUserById(userDto);
        if (response.getCode().getState()) {
            return ResponseManager.sendSuccessResponse(response.getMessage());
        } else {
            return ResponseManager.sendErrorResponse(response.getMessage());
        }
    }

    @RequestMapping(value = GlobalConstants.LOGIN, method = RequestMethod.GET)
    public ResponseEntity<String> userLogin(@RequestBody UserDto userDto) throws IOException, ExecutionException, InterruptedException {
        logger.info("Login request receive from " + userDto.getEmail());
        Response<UserDto> response = userService.userLogin(userDto);
        if (response.getCode().getState()) {
            return ResponseManager.sendSuccessResponse(response.getMessage());
        } else {
            return ResponseManager.sendErrorResponse(response.getMessage());
        }
    }
}
