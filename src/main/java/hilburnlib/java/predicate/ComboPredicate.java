package hilburnlib.java.predicate;

/**
 * Makes a combo of given Predicates 
 * will iterate all and returns false if one fails
 * @param <T>
 */
public class ComboPredicate<T> implements Predicate<T>
{
    private Predicate[] predicates;

    public ComboPredicate(Predicate... predicates)
    {
        this.predicates = predicates;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean compare(T a, T b)
    {
        for (Predicate predicate : predicates)
            if (!predicate.compare(a, b)) return false;
        return true;
    }
}
