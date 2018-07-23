package com.gthoya.application.sign.controller;

import com.gthoya.application.constant.CommonConstant;
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
    public void testGetMainPage() throws Exception {
        assertEquals(signController.getMainPage(), "main/main");
    }

    @Test
    public void testGetSignUpPage() {
        assertEquals(signController.getSignUpPage(), "sign/signUp");
    }

    @Test
    public void testSignUpWithoutUserId() {
        User param = new User();
        param.setPassword("test");

        assertEquals(signController.signUp(mockHttpSession, param).getMessage(), "user id is empty");

        verify(signService, never()).createUser(param);
    }

    @Test
    public void testSignUpWithoutPassword() {
        User param = new User();
        param.setUserId("test");

        assertEquals(signController.signUp(mockHttpSession, param).getMessage(), "password is empty");

        verify(signService, never()).createUser(param);
    }

    @Test
    public void testSignUp() {
        User param = new User();
        param.setUserId("test");
        param.setPassword("test");

        when(signService.createUser(param)).thenReturn(CommonConstant.SUCCESS);

        assertEquals(signController.signUp(mockHttpSession, param).getMessage(), CommonConstant.SUCCESS);

        verify(signService, times(1)).createUser(param);
    }

    @Test
    public void testSignUpWhenThrowsException() {
        User param = new User();
        param.setUserId("test");
        param.setPassword("test");

        when(signService.createUser(param)).thenThrow(Exception.class);

        assertEquals(signController.signUp(mockHttpSession, param).getMessage(), CommonConstant.FAIL);

        verify(signService, times(1)).createUser(param);
    }

    @Test
    public void testGetSignInPage() {
        assertEquals(signController.getSignInPage(), "sign/signIn");
    }

    @Test
    public void testSignInWithoutUserId() {
        User param = new User();
        param.setPassword("test");

        assertEquals(signController.signIn(mockHttpSession, param).getMessage(), "user id is empty");

        verify(signService, never()).createUser(param);
    }

    @Test
    public void testSignInWithoutPassword() {
        User param = new User();
        param.setUserId("test");

        assertEquals(signController.signIn(mockHttpSession, param).getMessage(), "password is empty");

        verify(signService, never()).createUser(param);
    }

    @Test
    public void testSignIn() {
        User param = new User();
        param.setUserId("test");
        param.setPassword("test");

        User result = new User();
        result.setMessage(CommonConstant.SUCCESS);

        when(signService.getUser(param)).thenReturn(result);

        assertEquals(signController.signIn(mockHttpSession, param).getMessage(), CommonConstant.SUCCESS);

        verify(signService, times(1)).getUser(param);
    }

    @Test
    public void testSignInWhenThrowsException() {
        User param = new User();
        param.setUserId("test");
        param.setPassword("test");

        User result = new User();
        result.setMessage("success");

        when(signService.getUser(param)).thenThrow(Exception.class);

        assertEquals(signController.signIn(mockHttpSession, param).getMessage(), CommonConstant.FAIL);

        verify(signService, times(1)).getUser(param);
    }
}
