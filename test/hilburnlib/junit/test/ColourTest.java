package hilburnlib.junit.test;

import hilburnlib.reference.Colours;
import org.junit.Assert;
import org.junit.Test;


public class ColourTest
{
    @Test
    public void testWhite()
    {
        Assert.assertEquals(Colours.WHITE, Colours.RGB(255, 255, 255));
    }

    @Test
    public void testBlack()
    {
        Assert.assertEquals(Colours.BLACK, Colours.RGB(0, 0, 0));
    }

    @Test
    public void testRed()
    {
        Assert.assertEquals(Colours.RED, Colours.RGB(255, 0, 0));
    }

    @Test
    public void testGreen()
    {
        Assert.assertEquals(Colours.GREEN, Colours.RGB(0, 255, 0));
    }
    
    @Test
    public void testBlue()
    {
        Assert.assertEquals(Colours.BLUE, Colours.RGB(0, 0, 255));
    }

    @Test
    public void testYellow()
    {
        Assert.assertEquals(Colours.YELLOW, Colours.RGB(255, 255, 0));
    }
}
