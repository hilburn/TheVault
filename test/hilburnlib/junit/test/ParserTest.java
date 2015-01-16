package hilburnlib.junit.test;

import hilburnlib.java.parsing.Parser;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest
{
    @Test
    public void testParserBasicInt()
    {
        assertEquals(25, Parser.doMath("12+13"), 0);
        assertEquals(2, Parser.doMath("15-13"), 0);
        assertEquals(6, Parser.doMath("12/2"), 0);
        assertEquals(24, Parser.doMath("12*2"), 0);
    }

    @Test
    public void testParserBasicDouble()
    {
        assertEquals(2.5, Parser.doMath("1.2+1.3"), 0.00000001);
        assertEquals(0.2, Parser.doMath("1.5-1.3"), 0.00000001);
        assertEquals(0.6, Parser.doMath("1.2/2"), 0.00000001);
        assertEquals(2.4, Parser.doMath("1.2*2"), 0.000000001);
    }
}
