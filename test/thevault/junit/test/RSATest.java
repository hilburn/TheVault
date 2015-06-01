package thevault.junit.test;

import thevault.java.crypto.KeyPair;
import thevault.java.crypto.RSA;
import org.junit.Test;

import java.math.BigInteger;

import static thevault.junit.minecraft.Assert.*;

public class RSATest
{
    @Test
    public void testRSAPrivateEncrypt()
    {
        KeyPair keyPair = RSA.genKeyPair();
        String plainText = "Hello World";
        System.out.println(plainText);
        String cipherText = RSA.encrypt(plainText, keyPair.getPrivateKey(), keyPair.getN());
        System.out.println(cipherText);
        String decrypted = RSA.decrypt(cipherText, keyPair.getPublicKey(), keyPair.getN());
        System.out.println(decrypted);
        assertEquals(plainText, decrypted);
    }

    @Test
    public void testRSAPublicEncrypt()
    {
        KeyPair keyPair = RSA.genKeyPair();
        String plainText = "Hello World";
        System.out.println(plainText);
        String cipherText = RSA.encrypt(plainText, keyPair.getPublicKey(), keyPair.getN());
        System.out.println(cipherText);
        String decrypted = RSA.decrypt(cipherText, keyPair.getPrivateKey(), keyPair.getN());
        System.out.println(decrypted);
        assertEquals(plainText, decrypted);
    }
    
    @Test
    public void testBigIntToString()
    {
        String baseText = "Hello World";
        String bigInt = new String(new BigInteger(baseText.getBytes()).toByteArray());
        assertEquals(baseText, bigInt);
    }

    @Test
    public void testRSA1028PrivateEncrypt()
    {
        KeyPair keyPair = RSA.genKeyPair(1028);
        String plainText = "Hello World";
        System.out.println(plainText);
        String cipherText = RSA.encrypt(plainText, keyPair.getPrivateKey(), keyPair.getN());
        System.out.println(cipherText);
        String decrypted = RSA.decrypt(cipherText, keyPair.getPublicKey(), keyPair.getN());
        System.out.println(decrypted);
        assertEquals(plainText, decrypted);
    }

    @Test
    public void testRSA1028PublicEncrypt()
    {
        KeyPair keyPair = RSA.genKeyPair(1028);
        String plainText = "Hello World";
        System.out.println(plainText);
        String cipherText = RSA.encrypt(plainText, keyPair.getPublicKey(), keyPair.getN());
        System.out.println(cipherText);
        String decrypted = RSA.decrypt(cipherText, keyPair.getPrivateKey(), keyPair.getN());
        System.out.println(decrypted);
        assertEquals(plainText, decrypted);
    }
}
