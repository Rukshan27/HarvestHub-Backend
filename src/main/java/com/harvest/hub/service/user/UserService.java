package com.harvest.hub.service.user;

import com.harvest.hub.dao.RegisterDao;
import com.harvest.hub.dao.LoginDao;
import com.harvest.hub.dto.AuthResponse;
import com.harvest.hub.dto.UserDto;

import java.security.Principal;
import java.util.List;

public interface UserService {

    AuthResponse login(LoginDao loginDao);

    void register(RegisterDao registerDao);

    UserDto me(Principal principal);

    List<UserDto> getAllUsers();

    void deleteUser(Long id, Principal principal);

    void createUser(RegisterDao registerDao, Principal principal);
}
