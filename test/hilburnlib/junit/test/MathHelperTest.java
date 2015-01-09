package hilburnlib.junit.test;

import hilburnlib.java.math.MathHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathHelperTest
{

    @Test
    public void testClamp() throws Exception
    {
        assertEquals(MathHelper.clamp(-1, 0, 1), 0);
        assertEquals(MathHelper.clamp(5, 0, 1), 1);
        assertEquals(MathHelper.clamp(-1,0.5,1),0.5, 0);
    }
}