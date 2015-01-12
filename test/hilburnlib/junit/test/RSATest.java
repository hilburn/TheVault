package hilburnlib.junit.test;

import hilburnlib.java.crypto.RSA;
import org.junit.Test;

import java.math.BigInteger;

import static hilburnlib.junit.minecraft.Assert.*;

public class RSATest
{
    @Test
    public void testRSA()
    {
        RSA rsa = new RSA();
        String plainText = "Hello World";
        System.out.println(plainText);
        String cipherText = rsa.encrypt(plainText);
        System.out.println(cipherText);
        String decrypted = rsa.decrypt(cipherText);
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
    public void testRSA1028()
    {
        RSA rsa = new RSA(1028);
        String plainText = "Hello World";
        System.out.println(plainText);
        String cipherText = rsa.encrypt(plainText);
        System.out.println(cipherText);
        String decrypted = rsa.decrypt(cipherText);
        System.out.println(decrypted);
        assertEquals(plainText, decrypted);
    }
}
