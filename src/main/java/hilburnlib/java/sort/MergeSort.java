package hilburnlib.java.sort;

import hilburnlib.java.array.ArrayHelper;
import hilburnlib.java.predicate.Operator;
import hilburnlib.java.predicate.Predicate;

import java.lang.reflect.Array;

public class MergeSort<T>
{
    private Class<T> type;
    private Predicate predicate;

    public MergeSort(Class<T> type, Operator predicate)
    {
        this.type = type;
        if (predicate == Operator.equals || predicate == Operator.notEquals)
            throw new IllegalArgumentException("Can't use == or != to sort");
        this.predicate = predicate;
    }

    public T[] sort(T[] toSort)
    {
        if (toSort.length == 1) return toSort;

        int half = toSort.length / 2;
        T[] leftHalf = ArrayHelper.subArray(toSort, half);
        T[] rightHalf = ArrayHelper.subArrayFrom(toSort, half);

        leftHalf = sort(leftHalf);
        rightHalf = sort(rightHalf);

        return merge(leftHalf, rightHalf);
    }


    @SuppressWarnings("unchecked")
    public T[] merge(T[] left, T[] right)
    {
        int size = left.length + right.length;
        T[] merged = (T[])Array.newInstance(this.type, size);

        int i = 0;
        int ri = 0;
        int li = 0;

        while (i < size)
        {
            if ((li < left.length) && (ri < right.length))
            {
                if (predicate.compare(left[li], right[ri]))
                {
                    merged[i] = left[li];
                    i++;
                    li++;
                } else
                {
                    merged[i] = right[ri];
                    i++;
                    ri++;
                }
            } else
            {
                if (li >= left.length)
                {
                    while (ri < right.length)
                    {
                        merged[i] = right[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= right.length)
                {
                    while (li < left.length)
                    {
                        merged[i] = left[li];
                        i++;
                        li++;
                    }
                }
            }
        }

        return merged;
    }
    
    public int[] sort(int[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Integer[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }

    public double[] sort(double[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Double[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }

    public float[] sort(float[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Float[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }

    public byte[] sort(byte[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Byte[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }
    public short[] sort(short[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Short[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }

    public long[] sort(long[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Long[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }

    public boolean[] sort(boolean[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Boolean[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }

    public char[] sort(char[] toSort)
    {
        return ArrayHelper.toPrimitiveArray((Character[]) sort((T[]) ArrayHelper.toObjectArray(toSort)));
    }
    
}
