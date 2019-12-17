/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserInformationService {
    void addUserInfo(User user);
    void editUserInfo(User user);
    boolean editUserAuth(User user);
    void addLoyalty(User user);
}
@Service
class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public void addUserInfo(User user) {

    }

    @Override
    public void editUserInfo(User user) {

    }

    @Override
    public boolean editUserAuth(User user) {
        User editedUser = (User) userService.loadUserByUsername(user.getUsername());
        editedUser.setLogin(user.getLogin());
        editedUser.setUsername(user.getUsername());
        editedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(editedUser);
        return true;
    }

    @Override
    public void addLoyalty(User user) {

    }
}
