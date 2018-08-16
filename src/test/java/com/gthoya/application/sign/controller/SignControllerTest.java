package com.gthoya.application.sign.controller;

import com.gthoya.constant.CommonConstant;
import com.gthoya.application.sign.model.User;
import com.gthoya.application.sign.service.SignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignControllerTest {
    @InjectMocks
    private SignController signController;

    @Mock
    private SignService signService;

    @Mock
    private MockHttpSession mockHttpSession;

    @Test
    public void testGetSignUpPage() {
        assertEquals(signController.getSignUpPage(), "sign/signUp");
    }

    @Test
    public void testSignUpWithoutUserId() {
        User user = new User();

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), "user id is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignUpWithoutPassword() {
        User user = new User();
        user.setUserId("test");

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), "password is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignUpWithoutUserName() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), "user name is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignUpWithoutAge() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");
        user.setUserName("test");

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), "age is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignUpWithoutGender() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");
        user.setUserName("test");
        user.setAge(10);

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), "gender is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignUp() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");
        user.setUserName("test");
        user.setAge(10);
        user.setGender("M");

        User result = new User();
        result.setMessage(CommonConstant.SUCCESS);

        when(signService.createUser(user)).thenReturn(CommonConstant.SUCCESS);
        when(signService.getUser(user)).thenReturn(result);

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), CommonConstant.SUCCESS);

        verify(signService, times(1)).createUser(user);
        verify(signService, times(1)).getUser(user);
    }

    @Test
    public void testSignUpWhenThrowsException() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");
        user.setUserName("test");
        user.setAge(10);
        user.setGender("M");

        when(signService.createUser(user)).thenThrow(Exception.class);

        assertEquals(signController.signUp(mockHttpSession, user).getMessage(), CommonConstant.FAIL);

        verify(signService, times(1)).createUser(user);
    }

    @Test
    public void testGetSignInPage() {
        assertEquals(signController.getSignInPage(), "sign/signIn");
    }

    @Test
    public void testSignInWithoutUserId() {
        User user = new User();

        assertEquals(signController.signIn(mockHttpSession, user).getMessage(), "user id is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignInWithoutPassword() {
        User user = new User();
        user.setUserId("test");

        assertEquals(signController.signIn(mockHttpSession, user).getMessage(), "password is empty");

        verify(signService, never()).createUser(user);
    }

    @Test
    public void testSignIn() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");

        User result = new User();
        result.setMessage(CommonConstant.SUCCESS);

        when(signService.getUser(user)).thenReturn(result);

        assertEquals(signController.signIn(mockHttpSession, user).getMessage(), CommonConstant.SUCCESS);

        verify(signService, times(1)).getUser(user);
    }

    @Test
    public void testSignInWhenThrowsException() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("test");

        User result = new User();
        result.setMessage("success");

        when(signService.getUser(user)).thenThrow(Exception.class);

        assertEquals(signController.signIn(mockHttpSession, user).getMessage(), CommonConstant.FAIL);

        verify(signService, times(1)).getUser(user);
    }

    @Test
    public void testSignOut() {
        signController.signOut(mockHttpSession);
    }
}
