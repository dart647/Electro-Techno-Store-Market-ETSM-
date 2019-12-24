/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface UserInformationService {
    boolean addUserInfo(User user, UserInfo userInfo);
    boolean editUserAuth(User oldUser, User newUser);
    void deleteUser(User user);
}
@Service
class UserInformationServiceImpl implements UserInformationService {
    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean addUserInfo(User user, UserInfo userInfo) {
        UserInfo newInfo = user.getUserInfo();
        newInfo.setUser_id(user);
        newInfo.setLoyaltyCode(userInfo.getLoyaltyCode());
        newInfo.setAddress(userInfo.getAddress());
        newInfo.setBirthDate(userInfo.getBirthDate());
        newInfo.setWallet(0);
        newInfo.setSales(new ArrayList<>());
        newInfo.setFio(userInfo.getFio());
        userInfoRepository.saveAndFlush(newInfo);
        return true;
    }

    @Override
    public boolean editUserAuth(User oldUser, User newUser) {
        try {
            User foundUser = userRepository.findByLogin(newUser.getLogin());
            User foundUser2 = userRepository.findByUsername(newUser.getUsername());
            if (foundUser != null && foundUser2 != null) {
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        oldUser.setLogin(newUser.getLogin());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.saveAndFlush(oldUser);
        return true;
    }

    @Override
    public void deleteUser(User user) {
        userInfoRepository.delete(user.getUserInfo());
        userRepository.delete(user);
    }
}
