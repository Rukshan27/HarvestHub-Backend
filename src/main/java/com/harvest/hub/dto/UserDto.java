package com.harvest.hub.dto;

import com.harvest.hub.constant.enums.UserType;
import com.harvest.hub.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private UserType userType;
    private String mobileNo;

    public UserDto(User user) {
        BeanUtils.copyProperties(user, this);
    }

}
