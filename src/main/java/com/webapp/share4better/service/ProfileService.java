package com.webapp.share4better.service;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String pass = "aetagrasdohasigysiadfguaisodhgjksdhfnaiowertyaw894taydjkgrugizdsiogsyrtlasdygioasryta";

    @Autowired
    private IUserRepository repository;

    @Override
    public Optional<Profile> getUserProfile(String userEmail) {

        Optional<Profile> profile = repository.userAuthentication(userEmail);
        if (profile.isPresent()){
            String pass = decrypt(profile.get().getUser_password());
            profile.get().setUser_password(pass);
        }
        return profile;
    }

    @Override
    public void addUser(Profile profile) {
        String pass = encrypt(profile.getUser_password());
        profile.setUser_password(pass);
              repository.save(profile);
    }

    @Override
    public Optional<Profile> findUserById(Integer id) {

        Optional<Profile> profile = repository.findUser(id);
        if (profile.isPresent()){
            String pass = decrypt(profile.get().getUser_password());
            profile.get().setUser_password(pass);
        }
        return profile;
    }

    @Override
    public void removeUser(Integer id) {
        repository.deleteById(id);
    }

    private static void setKey()
    {
        MessageDigest sha = null;
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

    private static String encrypt (String strToEncrypt)
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

    private static String decrypt(String strToDecrypt)
    {
        try
        {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


}
