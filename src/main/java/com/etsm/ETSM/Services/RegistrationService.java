package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

public interface RegistrationService{
    boolean AddNewUser(User user);
}

@Service
class RegistrationServiceImpl implements RegistrationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean AddNewUser(User user) {
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            User newUser = new User();
            UserInfo userInfo = new UserInfo();
            userInfo.setFio("new user");
            userInfo.setWallet(0);
            newUser.setUsername(user.getUsername());
            newUser.setLogin(user.getLogin());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setActive(true);
            newUser.setRoles(Collections.singleton(Role.USER));
            newUser.setUserInfo(userInfo);
            userRepository.saveAndFlush(newUser);
            return true;
        } else return false;
    }
}
