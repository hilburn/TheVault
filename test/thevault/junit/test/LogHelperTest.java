package thevault.junit.test;

import thevault.junit.minecraft.runner.MCTestRunner;
import thevault.utils.LogHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MCTestRunner.class)
public class LogHelperTest
{
    @Test
    public void testFMLLogging()
    {
        LogHelper.instance().debug("hi");
        LogHelper.instance().info("test");
        LogHelper.instance().warn("duh");
    }
}
