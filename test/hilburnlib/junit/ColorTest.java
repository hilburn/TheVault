package hilburnlib.junit;

import hilburnlib.reference.Colors;
import org.junit.Assert;
import org.junit.Test;

public class ColorTest
{
    @Test
    public void testWhite()
    {
        Assert.assertEquals(Colors.WHITE, Colors.RGB(255, 255, 255));
    }

    @Test
    public void testBlack()
    {
        Assert.assertEquals(Colors.BLACK, Colors.RGB(0, 0, 0));
    }

    @Test
    public void testRed()
    {
        Assert.assertEquals(Colors.RED, Colors.RGB(255, 0, 0));
    }

    @Test
    public void testGreen()
    {
        Assert.assertEquals(Colors.GREEN, Colors.RGB(0, 255, 0));
    }
    
    @Test
    public void testBlue()
    {
        Assert.assertEquals(Colors.BLUE, Colors.RGB(0, 0, 255));
    }

    @Test
    public void testYellow()
    {
        Assert.assertEquals(Colors.YELLOW, Colors.RGB(255, 255, 0));
    }
}
