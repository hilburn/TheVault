package thevault.java.predicate;

/**
 * Predicates for the default operators
 */
public enum Operator implements Predicate<Number>
{
    greaterThen(">")
            {
                @Override
                public boolean compare(Number a, Number b)
                {
                    return a.doubleValue() > b.doubleValue();
                }
            },
    greaterThanOrEqual(">=")
            {
                @Override
                public boolean compare(Number a, Number b)
                {
                    return a.doubleValue() >= b.doubleValue();
                }
            },
    lessThen("<")
            {
                @Override
                public boolean compare(Number a, Number b)
                {
                    return a.doubleValue() < b.doubleValue();
                }
            },
    lessThanOrEqual("<=")
            {
                @Override
                public boolean compare(Number a, Number b)
                {
                    return a.doubleValue() <= b.doubleValue();
                }
            },
    equals("==")
            {
                @Override
                public boolean compare(Number a, Number b)
                {
                    return a.doubleValue() == b.doubleValue();
                }
            },
    notEquals("!=")
            {
                @Override
                public boolean compare(Number a, Number b)
                {
                    return a.doubleValue() != b.doubleValue();
                }
            };

    private String symbol;

    private Operator(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol()
    {
        return symbol;
    }
}
