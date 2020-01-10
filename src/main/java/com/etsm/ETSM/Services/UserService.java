/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFoundByUsername = userRepository.findByUsername(username);
        User userFoundByLogin = userRepository.findByLogin(username);
        User userFindByGoogleUsername = userRepository.findByGoogleUsername(username);
        User userFindByGoogleName = userRepository.findByGoogleName(username);

        if (userFoundByUsername != null) {
            return userFoundByUsername;
        }

        if (userFoundByLogin != null) {
            return userFoundByLogin;
        }

        if(userFindByGoogleUsername != null)
        {
            return userFindByGoogleUsername;
        }

        if(userFindByGoogleName != null)
        {
            return userFindByGoogleName;
        }

        return null;
    }

    public void deactivateUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User temp = user.get();
            if (temp.isEnabled()) {
                temp.setActive(false);
            } else {
                temp.setActive(true);
            }
            userRepository.saveAndFlush(temp);
        }
    }

    public void changeUserRole(Long userId, String roleName) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User temp = user.get();
            temp.setRoles(Collections.singleton(Role.valueOf(roleName)));
            userRepository.saveAndFlush(temp);
        }
    }
}
