package com.gthoya.util;

import com.gthoya.constant.CommonConstant;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class CryptoComponent {
    private String iv;
    private Key keySpec;

    public CryptoComponent() throws Exception {
        this.iv = "passwordqwertyui";
        byte[] keyBytes = new byte[16];
        byte[] b = this.iv.getBytes(CommonConstant.DEFAULT_CHARSET);
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
    }

    public String encrypt(String str) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = c.doFinal(str.getBytes(CommonConstant.DEFAULT_CHARSET));

        return new String(Base64.encodeBase64(encrypted));
    }

    public String decrypt(String str) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        byte[] byteStr = Base64.decodeBase64(str.getBytes());

        return new String(c.doFinal(byteStr), CommonConstant.DEFAULT_CHARSET);
    }
}
