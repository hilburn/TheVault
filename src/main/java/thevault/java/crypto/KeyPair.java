package thevault.java.crypto;

import java.math.BigInteger;

/**
 * Holder of a public and private key
 */
public class KeyPair
{
    public class Key
    {
        private BigInteger key;

        private Key(BigInteger key)
        {
            this.key = key;
        }

        private Key(byte[] key)
        {
            this.key = new BigInteger(key);
        }

        protected BigInteger getKeyBI()
        {
            return this.key;
        }

        public String getKey()
        {
            return new String(this.key.toByteArray());
        }
    }

    private Key privateKey;
    private Key publicKey;
    private BigInteger n;

    public KeyPair(String privateKey, String publicKey, BigInteger n)
    {
        this.privateKey = new Key(privateKey.getBytes());
        this.publicKey = new Key(publicKey.getBytes());
        this.n = n;
    }
    
    protected KeyPair(BigInteger privateKey, BigInteger publicKey, BigInteger n)
    {
        this.privateKey = new Key(privateKey);
        this.publicKey = new Key(publicKey);
        this.n = n;
    }

    public Key getPrivateKey()
    {
        return privateKey;
    }

    public Key getPublicKey()
    {
        return publicKey;
    }

    public BigInteger getN()
    {
        return n;
    }
}
