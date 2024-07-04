package com.learning.spring.utils;

import com.learning.spring.Exception.GeneralException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordEncrypt {

    private static final Logger LOGGER = LogManager.getLogger(PasswordEncrypt.class.getName());

    public static String generateStorngPasswordHash(String password) {

        try {
            LOGGER.debug("inside PasswordEncrypt:generateStringPasswordHash");
            int iterations = 1000;
            char[] chars = password.toCharArray();
            byte[] salt = getSalt();

            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (Exception e) {
            throw new GeneralException("Exception while encrypting the pin, error: " + e.getLocalizedMessage());
        }
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {

        LOGGER.debug("inside PasswordEncrypt:getSalt");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {

        LOGGER.debug("inside PasswordEncrypt:toHex");
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}