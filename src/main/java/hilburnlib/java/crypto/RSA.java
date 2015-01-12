package hilburnlib.java.crypto;

import java.math.BigInteger;
import java.util.Random;

public class RSA
{
    private static BigInteger ONE = BigInteger.ONE;
    private static BigInteger TWO = new BigInteger("2");
    private static BigInteger THREE = new BigInteger("3");

    public static KeyPair genKeyPair()
    {
        return genKeyPair(128);
    }
    
    public static KeyPair genKeyPair(int size)
    {
        // Select two prime numbers
        BigInteger p = BigInteger.probablePrime(size, new Random());
        BigInteger q = BigInteger.probablePrime(size, new Random());
        // Calc z = (p - 1)*(q - 1)
        BigInteger z = (p.subtract(ONE)).multiply((q.subtract(ONE)));
        // Find e so that gcd(e, z) = 1
        BigInteger e = THREE;
        while (z.gcd(e).compareTo(ONE) != 0)
            e = e.add(TWO);
        // Calc d so that ed = 1 / (mod e)
        BigInteger d = e.modInverse(z);
        return new KeyPair(e, d, p.multiply(q));
    }
    
    public static String encrypt(String plainText, KeyPair.Key key, BigInteger n)
    {
        return (new BigInteger(plainText.getBytes())).modPow(key.getKeyBI(), n).toString();
    }
    
    public static String decrypt(String cipherText, KeyPair.Key key, BigInteger n)
    {
        return new String((new BigInteger(cipherText)).modPow(key.getKeyBI(), n).toByteArray());
    }
}
