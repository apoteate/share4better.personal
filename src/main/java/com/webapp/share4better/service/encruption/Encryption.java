package com.webapp.share4better.service.encruption;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class Encryption implements IEncryption {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String pass = "JmehDZknTYSTmgi0pM8JJyvbHT9Z1SfUbdVYytzrt0T0W9o6NNcHyXGhQJ7BZAPjp/pQ6H9nuumOtn/TDlAOGtBDqYk25a4PeiFPT4cARKnRzhh4iNsJdwNAvvK3RSUd$JmehDZknTYSTmgi0pM8JJyvbHT9Z1SfUbdVYytzrt0T0W9o6NNcHyXGhQJ7BZAPjp";

    public String encrypt (String strToEncrypt)
    {
        try
        {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {

        setKey();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

    private static void setKey()
    {
        MessageDigest sha;
        try {
            key = pass.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public boolean isEncrypted(String key) {
        try {
            decrypt(key);
            return true;
        } catch (InvalidKeyException e) {
            return false;
        } catch (NoSuchPaddingException e) {
            return false;
        } catch (NoSuchAlgorithmException e) {
            return false;
        } catch (IllegalBlockSizeException e) {
            return false;
        } catch (BadPaddingException e) {
            return false;
        }
    }


}
