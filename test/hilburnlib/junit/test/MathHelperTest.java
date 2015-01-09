package hilburnlib.junit.test;

import hilburnlib.java.math.MathHelper;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathHelperTest
{

    @Test
    public void testClamp() throws Exception
    {
        Assert.assertEquals(MathHelper.clamp(-1, 0, 1), 0);
        Assert.assertEquals(MathHelper.clamp(5, 0, 1), 1);
        Assert.assertEquals(MathHelper.clamp(-1,0.5,1),0.5);
    }
}