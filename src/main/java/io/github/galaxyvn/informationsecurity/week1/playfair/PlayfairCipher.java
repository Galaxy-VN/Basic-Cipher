package io.github.galaxyvn.informationsecurity.week1.playfair;

import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class PlayfairCipher {
    private char[][] keyMatrix;
    private String key;

    public PlayfairCipher(String key) {
        this.key = key;
        this.keyMatrix = generateKeyMatrix(key);
    }

    private char[][] generateKeyMatrix(String key) {
        char[][] matrix = new char[5][5];
        String keyString = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        
        StringBuilder keyBuilder = new StringBuilder();
        boolean[] used = new boolean[26];

        for (char c : keyString.toCharArray()) {
            if (!used[c - 'A']) {
                keyBuilder.append(c);
                used[c - 'A'] = true;
            }
        }

        for (char c : alphabet.toCharArray()) {
            if (!used[c - 'A']) {
                keyBuilder.append(c);
                used[c - 'A'] = true;
            }
        }

        int index = 0;
        String finalKey = keyBuilder.toString();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = finalKey.charAt(index++);
            }
        }
        return matrix;
    }

    private String formatText(String text) {
        String clean = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < clean.length()) {
            char first = clean.charAt(i);
            
            if (i + 1 < clean.length()) {
                char second = clean.charAt(i + 1);
                if (first == second) {
                    sb.append(first);
                    sb.append(first == 'X' ? 'Q' : 'X');
                    i++;
                } else {
                    sb.append(first);
                    sb.append(second);
                    i += 2;
                }
            } else {
                sb.append(first);
                i++;
            }
        }

        if (sb.length() % 2 != 0) {
            char last = sb.charAt(sb.length() - 1);
            sb.append(last == 'X' ? 'Q' : 'X');
        }

        return sb.toString();
    }

    private Point getPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public String encrypt(String text) {
        String preparedText = formatText(text);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < preparedText.length(); i += 2) {
            char a = preparedText.charAt(i);
            char b = preparedText.charAt(i + 1);

            Point p1 = getPosition(a);
            Point p2 = getPosition(b);

            int r1 = p1.x;
            int c1 = p1.y; 
            int r2 = p2.x;
            int c2 = p2.y;

            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            result.append(keyMatrix[r1][c1]);
            result.append(keyMatrix[r2][c2]);
        }
        return result.toString();
    }

    public String decrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            Point p1 = getPosition(a);
            Point p2 = getPosition(b);

            int r1 = p1.x;
            int c1 = p1.y;
            int r2 = p2.x;
            int c2 = p2.y;

            if (r1 == r2) {
                c1 = (c1 - 1 + 5) % 5;
                c2 = (c2 - 1 + 5) % 5;
            } else if (c1 == c2) {
                r1 = (r1 - 1 + 5) % 5;
                r2 = (r2 - 1 + 5) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            result.append(keyMatrix[r1][c1]);
            result.append(keyMatrix[r2][c2]);
        }
        return result.toString();
    }
    
    public String getKeyMatrix() {
        StringBuilder matrix = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix.append(keyMatrix[i][j]).append(" ");
            }
            matrix.append(System.lineSeparator());
        }
        return matrix.toString();
    }
}
