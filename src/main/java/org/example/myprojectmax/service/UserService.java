package org.example.myprojectmax.service;

import org.example.myprojectmax.dto.UserRegistrationDto;
import org.example.myprojectmax.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    List<User> getAll();

}
