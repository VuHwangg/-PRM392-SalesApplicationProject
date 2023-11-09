package com.fpt.PRM392_FinalProject.controller;

import com.fpt.PRM392_FinalProject.dto.UserDTOChangePasswordRequest;
import com.fpt.PRM392_FinalProject.dto.UserDTOLoginRequest;
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
@RequestMapping("api/v1/user")
public class UserController {
    UserService userService;


    @PostMapping("/confirmPassword")
    public Boolean confirmPassword(@RequestBody UserDTOChangePasswordRequest userDTOChangePasswordRequest){
        return userService.confirmPassword(userDTOChangePasswordRequest);
    }
}
