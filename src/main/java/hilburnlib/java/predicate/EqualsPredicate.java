package hilburnlib.java.predicate;

public class EqualsPredicate<T> implements Predicate<T>
{

    @Override
    public boolean compare(T a, T b)
    {
        return a.equals(b);
    }
}
