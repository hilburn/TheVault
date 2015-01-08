package hilburnlib.junit.test;

import hilburnlib.java.string.StringHelper;
import org.junit.Assert;
import org.junit.Test;

public class StringHelperTest
{
    @Test
    public void testContainsWord()
    {
        Assert.assertTrue(StringHelper.containsWord("Hello World", "World"));
        Assert.assertTrue(StringHelper.containsWord("Hello World", "world"));
        Assert.assertTrue(StringHelper.containsWord("Hello World", "heLLo"));
        Assert.assertFalse(StringHelper.containsWord("Hello World", "TEST"));
        Assert.assertFalse(StringHelper.containsWord("Hello World", "o"));


        Assert.assertTrue(StringHelper.containsWordStrict("Hello World", "World"));
        Assert.assertFalse(StringHelper.containsWordStrict("Hello World", "world"));
        Assert.assertFalse(StringHelper.containsWordStrict("Hello World", "heLLo"));
        Assert.assertFalse(StringHelper.containsWordStrict("Hello World", "TEST"));
        Assert.assertFalse(StringHelper.containsWordStrict("Hello World", "o"));
    }
}
