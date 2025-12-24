package io.github.galaxyvn.informationsecurity.week6.digitalsignature;

import java.security.*;

public class GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("Public Key: " + publicKey.getEncoded());
        System.out.println("Private Key: " + privateKey.getEncoded());
    }
}
