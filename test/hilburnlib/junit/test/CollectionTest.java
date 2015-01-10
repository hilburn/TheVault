package hilburnlib.junit.test;

import hilburnlib.java.collection.CollectionHelper;
import hilburnlib.java.predicate.Operator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static hilburnlib.junit.minecraft.Assert.*;

public class CollectionTest
{
    @Test
    public void testToArray()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(5);
        assertArrayEquals(new Integer[] {1,4,5}, CollectionHelper.toArray(list));
    }
    
    @Test
    public void testFind()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(5);
        assertArrayEquals(new Integer[] {4, 5}, CollectionHelper.toArray(CollectionHelper.find(3, list, Operator.greaterThen)));
        assertArrayEquals(new Integer[] {1}, CollectionHelper.toArray(CollectionHelper.find(3, list, Operator.lessThen)));
    }
}
