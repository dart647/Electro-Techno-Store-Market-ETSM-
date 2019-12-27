package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserServiceTest {


    private Object UserService;

    @Test
    public void loadUserByUsernameTest(){

        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserService userService = new UserService();

        String username = "test";
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setLogin("test");
        user.setGoogleUsername("test");
        user.setGoogleName("test");


        User userNull = new User();
        userNull.setId(2L);



        userService.setUserRepository(userRepositoryMock);

        Mockito.when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(userRepositoryMock.findByLogin(user.getLogin())).thenReturn(user);
        Mockito.when(userRepositoryMock.findByGoogleUsername(user.getGoogleUsername())).thenReturn(user);
        Mockito.when(userRepositoryMock.findByGoogleName(user.getGoogleName())).thenReturn(user);

        Mockito.when(userRepositoryMock.findByGoogleName(userNull.getGoogleName())).thenReturn(null);


        Assert.assertEquals(userService.loadUserByUsername(user.getUsername()),user);
        Assert.assertEquals(userService.loadUserByUsername(user.getLogin()),user);
        Assert.assertEquals(userService.loadUserByUsername(user.getGoogleUsername()),user);
        Assert.assertEquals(userService.loadUserByUsername(user.getGoogleName()),user);

        Assert.assertEquals(userService.loadUserByUsername(userNull.getGoogleName()),null);

    }

}