/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.galaxyvn.informationsecurity.week2.aes;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Administrator
 */
public class AESCipher {
    
    private static final String ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "encryptionKey";
    
    public static String encrypt(String message, String secretKey) throws 
            NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, 
            NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException
    {
        SecretKey key = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encrypted, String secretKey) throws
            NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
            NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException
    {
        SecretKey key = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
    
    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException {
        byte[] keyBytes = secretKey.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        return keySpec;
    }
    
    public static String generateRegistrationKey(String username, String password) {
        String registrationKey = username+":"+password+":"+ENCRYPTION_KEY;
        return registrationKey;
    }
    
    public static void saveRegistrationKeyToFile(String registrationKey, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(registrationKey);
        }
    }

    public static String readRegistrationKeyFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (String) ois.readObject();
        }
    }
}
