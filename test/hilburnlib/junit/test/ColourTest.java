package hilburnlib.junit.test;

import hilburnlib.reference.Colours;
import org.junit.Test;

import static hilburnlib.junit.minecraft.Assert.*;


public class ColourTest
{
    @Test
    public void testWhite()
    {
        assertEquals(Colours.WHITE, Colours.RGB(255, 255, 255));
    }

    @Test
    public void testBlack()
    {
        assertEquals(Colours.BLACK, Colours.RGB(0, 0, 0));
    }

    @Test
    public void testRed()
    {
        assertEquals(Colours.RED, Colours.RGB(255, 0, 0));
    }

    @Test
    public void testGreen()
    {
        assertEquals(Colours.GREEN, Colours.RGB(0, 255, 0));
    }
    
    @Test
    public void testBlue()
    {
        assertEquals(Colours.BLUE, Colours.RGB(0, 0, 255));
    }

    @Test
    public void testYellow()
    {
        assertEquals(Colours.YELLOW, Colours.RGB(255, 255, 0));
    }
}
