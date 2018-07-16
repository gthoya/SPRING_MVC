package com.gthoya.application.sign.service;

import com.gthoya.application.constant.CommonConstant;
import com.gthoya.application.sign.dao.SignDAO;
import com.gthoya.application.sign.model.User;
import com.gthoya.application.util.CryptoComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignServiceTest {
    private final String plainPassword = "plain";
    private final String encryptPassword = "encrypt";
    @InjectMocks
    private SignService signService;

    @Mock
    private CryptoComponent cryptoComponent;

    @Mock
    private SignDAO signDAO;

    @Test
    public void testCreateUser() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithoutPassword(param)).thenReturn(null);
        when(signDAO.insertUser(param)).thenReturn(1);

        assertEquals(signService.createUser(param), CommonConstant.SUCCESS);

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithoutPassword(param);
        verify(signDAO, times(1)).insertUser(param);
    }

    @Test
    public void testCreateUserWhenUserIsAlreadyExists() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithoutPassword(param)).thenReturn(new User());

        assertEquals(signService.createUser(param), "user id already exists");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithoutPassword(param);
        verify(signDAO, never()).insertUser(param);
    }

    @Test
    public void testCreateUserWhenSignUpIsFail() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithoutPassword(param)).thenReturn(null);
        when(signDAO.insertUser(param)).thenReturn(0);

        assertEquals(signService.createUser(param), "sign up fail");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithoutPassword(param);
        verify(signDAO, times(1)).insertUser(param);
    }

    @Test
    public void testCreateUserWhenEncryptThrowsException() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenThrow(Exception.class);

        assertEquals(signService.createUser(param), "password encrypt error");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, never()).selectUserWithoutPassword(param);
        verify(signDAO, never()).insertUser(param);
    }

    @Test
    public void testGetUser() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithPassword(param)).thenReturn(new User());

        assertEquals(signService.getUser(param).getMessage(), CommonConstant.SUCCESS);

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithPassword(param);
    }

    @Test
    public void testGetUserWhenUserIsNotExists() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithPassword(param)).thenReturn(null);

        assertEquals(signService.getUser(param).getMessage(), "user not found (please check ID and password)");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithPassword(param);
    }

    @Test
    public void testGetUserWhenEncryptThrowsException() throws Exception {
        User param = new User();
        param.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenThrow(Exception.class);

        assertEquals(signService.getUser(param).getMessage(), "password encrypt error");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, never()).selectUserWithPassword(param);
    }
}
