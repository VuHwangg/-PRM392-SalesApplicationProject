package com.fpt.PRM392_FinalProject.controller;

import com.fpt.PRM392_FinalProject.dto.UserDTOLoginRequest;
import com.fpt.PRM392_FinalProject.dto.UserDTOLoginResponse;
import com.fpt.PRM392_FinalProject.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/login")
public class UserLoginController {

    UserService userService;

    @PostMapping
    public UserDTOLoginResponse login(@RequestBody UserDTOLoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
