package com.webapp.share4better.service.encruption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IEncryption {
    public String decrypt(String strToDecrypt) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException;
    public String encrypt (String strToEncrypt);
    public boolean isEncrypted(String s);
}
