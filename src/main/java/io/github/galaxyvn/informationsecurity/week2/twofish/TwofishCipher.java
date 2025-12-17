/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.galaxyvn.informationsecurity.week2.twofish;

import java.nio.charset.StandardCharsets;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/**
 *
 * @author Administrator
 */
public class TwofishCipher {
    private static final int BLOCK_SIZE = 16;
    private BlockCipher cipher = new CBCBlockCipher(new TwofishEngine());
    
    public byte[] encrypt(String message, byte[] key, byte[] iv) throws Exception {
        PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(cipher);
        ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(key), iv);
        paddedCipher.init(true, parameters);
        
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        int minSize = paddedCipher.getOutputSize(messageBytes.length);
        byte[] outBuf = new byte[minSize];
        int length1 = paddedCipher.processBytes(messageBytes, 0, messageBytes.length, outBuf, 0);
        int length2 = paddedCipher.doFinal(outBuf, length1);
        
        byte[] encrypted = new byte[length1 + length2];
        System.arraycopy(outBuf, 0, encrypted, 0, encrypted.length);
        return encrypted;
    }
    
    public String decrypt(byte[] encrypted, byte[] key, byte[] iv) throws Exception {
        PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(cipher);
        ParametersWithIV parameters = new ParametersWithIV(new KeyParameter(key), iv);
        paddedCipher.init(false, parameters);
        
        int minSize = paddedCipher.getOutputSize(encrypted.length);
        byte[] outBuf = new byte[minSize];
        int length1 = paddedCipher.processBytes(encrypted, 0, encrypted.length, outBuf, 0);
        int length2 = paddedCipher.doFinal(outBuf, length1);
        
        byte[] messageBytes = new byte[length1 + length2];
        System.arraycopy(outBuf, 0, messageBytes, 0, messageBytes.length);
        return new String(messageBytes, StandardCharsets.UTF_8);
    }
   
}
