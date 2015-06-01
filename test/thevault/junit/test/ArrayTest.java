package thevault.junit.test;

import thevault.java.array.ArrayHelper;
import org.junit.Test;

import static thevault.junit.minecraft.Assert.*;

public class ArrayTest
{
    @Test
    public void testSubArrays()
    {
        Integer[] array = new Integer[] { 1, 2, 3, 4};
        assertArrayEquals(new Integer[] {2,3}, ArrayHelper.subArray(array, 1, 3));
        assertArrayEquals(new Integer[] {2,3,4}, ArrayHelper.subArrayOfSize(array, 1, 3));
        assertArrayEquals(new Integer[] {3,4}, ArrayHelper.subArrayFrom(array, 2));
        assertArrayEquals(new Integer[] {1,2}, ArrayHelper.subArray(array, 2));
    }
}
