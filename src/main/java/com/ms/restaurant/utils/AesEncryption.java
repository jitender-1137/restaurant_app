package com.ms.restaurant.utils;

import com.ms.restaurant.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.regex.Pattern;

@Slf4j
public class AesEncryption {

    public static String iv = "aibKcM9Jq6i8NIt+ACg8LQ==";
    public static String ivKey = "dxW/a/raDOtWV9T/8UL8OLVig0am9k4kBMw4x9rddfg=";
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[]{'P', 'i', '@', 'a', 'T', 'o', 'm', '#', '$', 't', 'e', 'c', 'h',
            '1', '2', '3'};

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public static String encrypt(String data) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(data.getBytes());
            String encodeToString = Base64.getEncoder().encodeToString(encVal);
            if (Pattern.compile("\\/").matcher(encodeToString).find()) {
                encodeToString = encodeToString.replaceAll("/", "_");
            }
            return encodeToString;
        } catch (Exception e) {
            log.error("Error while AesEncryption :: {} ", e.getMessage());
            return null;
        }
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) {
        try {
            if (Pattern.compile("_").matcher(encryptedData).find()) {
                encryptedData = encryptedData.replaceAll("_", "/");
            }
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            return new String(decValue);
        } catch (Exception e) {
            log.info("Error in Decryption :: " + e.getMessage());
            throw new ServiceException("CS79");
        }
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }

    public static String ivEncrypt(String string) {
        try {
            byte[] ivs = Base64.getDecoder().decode(iv);
            IvParameterSpec ivspec = new IvParameterSpec(ivs);

            SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(ivKey), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(string.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error("Error in ivEncryption :: {} ", e.getMessage());

        }
        return null;
    }

    public static String ivDecrypt(String message) {
        try {
            byte[] msg = Base64.getDecoder().decode(message.getBytes());

            SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(ivKey), "AES");

            byte[] ivs = Base64.getDecoder().decode(iv);
            IvParameterSpec ivspec = new IvParameterSpec(ivs);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivspec);
            return new String(cipher.doFinal(msg));
        } catch (Exception e) {
            log.error("Error in ivDecryption :: {} ", e.getMessage());
        }
        return null;
    }
}
