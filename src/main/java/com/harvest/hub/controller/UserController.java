package com.harvest.hub.controller;

import com.harvest.hub.dao.LoginDao;
import com.harvest.hub.dao.RegisterDao;
import com.harvest.hub.dto.AuthResponse;
import com.harvest.hub.dto.UserDto;
import com.harvest.hub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(
            @RequestBody LoginDao loginDao
    ) {
        return this.userService.login(loginDao);
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestBody RegisterDao registerDao
    ) {
        this.userService.register(registerDao);
    }

    @GetMapping("me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto me(
            Principal principal
    ) {
        return this.userService.me(principal);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(
            @PathVariable Long id,
            Principal principal
    ) {
        this.userService.deleteUser(id, principal);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestBody RegisterDao registerDao,
            Principal principal
    ) {
        this.userService.createUser(registerDao, principal);
    }

}


