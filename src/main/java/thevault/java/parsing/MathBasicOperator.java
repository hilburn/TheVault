package thevault.java.parsing;

import java.util.HashMap;
import java.util.Map;

public enum MathBasicOperator implements MathOperator
{
    sum("+")
    {
        @Override
        public double apply(double... args)
        {
            return args[0] + args[1];
        }

        @Override
        public int priority()
        {
            return 0;
        }
    },

    difference("-")
    {
        @Override
        public double apply(double... args)
        {
            return args[0] - args[1];
        }

        @Override
        public int priority()
        {
            return 0;
        }
    },

    multiplication("*","x")
    {
        @Override
        public double apply(double... args)
        {
            return args[0] * args[1];
        }

        @Override
        public int priority()
        {
            return 1;
        }
    },

    division("/",":")
    {
        @Override
        public double apply(double... args)
        {
            return args[0] / args[1];
        }

        @Override
        public int priority()
        {
            return 1;
        }
    };

    private static Map<String, MathBasicOperator> mapping = new HashMap<>();
    private static boolean init = false;
    public String[] signs;
    private MathBasicOperator(String... signs)
    {
        this.signs = signs;
    }

    public static MathBasicOperator findBySign(String sign)
    {
        if (!init) init();
        return mapping.get(sign.toLowerCase());
    }
    
    private static void init()
    {
        for (MathBasicOperator op : values())
            for (String sign : op.signs)
                mapping.put(sign, op);
        init = true;
    }
    
    public int argsCount()
    {
        return 2;
    }
}
