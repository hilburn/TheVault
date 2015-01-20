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
        assertEquals(1.0F, Colours.getRed(Colours.WHITE), 0);
        assertEquals(1.0F, Colours.getGreen(Colours.WHITE), 0);
        assertEquals(1.0F, Colours.getBlue(Colours.WHITE), 0);
    }

    @Test
    public void testBlack()
    {
        assertEquals(Colours.BLACK, Colours.RGB(0, 0, 0));
        assertEquals(0, Colours.getRed(Colours.BLACK), 0);
        assertEquals(0, Colours.getGreen(Colours.BLACK), 0);
        assertEquals(0, Colours.getBlue(Colours.BLACK), 0);
    }

    @Test
    public void testRed()
    {
        assertEquals(Colours.RED, Colours.RGB(255, 0, 0));
        assertEquals(1.0F, Colours.getRed(Colours.RED), 0);
        assertEquals(0, Colours.getGreen(Colours.RED), 0);
        assertEquals(0, Colours.getBlue(Colours.RED), 0);
    }

    @Test
    public void testGreen()
    {
        assertEquals(Colours.GREEN, Colours.RGB(0, 255, 0));
        assertEquals(0, Colours.getRed(Colours.GREEN), 0);
        assertEquals(1.0F, Colours.getGreen(Colours.GREEN), 0);
        assertEquals(0, Colours.getBlue(Colours.GREEN), 0);
    }
    
    @Test
    public void testBlue()
    {
        assertEquals(Colours.BLUE, Colours.RGB(0, 0, 255));
        assertEquals(0, Colours.getRed(Colours.BLUE), 0);
        assertEquals(0, Colours.getGreen(Colours.BLUE), 0);
        assertEquals(1.0F, Colours.getBlue(Colours.BLUE), 0);
    }

    @Test
    public void testYellow()
    {
        assertEquals(Colours.YELLOW, Colours.RGB(255, 255, 0));
        assertEquals(1.0F, Colours.getRed(Colours.YELLOW), 0);
        assertEquals(1.0F, Colours.getGreen(Colours.YELLOW), 0);
        assertEquals(0, Colours.getBlue(Colours.YELLOW), 0);
    }
}
