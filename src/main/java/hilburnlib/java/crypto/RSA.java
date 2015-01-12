package hilburnlib.java.crypto;

import java.math.BigInteger;
import java.util.Random;

public class RSA
{
    private static BigInteger ONE = BigInteger.ONE;
    private static BigInteger TWO = new BigInteger("2");
    private static BigInteger THREE = new BigInteger("3");
    
    private BigInteger n;
    private BigInteger e,d;
    
    public RSA()
    {
        this(128);
    }
    
    public RSA(int size)
    {
        // Select two prime numbers
        BigInteger p = BigInteger.probablePrime(size, new Random());
        BigInteger q = BigInteger.probablePrime(size, new Random());
        // Calc n = pq
        n = p.multiply(q);
        // Calc z = (p - 1)*(q - 1)
        BigInteger z = (p.subtract(ONE)).multiply((q.subtract(ONE)));
        // Find e so that gcd(e, z) = 1
        e = THREE;
        while (z.gcd(e).compareTo(ONE) != 0)
            e = e.add(TWO);
        // Calc d so that ed = 1 / (mod e)
        d = e.modInverse(z);
    }
    
    public String encrypt(String plainText)
    {
        return (new BigInteger(plainText.getBytes())).modPow(e, n).toString();
    }
    
    public String decrypt(String cipherText)
    {
        return new String((new BigInteger(cipherText)).modPow(d, n).toByteArray());
    }
}
