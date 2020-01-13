package com.etsm.ETSM.Services;

import com.etsm.ETSM.Annotations.EmailExistsException;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.VerificationToken;
import com.etsm.ETSM.Repositories.UserRepository;
import com.etsm.ETSM.Repositories.VerificationTokenRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RegistrationServiceImplTest {

    @Test(expected = EmailExistsException.class)
    public void AddNewUserTest() throws EmailExistsException {

        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        PasswordEncoder passwordEncoderMock = mock(PasswordEncoder.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        registrationService.setPasswordEncoder(passwordEncoderMock);
        registrationService.setUserRepository(userRepositoryMock);
        //

        User user = new User();
        user.setId(1L);
        user.setPassword("password");
        Mockito.when(passwordEncoderMock.encode(user.getPassword())).thenReturn(user.getPassword());
        registrationService.AddNewUser(user);

        Mockito.when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(user);
        registrationService.AddNewUser(user);

    }

    @Test
    public void getUserTest(){
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        VerificationTokenRepository verificationTokenRepositoryMock = mock(VerificationTokenRepository.class);
        registrationService.setTokenRepository(verificationTokenRepositoryMock);
        //

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setId(1L);
        User user = new User();
        user.setId(1L);
        user.setVerificationToken(verificationToken);
        String token = "123";

        Mockito.when(verificationTokenRepositoryMock.findByToken(token)).thenReturn(verificationToken);
        registrationService.getUser(token);


    }

    @Test
    public void getVerificationTokenTest(){
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        VerificationTokenRepository verificationTokenRepositoryMock = mock(VerificationTokenRepository.class);
        registrationService.setTokenRepository(verificationTokenRepositoryMock);
        //

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setId(1L);
        User user = new User();
        user.setId(1L);
        user.setVerificationToken(verificationToken);
        String token = "123";

        Mockito.when(verificationTokenRepositoryMock.findByToken(token)).thenReturn(verificationToken);
        registrationService.getVerificationToken(token);

    }

    @Test
    public void saveRegisteredUserTest(){
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        registrationService.setUserRepository(userRepositoryMock);
        //

        User user = new User();
        user.setId(1L);

        registrationService.saveRegisteredUser(user);

    }

    @Test
    public void createVerificationToken(){
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        VerificationTokenRepository verificationTokenRepositoryMock = mock(VerificationTokenRepository.class);
        registrationService.setTokenRepository(verificationTokenRepositoryMock);
        UserService userServiceMock = mock(UserService.class);
        registrationService.setUserService(userServiceMock);
        //

        User user = new User();
        user.setId(1L);

        String tocken = "123";

        registrationService.createVerificationToken(user,tocken);

    }


}