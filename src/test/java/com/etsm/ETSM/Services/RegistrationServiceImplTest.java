
package com.etsm.ETSM.Services;


import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import com.sun.istack.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RegistrationServiceImplTest {

/*
    @Test
public void AddNewUserTest() {
        RegistrationServiceImpl registrationServiceImpl = new RegistrationServiceImpl();
        UserService userServiceMock = mock(UserService.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        PasswordEncoder passwordEncoderMock = mock(PasswordEncoder.class);

        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("test2");

        registrationServiceImpl.setUserRepository(userRepositoryMock);
        registrationServiceImpl.setUserService(userServiceMock);
        registrationServiceImpl.setPasswordEncoder(passwordEncoderMock);

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

    RegistrationServiceImpl registrationServiceImpl2 = new RegistrationServiceImpl();
        registrationServiceImpl2.setUserRepository(userRepositoryMock);
        registrationServiceImpl2.setUserService(userServiceMock);
        registrationServiceImpl2.setPasswordEncoder(passwordEncoderMock);

        Mockito.when(userServiceMock.loadUserByUsername(user.getUsername())).thenReturn(userDetails);

        Assert.assertEquals(registrationServiceImpl.AddNewUser(user),false);
        Assert.assertEquals(registrationServiceImpl2.AddNewUser(user2),true);

    }
*/
}
