
package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthProviderTest {

    @Test
    public void authenticateTest() {

        AuthProvider authProvider = new AuthProvider();
        UserService userServiceMock = mock(UserService.class);
        PasswordEncoder passwordEncoderMock = mock(PasswordEncoder.class);
        authProvider.setPasswordEncoder(passwordEncoderMock);
        authProvider.setUserService(userServiceMock);

        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return "123";
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "username";
            }
        };

        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("123");
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
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
        String password = (String) authentication.getCredentials();
        Mockito.when(userServiceMock.loadUserByUsername(user.getUsername())).thenReturn(userDetails);
        Mockito.when(passwordEncoderMock.matches(password, user.getPassword())).thenReturn(false);

//        Assert.assertEquals(authProvider.authenticate(authentication), "Wrong password");
    }
}


