package hilburnlib.junit;

import hilburnlib.reference.Constants;
import org.junit.Assert;
import org.junit.Test;

public class ColorTest
{
    @Test
    public void testWhite()
    {
        Assert.assertEquals(Constants.Color.WHITE, Constants.Color.RGB(255, 255, 255));
    }

    @Test
    public void testBlack()
    {
        Assert.assertEquals(Constants.Color.BLACK, Constants.Color.RGB(0, 0, 0));
    }

    @Test
    public void testRed()
    {
        Assert.assertEquals(Constants.Color.RED, Constants.Color.RGB(255, 0, 0));
    }
    
    @Test
    public void testGreen()
    {
        Assert.assertEquals(Constants.Color.GREEN, Constants.Color.RGB(0, 255, 0));
    }
    
    @Test
    public void testBlue()
    {
        Assert.assertEquals(Constants.Color.BLUE, Constants.Color.RGB(0, 0, 255));
    }

    @Test
    public void testYellow()
    {
        Assert.assertEquals(Constants.Color.YELLOW, Constants.Color.RGB(255, 255, 0));
    }
}
