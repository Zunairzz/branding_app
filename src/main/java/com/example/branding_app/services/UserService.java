package com.example.branding_app.services;

import com.example.branding_app.Utils.Response;
import com.example.branding_app.dto.UserDto;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface UserService {
    Response<UserDto> saveUser(UserDto user);
    Response<UserDto> updateUser(UserDto userDto) throws IOException, ExecutionException, InterruptedException;
}
