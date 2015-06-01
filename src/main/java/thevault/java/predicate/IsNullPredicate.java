package thevault.java.predicate;

/**
 * Predicate that checks if both are null
 * @param <T>
 */
public class IsNullPredicate<T> implements Predicate<T>
{
    @Override
    public boolean compare(T a, T b)
    {
        return a == null && b == null;
    }
}
