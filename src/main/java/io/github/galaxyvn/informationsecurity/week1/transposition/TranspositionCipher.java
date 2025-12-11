package io.github.galaxyvn.informationsecurity.week1.transposition;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class TranspositionCipher {

    public String encrypt(String text, int[] key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length);
        char[][] grid = new char[numRows][key.length];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }
        for (int i = 0; i < text.length(); i++) {
            grid[i / key.length][i % key.length] = text.charAt(i);
        }
        StringBuilder cipherText = new StringBuilder();
        for (int k : key) {
            for (int row = 0; row < numRows; row++) {
                if (grid[row][k - 1] != ' ') {
                    cipherText.append(grid[row][k - 1]);
                }
            }
        }
        return cipherText.toString();
    }
    
    public String decrypt(String text, int[] key) {
        int numCols = key.length;
        int numRows = (int) Math.ceil((double) text.length() / numCols);
        int fullCols = text.length() % numCols;
        if (fullCols == 0) {
            fullCols = numCols;
        }
        
        char[][] grid = new char[numRows][numCols];
        for (char[] row : grid) {
            Arrays.fill(row, '\0');
        }
        
        int textIndex = 0;
        for (int k : key) {
            int colIndex = k - 1;
            // Xác định số hàng trong cột này
            int rowsInThisCol = (colIndex < fullCols) ? numRows : numRows - 1;
            for (int row = 0; row < rowsInThisCol; row++) {
                if (textIndex < text.length()) {
                    grid[row][colIndex] = text.charAt(textIndex++);
                }
            }
        }
        
        StringBuilder plainText = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] != '\0') {
                    plainText.append(grid[row][col]);
                }
            }
        }
        return plainText.toString();
    }
}
