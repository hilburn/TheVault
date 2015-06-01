package thevault.junit.test;

import thevault.java.predicate.Operator;
import thevault.java.sort.MergeSort;
import org.junit.Test;

import static thevault.junit.minecraft.Assert.*;

public class MergeTest
{
    @Test
    public void testMergeSort()
    {
        MergeSort<Integer> integerSorter = new MergeSort<>(Integer.class, Operator.greaterThen);
        Integer[] array = integerSorter.sort(new Integer[]{5, 7, 9, 8});
        assertArrayEquals(new Integer[] {9, 8, 7, 5}, array);

        integerSorter = new MergeSort<>(Integer.class, Operator.lessThen);
        array = integerSorter.sort(new Integer[]{5, 7, 4, 6, 9, 8});
        assertArrayEquals(new Integer[] { 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    public void testMergeSortPrimitive()
    {
        int[] array = MergeSort.sort(new int[]{5, 7, 9, 8}, Operator.greaterThen);
        assertArrayEquals(new int[] {9, 8, 7, 5}, array);

        array = MergeSort.sort(new int[]{5, 7, 4, 6, 9, 8}, Operator.lessThen);
        assertArrayEquals(new int[] { 4, 5, 6, 7, 8, 9}, array);

        double[] doubles = MergeSort.sort(new double[]{5.6, 7.4, 9.1, 8.1}, Operator.greaterThen);
        assertArrayEquals(new double[]{9.1, 8.1, 7.4, 5.6}, doubles, 0);
    }
}
