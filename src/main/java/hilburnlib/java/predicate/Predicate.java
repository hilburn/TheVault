package hilburnlib.java.predicate;

/**
 * This is used to compare two objects
 *
 * @param <T> the type to compare
 */
public interface Predicate<T>
{
    public boolean compare(T a, T b);
}
