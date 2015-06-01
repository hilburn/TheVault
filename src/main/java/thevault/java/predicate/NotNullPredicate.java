package thevault.java.predicate;

/**
 * Predicate that checks if both are not null
 * @param <T>
 */
public class NotNullPredicate<T> implements Predicate<T>
{
    @Override
    public boolean compare(T a, T b)
    {
        return a != null && b != null;
    }
}
