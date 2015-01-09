package hilburnlib.junit.test;

import hilburnlib.junit.minecraft.runner.MCTestRunner;
import hilburnlib.utils.LogHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MCTestRunner.class)
public class LogHelperTest
{
    @Test
    public void testFMLLogging()
    {
        LogHelper.debug("hi");
        LogHelper.info("test");
        LogHelper.warn("duh");
    }
}
