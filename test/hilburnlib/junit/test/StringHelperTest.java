package hilburnlib.junit.test;

import hilburnlib.java.string.StringHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringHelperTest
{
    @Test
    public void testContainsWord()
    {
        assertTrue(StringHelper.containsWord("Hello World", "World"));
        assertTrue(StringHelper.containsWord("Hello World", "world"));
        assertTrue(StringHelper.containsWord("Hello World", "heLLo"));
        assertFalse(StringHelper.containsWord("Hello World", "TEST"));
        assertFalse(StringHelper.containsWord("Hello World", "o"));
    }

    @Test
    public void testContainsWordStrict()
    {
        assertTrue(StringHelper.containsWordStrict("Hello World", "World"));
        assertTrue(StringHelper.containsWordStrict("Hello World", "Hello"));
        assertFalse(StringHelper.containsWordStrict("Hello World", "world"));
        assertFalse(StringHelper.containsWordStrict("Hello World", "heLLo"));
    }
}
