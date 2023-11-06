package com.fpt.PRM392_FinalProject.service.impl;

import com.fpt.PRM392_FinalProject.dto.UserDTOLoginRequest;
import com.fpt.PRM392_FinalProject.dto.UserDTOLoginResponse;
import com.fpt.PRM392_FinalProject.entity.Customer;
import com.fpt.PRM392_FinalProject.exception.Exception;
import com.fpt.PRM392_FinalProject.mapper.UserMapper;
import com.fpt.PRM392_FinalProject.repository.UserRepository;
import com.fpt.PRM392_FinalProject.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;


    @Override
    public UserDTOLoginResponse login(UserDTOLoginRequest loginRequest) {
        Customer customer = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> Exception.badRequest("Account not found"));

        boolean isAuthentication = customer.getPassword().matches(loginRequest.getPassword());

        if (!isAuthentication) {
            throw Exception.badRequest("Username or password is incorrect");
        }
        return UserMapper.toUserDTOLoginResponse(customer);
    }
}
