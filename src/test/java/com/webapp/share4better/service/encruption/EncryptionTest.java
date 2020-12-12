package com.webapp.share4better.service.encruption;

import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class EncryptionTest {

    private IEncryption encryption = new Encryption();


    @Test
    public void encrypt() throws NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException {
        String encrypted = encryption.encrypt("ABCDEFG");
        assertEquals("Encryption test","eKFpo61ZA9sRWHud7bqzcw==",encrypted);

        assertTrue("IsEncrypted test",encryption.isEncrypted(encrypted));

        String decrypted = encryption.decrypt(encrypted);

        assertEquals("Decryption test","ABCDEFG",decrypted);
    }

}