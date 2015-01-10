package hilburnlib.java.collection;

import hilburnlib.java.predicate.Predicate;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class CollectionHelper
{
    /**
     * Cast a collection to an array
     *
     * @param collection the collection to cast
     * @param <T>        the type of the collection contents
     * @return an array of type T
     */
    public static <T> T[] toArray(Collection<T> collection)
    {
        return (T[])collection.toArray(new Object[collection.size()]);
    }

    /**
     * Find elements in Collection matching toFind using the predicate
     *
     * @param toFind     element to match
     * @param collection the collection to search
     * @param predicate  the predicate used to match
     * @param <T>        the type of the contents of the collections
     * @return A collection containing all matching T elements
     */
    public static <T> Collection<T> find(T toFind, Collection<T> collection, Predicate predicate)
    {
        Collection<T> result = new LinkedList<>();
        for (T toCompare : collection)
            if (predicate.compare(toCompare, toFind)) result.add(toCompare);
        return result;
    }
}
