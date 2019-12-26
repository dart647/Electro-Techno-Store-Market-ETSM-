package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.UserInfo;
import com.etsm.ETSM.Repositories.UserInfoRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserInformationServiceImplTest {

    @Test
    public void addUserInfoTest(){
        UserInformationServiceImpl userInformationServiceImpl = new UserInformationServiceImpl();
        UserInfoRepository userInfoRepositoryMock = mock(UserInfoRepository.class);

        User user = new User();
        user.setId(1L);
        UserInfo userInfo = new UserInfo();
        user.setUserInfo(userInfo);
       UserInfo newInfo = user.getUserInfo();
       newInfo.setUser_id(user);

       Mockito.when(userInfoRepositoryMock.saveAndFlush(newInfo)).thenReturn(userInfo);
       userInformationServiceImpl.setUserInfoRepository(userInfoRepositoryMock);

        Assert.assertEquals(userInformationServiceImpl.addUserInfo(user,userInfo),true);

    }

    @Test (expected=NullPointerException.class)
    public void editUserAuthTest()  {




      UserRepository userRepositoryMock = mock(UserRepository.class);
      PasswordEncoder passwordEncoderMock = mock(PasswordEncoder.class);
      UserService userServiceMock = mock(UserService.class);
      UserInformationServiceImpl userInformationServiceImpl = new UserInformationServiceImpl();
      userInformationServiceImpl.setUserRepository(userRepositoryMock);
      userInformationServiceImpl.setPasswordEncoder(passwordEncoderMock);
      userInformationServiceImpl.setUserService(userServiceMock);

        User existUser = new User();
        existUser.setId(1L);
        existUser.setLogin("Login");
        existUser.setUsername("username");
        existUser.setPassword("password");

          User oldUser = new User();
          oldUser.setId(2L);
          oldUser.setLogin("Login");
          oldUser.setUsername("username");
          oldUser.setPassword("password");



      List<User> UserList = new ArrayList<>();
      UserList.add(existUser);

      User newUser = new User();
      newUser.setId(3L);
      newUser.setPassword("");
        newUser.setLogin("Login");
        newUser.setUsername("username");
        newUser.setPassword("password");

        User newUser2 = new User();
        newUser2.setId(4L);
        newUser2.setPassword("");

        User newUser3 = new User();
        newUser3.setId(5L);
        newUser3.setPassword("123");







     Mockito.when(userRepositoryMock.findAll()).thenReturn(UserList);
      Assert.assertFalse(userInformationServiceImpl.editUserAuth(oldUser,newUser));
      Assert.assertFalse(userInformationServiceImpl.editUserAuth(oldUser,newUser2));

      Mockito.when(passwordEncoderMock.encode(newUser3.getPassword())).thenReturn(newUser3.getPassword());
      Assert.assertTrue(userInformationServiceImpl.editUserAuth(oldUser,newUser3));


       // List<User> NullList=null;
     // Mockito.when(userRepositoryMock.findAll()).thenReturn(null);
     // User oldNull = new User();
      //oldNull.setId(50L);
      //Mockito.when(userRepositoryMock.findAll()).thenReturn(NullList);
        Assert.assertNull(userInformationServiceImpl.editUserAuth(null,newUser));
        //String nullString =null;
        //Assert.fail(nullString);


    }

    @Test
    public void deleteUserTest(){

        UserInformationServiceImpl userInformationServiceImpl = new UserInformationServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserInfoRepository userInfoRepositoryMock = mock(UserInfoRepository.class);

        User user = new User();
        user.setId(1L);

        userInformationServiceImpl.setUserRepository(userRepositoryMock);
        userInformationServiceImpl.setUserInfoRepository(userInfoRepositoryMock);
        userInformationServiceImpl.deleteUser(user);


    }

}