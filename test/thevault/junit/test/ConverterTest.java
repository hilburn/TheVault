package thevault.junit.test;

import thevault.utils.Converter;
import org.junit.Test;

import static thevault.junit.minecraft.Assert.*;

public class ConverterTest
{
    @Test
    public void intConverterTest()
    {
        assertEquals(12, Converter.INT_CONVERTER.readFromString("12"), 0);
        assertEquals(200, Converter.INT_CONVERTER.readFromString("200"), 0);
        assertEquals(-120, Converter.INT_CONVERTER.readFromString("-120"), 0);
        try
        {
            Converter.INT_CONVERTER.readFromString("kljqdsf");
            fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.INT_CONVERTER.readFromString("10000000000000000000000000000000000000000000000000000000000");
            fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void boolConverterTest()
    {
        assertEquals(false, Converter.BOOL_CONVERTER.readFromString("falSe"));
        assertEquals(true, Converter.BOOL_CONVERTER.readFromString("tRue"));
        assertEquals(false, Converter.BOOL_CONVERTER.readFromString("false"));
        assertEquals(true, Converter.BOOL_CONVERTER.readFromString("TrUE"));
        assertEquals(false, Converter.BOOL_CONVERTER.readFromString("13"));
        assertEquals(false, Converter.BOOL_CONVERTER.readFromString("qsdfq"));
        assertEquals(false, Converter.BOOL_CONVERTER.readFromString("156"));
    }

    @Test
    public void byteConverterTest()
    {
        assertEquals(1, Converter.BYTE_CONVERTER.readFromString("1"), 0);
        assertEquals(12, Converter.BYTE_CONVERTER.readFromString("12"), 0);
        assertEquals(-120, Converter.BYTE_CONVERTER.readFromString("-120"), 0);
        assertEquals(0, Converter.BYTE_CONVERTER.readFromString("0"), 0);
        assertEquals(50, Converter.BYTE_CONVERTER.readFromString("50"), 0);
        try
        {
            Converter.BYTE_CONVERTER.readFromString("-1000");
            fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.BYTE_CONVERTER.readFromString("1236");
            fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void floatConverterTest()
    {
        assertEquals(12.2F, Converter.FLOAT_CONVERTER.readFromString("12.2"), 0);
        assertEquals(200.0F, Converter.FLOAT_CONVERTER.readFromString("200.0"), 0);
        assertEquals(-120.0F, Converter.FLOAT_CONVERTER.readFromString("-120.0"), 0);
        try
        {
            Converter.FLOAT_CONVERTER.readFromString("kljqdsf");
            fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void doubleConverterTest()
    {
        assertEquals(12.2D, Converter.DOUBLE_CONVERTER.readFromString("12.2"), 0);
        assertEquals(200.0D, Converter.DOUBLE_CONVERTER.readFromString("200.0"), 0);
        assertEquals(-120.0D, Converter.DOUBLE_CONVERTER.readFromString("-120.0"), 0);
        try
        {
            Converter.DOUBLE_CONVERTER.readFromString("kljqdsf");
            fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void longConverterTest()
    {
        assertEquals(12L, Converter.LONG_CONVERTER.readFromString("12"), 0);
        assertEquals(200L, Converter.LONG_CONVERTER.readFromString("200"), 0);
        assertEquals(-120L, Converter.LONG_CONVERTER.readFromString("-120"), 0);
        try
        {
            Converter.LONG_CONVERTER.readFromString("kljqdsf");
            fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void shortConverterTest()
    {
        assertEquals(12, Converter.SHORT_CONVERTER.readFromString("12"), 0);
        assertEquals(200, Converter.SHORT_CONVERTER.readFromString("200"), 0);
        assertEquals(-120, Converter.SHORT_CONVERTER.readFromString("-120"), 0);
        try
        {
            Converter.SHORT_CONVERTER.readFromString("kljqdsf");
            fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.SHORT_CONVERTER.readFromString("-32768123");
            fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.SHORT_CONVERTER.readFromString("32768123");
            fail();
        } catch (NumberFormatException ignored) {}
    }
}
