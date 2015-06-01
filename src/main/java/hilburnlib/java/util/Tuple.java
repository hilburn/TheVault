package hilburnlib.java.util;

import java.util.Map;

/**
 * Storage object for 2 sub-objects
 * Can be constructed from a {@link java.util.Map.Entry}
 * Basically a better implementation of {@link net.minecraft.util.Tuple}
 * @param <V> type of the first object
 * @param <W> type of the second object
 */
public class Tuple<V, W>
{
    private V objV;
    private W objW;

    public Tuple(V objV, W objW)
    {
        this.objV = objV;
        this.objW = objW;
    }

    public Tuple(Map.Entry<V, W> entry)
    {
        this(entry.getKey(), entry.getValue());
    }

    /**
     * Set the first object
     * @param obj the object of type {@link V}
     */
    public void setFirst(V obj)
    {
        this.objV = obj;
    }

    /**
     * Set the second object
     * @param obj the object of type {@link W}
     */
    public void setSecond(W obj)
    {
        this.objW = obj;
    }

    /**
     * Get the first object
     * @return an object of type {@link V}
     */
    public V getFirst()
    {
        return objV;
    }

    /**
     * Get the first object
     * @return an object of type {@link W}
     */
    public W getSecond()
    {
        return objW;
    }
}