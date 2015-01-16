package hilburnlib.java.parsing;

public interface MathOperator
{
    public double apply(double... args);
    public int argsCount();
    public int priority();
}
