package hilburnlib.junit.test;

import hilburnlib.nbt.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;
import org.junit.Test;

import static org.junit.Assert.*;

public class NBTTest
{
    @Test
    public void testString()
    {
        String value = "test";
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        String result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result);
    }

    @Test
    public void testInt()
    {
        int value = 612;
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        int result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result);
    }

    @Test
    public void testLong()
    {
        long value = 4685413;
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        long result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result);
    }

    @Test
    public void testDouble()
    {
        double value = 4685413.065132D;
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        double result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result, 0);
    }

    @Test
    public void testFloat()
    {
        float value = 123.561F;
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        float result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result, 0);
    }

    @Test
    public void testByte()
    {
        byte value = 12;
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        byte result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result);
    }

    @Test
    public void testBool()
    {
        boolean value = true;
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        boolean result = NBTHelper.readFromNBT(tag);
        assertEquals(value, result);
    }

    @Test
    public void testIntArray()
    {
        int[] value = new int[] { 612, 123, 48 };
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        int[] result = NBTHelper.readFromNBT(tag);
        assertArrayEquals(value, result);
    }

    @Test
    public void testStringArray()
    {
        String[] value = new String[] { "test", "hello", "world" };
        NBTTagCompound tag = NBTHelper.writeToNBT(value);
        String[] result = NBTHelper.readFromNBT(tag);
        assertArrayEquals(value, result);
    }
}
