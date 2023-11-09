package com.fpt.PRM392_FinalProject.service;

import com.fpt.PRM392_FinalProject.dto.UserDTOChangePasswordRequest;
import com.fpt.PRM392_FinalProject.dto.UserDTOLoginRequest;
import com.fpt.PRM392_FinalProject.dto.UserDTOLoginResponse;

public interface UserService {
    UserDTOLoginResponse login(UserDTOLoginRequest loginRequest);

    Boolean confirmPassword(UserDTOChangePasswordRequest userDTOChangePasswordRequest);
}
