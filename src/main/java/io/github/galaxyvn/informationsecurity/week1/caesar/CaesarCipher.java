/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.galaxyvn.informationsecurity.week1.caesar;

/**
 *
 * @author Administrator
 */
public class CaesarCipher {
    public static String encrypt(String text, int key) {
        return caeserCipher(text, key, true);
    }
    
    public static String decrypt(String text, int key) {
        return caeserCipher(text, key, false);
    }
    
    private static String caeserCipher(String text, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int shift = encrypt ? key : -key;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int offset = (c-base+shift)%26;
                if (offset<0)offset+=26;
                result.append((char)(base+offset));
            } else 
                result.append(c);
        }
        return result.toString();
    }
}
