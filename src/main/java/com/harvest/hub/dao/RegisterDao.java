package com.harvest.hub.dao;

import com.harvest.hub.constant.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDao {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private String mobileNo;
    private UserType userType;

}
