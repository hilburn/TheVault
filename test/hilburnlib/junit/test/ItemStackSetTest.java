package hilburnlib.junit.test;

import hilburnlib.collections.ItemStackSet;
import hilburnlib.junit.minecraft.runner.MCTestRunner;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(MCTestRunner.class)
public class ItemStackSetTest
{

    @Test
    public void testHashing()
    {
        ItemStackSet testSet = new ItemStackSet();
        testSet.add(new ItemStack(Items.blaze_powder));
        assertTrue(testSet.contains(new ItemStack(Items.blaze_powder)));
        assertFalse(testSet.contains(new ItemStack(Items.carrot_on_a_stick)));

        testSet = new ItemStackSet(true);
        testSet.add(new ItemStack(Items.blaze_powder));
        assertTrue(testSet.contains(new ItemStack(Items.blaze_powder,5)));
    }
}