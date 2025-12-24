package io.github.galaxyvn.informationsecurity.week6.digitalsignature;

import java.security.*;
import java.util.Base64;

public class RSASignature {
    public static void main(String[] args) throws Exception {
        String text = "Hello, this is a message to be signed.";
        byte[] hashedText = SHA256Hashing.hash(text);
        KeyPair keyPair = generateRSAKeyPair();
        byte[] signature = sign(hashedText, keyPair.getPrivate());
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        boolean isVerified = verify(hashedText, signature, keyPair.getPublic());
        System.out.println("Signature verified: " + isVerified);
    }

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    public static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(signature);
    }
}
