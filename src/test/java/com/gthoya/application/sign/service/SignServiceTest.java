package com.gthoya.application.sign.service;

import com.gthoya.constant.CommonConstant;
import com.gthoya.application.sign.dao.SignDAO;
import com.gthoya.application.sign.model.User;
import com.gthoya.util.CryptoComponent;
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
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithoutPassword(user)).thenReturn(null);
        when(signDAO.insertUser(user)).thenReturn(1);

        assertEquals(signService.createUser(user), CommonConstant.SUCCESS);

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithoutPassword(user);
        verify(signDAO, times(1)).insertUser(user);
    }

    @Test
    public void testCreateUserWhenUserIsAlreadyExists() throws Exception {
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithoutPassword(user)).thenReturn(new User());

        assertEquals(signService.createUser(user), "user id already exists");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithoutPassword(user);
        verify(signDAO, never()).insertUser(user);
    }

    @Test
    public void testCreateUserWhenSignUpIsFail() throws Exception {
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithoutPassword(user)).thenReturn(null);
        when(signDAO.insertUser(user)).thenReturn(0);

        assertEquals(signService.createUser(user), "sign up fail");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithoutPassword(user);
        verify(signDAO, times(1)).insertUser(user);
    }

    @Test
    public void testCreateUserWhenEncryptThrowsException() throws Exception {
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenThrow(Exception.class);

        assertEquals(signService.createUser(user), "password encrypt or decrypt error");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, never()).selectUserWithoutPassword(user);
        verify(signDAO, never()).insertUser(user);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithPassword(user)).thenReturn(new User());

        assertEquals(signService.getUser(user).getMessage(), CommonConstant.SUCCESS);

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithPassword(user);
    }

    @Test
    public void testGetUserWhenUserIsNotExists() throws Exception {
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenReturn(encryptPassword);
        when(signDAO.selectUserWithPassword(user)).thenReturn(null);

        assertEquals(signService.getUser(user).getMessage(), "user not found (please check ID and password)");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, times(1)).selectUserWithPassword(user);
    }

    @Test
    public void testGetUserWhenEncryptThrowsException() throws Exception {
        User user = new User();
        user.setPassword(plainPassword);

        when(cryptoComponent.encrypt(anyString())).thenThrow(Exception.class);

        assertEquals(signService.getUser(user).getMessage(), "password encrypt error");

        verify(cryptoComponent, times(1)).encrypt(anyString());
        verify(signDAO, never()).selectUserWithPassword(user);
    }
}
