package com.gthoya.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CryptComponentTest {
    private CryptoComponent cryptoComponent;

    @Before
    public void before() throws Exception {
        cryptoComponent = new CryptoComponent();
    }

    @Test
    public void testEncrypt() throws Exception {
        System.out.println(cryptoComponent.encrypt("test"));
    }

    @Test
    public void testDecrypt() throws Exception {
        Assert.assertEquals(cryptoComponent.decrypt("JzSnOy5oaMA1+hPTU6pU6A=="), "test");
    }
}
