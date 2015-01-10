package hilburnlib.junit.test;

import hilburnlib.java.predicate.Operator;
import hilburnlib.java.sort.MergeSort;
import org.junit.Test;

import static hilburnlib.junit.minecraft.Assert.*;

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
}
