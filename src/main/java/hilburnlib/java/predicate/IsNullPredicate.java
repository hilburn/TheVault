package hilburnlib.java.predicate;

public class IsNullPredicate<T> implements Predicate<T>
{
    @Override
    public boolean compare(T a, T b)
    {
        return a == null && b == null;
    }
}
