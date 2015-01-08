package hilburnlib.java.collection;

import java.util.Collection;

public class CollectionHelper
{
    public static <T> T[] toArray(Collection<T> collection)
    {
        return (T[]) collection.toArray(new Object[collection.size()]);
    }

}
