package thevault.java.sort;

import thevault.java.array.ArrayHelper;
import thevault.java.predicate.Operator;
import thevault.java.predicate.Predicate;

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
    
    public static int[] sort(int[] toSort, Operator operator)
    {
        MergeSort<Integer> sorter = new MergeSort<>(Integer.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort((ArrayHelper.toObjectArray(toSort))));
    }

    public static double[] sort(double[] toSort, Operator operator)
    {
        MergeSort<Double> sorter = new MergeSort<>(Double.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }

    public static float[] sort(float[] toSort, Operator operator)
    {
        MergeSort<Float> sorter = new MergeSort<>(Float.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }

    public static byte[] sort(byte[] toSort, Operator operator)
    {
        MergeSort<Byte> sorter = new MergeSort<>(Byte.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }
    public static short[] sort(short[] toSort, Operator operator)
    {
        MergeSort<Short> sorter = new MergeSort<>(Short.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }

    public static long[] sort(long[] toSort, Operator operator)
    {
        MergeSort<Long> sorter = new MergeSort<>(Long.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }

    public static boolean[] sort(boolean[] toSort, Operator operator)
    {
        MergeSort<Boolean> sorter = new MergeSort<>(Boolean.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }

    public static char[] sort(char[] toSort, Operator operator)
    {
        MergeSort<Character> sorter = new MergeSort<>(Character.class, operator);
        return ArrayHelper.toPrimitiveArray(sorter.sort(ArrayHelper.toObjectArray(toSort)));
    }
    
}
