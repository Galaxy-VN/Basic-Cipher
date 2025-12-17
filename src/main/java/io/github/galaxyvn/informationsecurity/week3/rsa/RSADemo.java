package io.github.galaxyvn.informationsecurity.week3.rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class RSADemo {
    
    public static void main(String[] args) throws IOException {
        int primeSize = 2048;
        RSACipher rsa = new RSACipher(primeSize);

        System.out.println("Please enter message: ");
        String message = new BufferedReader(new InputStreamReader(System.in)).readLine();
        
        String encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage + System.lineSeparator());
        
        String decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage + System.lineSeparator());
    }
}
