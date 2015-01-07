package hilburnlib.junit;

import hilburnlib.utils.Converter;
import org.junit.Assert;
import org.junit.Test;

public class ConverterTest
{
    @Test
    public void intConverterTest()
    {
        Assert.assertEquals(12, Converter.INT_CONVERTER.readFromString("12"), 0);
        Assert.assertEquals(200, Converter.INT_CONVERTER.readFromString("200"), 0);
        Assert.assertEquals(-120, Converter.INT_CONVERTER.readFromString("-120"), 0);
        try
        {
            Converter.INT_CONVERTER.readFromString("kljqdsf");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.INT_CONVERTER.readFromString("10000000000000000000000000000000000000000000000000000000000");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void boolConverterTest()
    {
        Assert.assertEquals(false, Converter.BOOL_CONVERTER.readFromString("falSe"));
        Assert.assertEquals(true, Converter.BOOL_CONVERTER.readFromString("tRue"));
        Assert.assertEquals(false, Converter.BOOL_CONVERTER.readFromString("false"));
        Assert.assertEquals(true, Converter.BOOL_CONVERTER.readFromString("TrUE"));
        Assert.assertEquals(false, Converter.BOOL_CONVERTER.readFromString("13"));
        Assert.assertEquals(false, Converter.BOOL_CONVERTER.readFromString("qsdfq"));
        Assert.assertEquals(false, Converter.BOOL_CONVERTER.readFromString("156"));
    }

    @Test
    public void byteConverterTest()
    {
        Assert.assertEquals(1, Converter.BYTE_CONVERTER.readFromString("1"), 0);
        Assert.assertEquals(12, Converter.BYTE_CONVERTER.readFromString("12"), 0);
        Assert.assertEquals(-120, Converter.BYTE_CONVERTER.readFromString("-120"), 0);
        Assert.assertEquals(0, Converter.BYTE_CONVERTER.readFromString("0"), 0);
        Assert.assertEquals(50, Converter.BYTE_CONVERTER.readFromString("50"), 0);
        try
        {
            Converter.BYTE_CONVERTER.readFromString("-1000");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.BYTE_CONVERTER.readFromString("1236");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void floatConverterTest()
    {
        Assert.assertEquals(12.2F, Converter.FLOAT_CONVERTER.readFromString("12.2"), 0);
        Assert.assertEquals(200.0F, Converter.FLOAT_CONVERTER.readFromString("200.0"), 0);
        Assert.assertEquals(-120.0F, Converter.FLOAT_CONVERTER.readFromString("-120.0"), 0);
        try
        {
            Converter.FLOAT_CONVERTER.readFromString("kljqdsf");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void doubleConverterTest()
    {
        Assert.assertEquals(12.2D, Converter.DOUBLE_CONVERTER.readFromString("12.2"), 0);
        Assert.assertEquals(200.0D, Converter.DOUBLE_CONVERTER.readFromString("200.0"), 0);
        Assert.assertEquals(-120.0D, Converter.DOUBLE_CONVERTER.readFromString("-120.0"), 0);
        try
        {
            Converter.DOUBLE_CONVERTER.readFromString("kljqdsf");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void longConverterTest()
    {
        Assert.assertEquals(12L, Converter.LONG_CONVERTER.readFromString("12"), 0);
        Assert.assertEquals(200L, Converter.LONG_CONVERTER.readFromString("200"), 0);
        Assert.assertEquals(-120L, Converter.LONG_CONVERTER.readFromString("-120"), 0);
        try
        {
            Converter.LONG_CONVERTER.readFromString("kljqdsf");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void shortConverterTest()
    {
        Assert.assertEquals(12, Converter.SHORT_CONVERTER.readFromString("12"), 0);
        Assert.assertEquals(200, Converter.SHORT_CONVERTER.readFromString("200"), 0);
        Assert.assertEquals(-120, Converter.SHORT_CONVERTER.readFromString("-120"), 0);
        try
        {
            Converter.SHORT_CONVERTER.readFromString("kljqdsf");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.SHORT_CONVERTER.readFromString("-32768123");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
        try
        {
            Converter.SHORT_CONVERTER.readFromString("32768123");
            Assert.fail();
        } catch (NumberFormatException ignored) {}
    }
}
