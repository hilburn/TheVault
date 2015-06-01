package thevault.java.crypto;

import java.math.BigInteger;
import java.util.Random;

public class RSA
{
    private static BigInteger ONE = BigInteger.ONE;
    private static BigInteger TWO = new BigInteger("2");
    private static BigInteger THREE = new BigInteger("3");

    /**
     * Gens an RSA KeyPair with bits as length
     * @return
     */
    public static KeyPair genKeyPair()
    {
        return genKeyPair(128);
    }

    /**
     * Gen a new {@link KeyPair} for use with RSA
     * @param size amount of bytes in the base numbers
     * @return the KeyPair
     */
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

    /**
     * encrypt a text with given key and N 
     * @param plainText the to encrypt text
     * @param key the key to use
     * @param n the N of given key
     * @return the encrypted string
     */
    public static String encrypt(String plainText, KeyPair.Key key, BigInteger n)
    {
        return (new BigInteger(plainText.getBytes())).modPow(key.getKeyBI(), n).toString();
    }

    /**
     * decrypt a given cipher text wit given key and N 
     * @param cipherText the to decrypt cipher text
     * @param key the key to use
     * @param n the N of given key
     * @return the decrypted string
     */
    public static String decrypt(String cipherText, KeyPair.Key key, BigInteger n)
    {
        return new String((new BigInteger(cipherText)).modPow(key.getKeyBI(), n).toByteArray());
    }
}
