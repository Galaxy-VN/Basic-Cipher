package io.github.galaxyvn.informationsecurity.week6.digitalsignature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hashing {
    public static byte[] hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes());
    }
}
