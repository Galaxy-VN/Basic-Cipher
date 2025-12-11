/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.galaxyvn.informationsecurity.week1.vigenere;

/**
 *
 * @author Administrator
 */
public class ViginereCipher {
    public String encrypt(String text, String key) {
        return viginereCipher(text, key, true);
    }

    public String decrypt(String text, String key) {
        return viginereCipher(text, key, false);
    }
    
    private String viginereCipher(String text, String key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        
        int keyLen = key.length();
        int keyIdx = 0;
        
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int shift = key.charAt(keyIdx % keyLen) - 'a';
                
                if (!encrypt) shift = 26 - shift;
                
                result.append((char) (base + (c - base + shift) % 26));
                
                keyIdx++;
            } else
                result.append(c);
        }
        
        return result.toString();
    }
}
