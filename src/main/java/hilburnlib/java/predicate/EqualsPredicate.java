package hilburnlib.java.predicate;

/**
 * Predicate that checks if both are equal
 * @param <T>
 */
public class EqualsPredicate<T> implements Predicate<T>
{

    @Override
    public boolean compare(T a, T b)
    {
        return a.equals(b);
    }
}
