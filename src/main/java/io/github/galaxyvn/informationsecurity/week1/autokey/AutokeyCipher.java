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
    
    // Example 
    // @String plainText = AB
    // @int key = 7;
    // key -> currentKey
    // Vong 1 c = A
    // char base = A (65)
    // int value = 0
    // encryptedVal = 7
    // kq H (72)
    // value -> currentKey
    // Vong 2 c = 0
    // char base = B (66)
    // int value = 1
    // encryptedVal = 8
    // kq H + I (73)
    // return HI
    public String encrypt(String plainText, int key) {
        StringBuilder result = new StringBuilder();
        int currentKey = (key % 26 + 26) % 26; // ASCII lowercase a-z 2 lan mod de tranh truong hop key la so am
        
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int value = Character.toLowerCase(c) - 'a';
                
                int encryptedVal = (value + currentKey) % 26;
                
                result.append((char) (base + encryptedVal));
                currentKey = value; 
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    public String decrypt(String cipherText, int key) {
        StringBuilder result = new StringBuilder();
        int currentKey = (key % 26 + 26) % 26;

        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int value = Character.toLowerCase(c) - 'a';

                int decryptedVal = (value - currentKey + 26) % 26;
                result.append((char) (base + decryptedVal));

                currentKey = decryptedVal;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
