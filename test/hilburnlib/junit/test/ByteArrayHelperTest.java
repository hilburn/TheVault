package hilburnlib.junit.test;

import hilburnlib.utils.ByteArrayHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hilburnlib.junit.minecraft.Assert.*;

public class ByteArrayHelperTest
{
    @Test
    public void listTest()
    {
        List<String> test = new ArrayList<String>();
        test.add("woo");
        test.add("lego");
        byte[] array = ByteArrayHelper.toByteArray(test);
        List<String> result = ByteArrayHelper.fromByteArray(array);
        assertArrayEquals(test.toArray(), result.toArray());
    }

    @Test
    public void mapTest()
    {
        Map<String, String> test = new HashMap<String, String>();
        test.put("woo", "hoo");
        byte[] array = ByteArrayHelper.toByteArray(test);
        Map<String, String> result = ByteArrayHelper.fromByteArray(array);
        assertEquals(test.get("woo"), result.get("woo"));
    }
}
