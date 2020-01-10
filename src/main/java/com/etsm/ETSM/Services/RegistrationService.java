package com.etsm.ETSM.Services;

import com.etsm.ETSM.Annotations.EmailExistsException;
import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.UserRepository;
import com.etsm.ETSM.Repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

public interface RegistrationService{
    User AddNewUser(User user) throws EmailExistsException;
    User getUser(String verificationToken);
    void saveRegisteredUser(User user);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
}

@Service
class RegistrationServiceImpl implements RegistrationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private VerificationTokenRepository tokenRepository;

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

    @Autowired
    public void setTokenRepository(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public User AddNewUser(User user) throws EmailExistsException {
        if (emailExist(user.getUsername())) {
            throw new EmailExistsException(
                    "Account already exists!");
        }
            User newUser = new User();
            UserInfo userInfo = new UserInfo();
            Loyalty loyalty = new Loyalty();
            loyalty.setUserInfo_id(userInfo);
            loyalty.setBalance(0);
            userInfo.setFio("new user");
            userInfo.setWallet(0);
            userInfo.setPhoneNumber("0");
            newUser.setUsername(user.getUsername());
            newUser.setLogin(user.getLogin());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setActive(false);
            newUser.setRoles(Collections.singleton(Role.USER));
            newUser.setUserInfo(userInfo);
            return userRepository.saveAndFlush(newUser);
    }

    private boolean emailExist(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
