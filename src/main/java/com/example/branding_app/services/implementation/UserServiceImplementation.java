package com.example.branding_app.services.implementation;

import com.example.branding_app.Utils.Response;
import com.example.branding_app.Utils.ResponseCode;
import com.example.branding_app.Utils.UserModelMapping;
import com.example.branding_app.dataBase.UserRepository;
import com.example.branding_app.dto.UserDto;
import com.example.branding_app.model.User;
import com.example.branding_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response<UserDto> saveUser(UserDto userDto) {
        Response<UserDto> response;
        User saveUser = this.modelMapper.map(userDto, User.class);
        Map<String, Object> data = UserModelMapping.userMapping(saveUser);
        Boolean flag = userRepository.saveUser(data);

        if (flag == true) {
            UserDto userdto = UserModelMapping.mapToUserDto(data);
            response = new Response<>(ResponseCode.SUCCESS, "data added successfully", userdto);
        } else {
            response = new Response<>(ResponseCode.BAD_REQUEST, "data not saved!");
        }
        return response;
    }

    @Override
    public Response<UserDto> updateUser(UserDto userDto) throws IOException, ExecutionException, InterruptedException {

        Map<String, Object> existing = this.userRepository.getUserById(userDto.getId());
        Response<UserDto> response;
        if (existing != null) {
            User updateUser = this.modelMapper.map(userDto, User.class);
            Map<String, Object> data = UserModelMapping.userMapping(updateUser);
            boolean flag = userRepository.saveUser(data);
            if (flag == true) {
                UserDto userdto = UserModelMapping.mapToUserDto(data);
                response = new Response<>(ResponseCode.SUCCESS, "data update successfully", userdto);
            } else {
                response = new Response<>(ResponseCode.BAD_REQUEST, "data does update");
            }
        } else {
            response = new Response<>(ResponseCode.UNMODIFIED, "data does not exist");
        }
        return response;
    }
}
