package com.example.branding_app.services.implementation;

import com.example.branding_app.dataBase.UserRepository;
import com.example.branding_app.model.User;
import com.example.branding_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean saveUser(User user) {
        return userRepository.saveUser(user);
    }
}
