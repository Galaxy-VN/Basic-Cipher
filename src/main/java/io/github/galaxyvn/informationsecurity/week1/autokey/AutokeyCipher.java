/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.galaxyvn.informationsecurity.week1.autokey;

/**
 *
 * @author Galaxy-VN
 */
public class AutokeyCipher {
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase();

        StringBuilder extendedKey = new StringBuilder(key);
        extendedKey.append(plaintext); // Extend key with plaintext

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = extendedKey.charAt(i);

            int encryptedChar = (plainChar - 'A' + keyChar - 'A') % 26 + 'A';
            ciphertext.append((char) encryptedChar);
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase();

        StringBuilder plaintext = new StringBuilder();
        StringBuilder extendedKey = new StringBuilder(key);

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = extendedKey.charAt(i);

            int decryptedChar = (cipherChar - 'A' - (keyChar - 'A') + 26) % 26 + 'A';
            plaintext.append((char) decryptedChar);

            extendedKey.append((char) decryptedChar); // Extend key with each decrypted char
        }

        return plaintext.toString();
    }
}
