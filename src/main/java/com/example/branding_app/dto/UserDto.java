package com.example.branding_app.dto;

import com.example.branding_app.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    String id;
    String name;
    String email;
    String password;
    String address;

    public User getUpdatedValues(User user) {
        if (null != this.id) user.setId(this.id);
        if (null != this.name) user.setName(this.name);
        if (null != this.email) user.setEmail(this.email);
        if (null != this.password) user.setPassword(this.password);
        if (null != this.address) user.setAddress(this.address);
        return user;
    }
}
