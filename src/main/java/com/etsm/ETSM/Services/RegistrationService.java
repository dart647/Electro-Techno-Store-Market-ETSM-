package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

public interface RegistrationService{
    void AddNewUser(User user);
}

@Service
class RegistrationServiceImpl implements RegistrationService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void AddNewUser(User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.saveAndFlush(newUser);
    }
}