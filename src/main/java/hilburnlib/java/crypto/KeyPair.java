package hilburnlib.java.crypto;

import java.math.BigInteger;

public class KeyPair
{
    public abstract class Key
    {
        public abstract String getKey();
        protected abstract BigInteger getKeyBI();
    }
    
    public class PrivateKey extends Key
    {
        private BigInteger key;

        private PrivateKey(BigInteger key)
        {
            this.key = key;
        }

        private PrivateKey(byte[] key)
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
    
    public class PublicKey extends Key
    {
        private BigInteger key;

        private PublicKey(BigInteger key)
        {
            this.key = key;
        }
        
        private PublicKey(byte[] key)
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
    
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private BigInteger n;

    public KeyPair(String privateKey, String publicKey, BigInteger n)
    {
        this.privateKey = new PrivateKey(privateKey.getBytes());
        this.publicKey = new PublicKey(publicKey.getBytes());
        this.n = n;
    }
    
    protected KeyPair(BigInteger privateKey, BigInteger publicKey, BigInteger n)
    {
        this.privateKey = new PrivateKey(privateKey);
        this.publicKey = new PublicKey(publicKey);
        this.n = n;
    }

    public PrivateKey getPrivateKey()
    {
        return privateKey;
    }

    public PublicKey getPublicKey()
    {
        return publicKey;
    }

    public BigInteger getN()
    {
        return n;
    }
}
