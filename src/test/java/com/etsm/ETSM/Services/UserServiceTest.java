package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.UserRepository;
import org.apache.catalina.Manager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Optional;

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

    @Test
    public void deactivateUserTest(){
        UserService userService = new UserService();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        userService.setUserRepository(userRepositoryMock);
        //

        User user = new User();
        user.setId(1L);
        user.setActive(true);
        Optional<User>optionalUser=Optional.of(user);
        Mockito.when(userRepositoryMock.findById(user.getId())).thenReturn(optionalUser);

        User user2 = new User();
        user2.setId(2L);
        user2.setActive(false);
        Optional<User>optionalUser2=Optional.of(user2);
        Mockito.when(userRepositoryMock.findById(user2.getId())).thenReturn(optionalUser2);


        userService.deactivateUser(user2.getId());

    }

    @Test
    public void changeUserRoleTest(){
        UserService userService = new UserService();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        userService.setUserRepository(userRepositoryMock);
        //
        User user = new User();
        user.setId(1L);
        user.setActive(true);

        Optional<User>optionalUser=Optional.of(user);
        Mockito.when(userRepositoryMock.findById(user.getId())).thenReturn(optionalUser);

        userService.changeUserRole(user.getId(),"ADMIN");


    }

}