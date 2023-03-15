package com.example.branding_app.services.implementation;

import com.example.branding_app.Utils.Response;
import com.example.branding_app.Utils.ResponseCode;
import com.example.branding_app.Utils.UserModelMapping;
import com.example.branding_app.dataBase.UserRepository;
import com.example.branding_app.dto.UserDto;
import com.example.branding_app.model.User;
import com.example.branding_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImplementation implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
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
            logger.info("User saved successfully!");
        } else {
            response = new Response<>(ResponseCode.BAD_REQUEST, "data not saved!");
            logger.warn("data not saved!");
        }
        return response;
    }

    @Override
    public Response<UserDto> updateUser(UserDto userDto) throws IOException, ExecutionException, InterruptedException {
        logger.info("Check user exist or not");
        Map<String, Object> existing = this.userRepository.getUserById(userDto.getId());
        Response<UserDto> response;
        if (existing != null) {
            logger.info("User found!");
            User user = this.modelMapper.map(userDto, User.class);
            User updateUser = userDto.getUpdatedValues(user);
            logger.info("User model mapping");
            Map<String, Object> data = UserModelMapping.userModelMapping(updateUser);
            boolean flag = userRepository.saveUser(data);
            if (flag) {
                logger.info("User updated successfully!");
                UserDto userdto = UserModelMapping.mapToUserDto(data);
                response = new Response<>(ResponseCode.SUCCESS, "data update successfully", userdto);
            } else {
                logger.warn("User does not update!");
                response = new Response<>(ResponseCode.UNMODIFIED, "data does update");
            }
        } else {
            logger.warn("User does not exist!");
            response = new Response<>(ResponseCode.BAD_REQUEST, "User does not exist!");
        }
        return response;
    }

    @Override
    public Response<UserDto> deleteUserById(UserDto userDto) throws Exception {
        Response<UserDto> response;
        logger.info("Check user exist or not");
        Map<String, Object> existing = this.userRepository.getUserById(userDto.getId());
        if (existing != null) {
            Boolean flag = this.userRepository.deleteUserById(userDto.getId());
            if (flag) {
                logger.info("User deleted successfully!");
                response = new Response<>(ResponseCode.SUCCESS, "User deleted successfully!");
            } else {
                logger.warn("User does not delete!");
                response = new Response<>(ResponseCode.UNMODIFIED, "User does not delete!");
            }
        } else {
            logger.warn("User does not exist!");
            response = new Response<>(ResponseCode.BAD_REQUEST, "User does not exist!");
        }
        return response;
    }
}
