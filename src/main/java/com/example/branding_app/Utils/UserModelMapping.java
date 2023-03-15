package com.example.branding_app.Utils;

import com.example.branding_app.dto.UserDto;
import com.example.branding_app.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UserModelMapping {
    public static Map<String, Object> userMapping(User user) {
        Map<String, Object> data = new HashMap<>();
        String id = UUID.randomUUID().toString();

        data.put("id", id);
        data.put("name", user.getName());
        data.put("email", user.getEmail());
        data.put("password", user.getPassword());
        data.put("address", user.getAddress());
        return data;
    }

    public static Map<String, Object> userModelMapping(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("email", user.getEmail());
        data.put("password", user.getPassword());
        data.put("address", user.getAddress());
        return data;
    }

    public static UserDto mapToUserDto(Map<String, Object> data) {
        UserDto userDto = new UserDto();
        userDto.setId(data.get("id").toString());
        userDto.setName(data.get("name").toString());
        userDto.setEmail(data.get("email").toString());
        userDto.setPassword(data.get("password").toString());
        userDto.setAddress(data.get("address").toString());
        return userDto;
    }
}
