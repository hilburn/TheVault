package hilburnlib.junit.test;

import hilburnlib.config.Config;
import hilburnlib.config.ConfigProcessor;
import hilburnlib.junit.minecraft.runner.MCTestRunner;
import net.minecraftforge.common.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(MCTestRunner.class)
public class ConfigTest
{
    private final static String CONFIG_FOLDER = "run\\config\\";
    private final static String MOD_ID = "testMod";
    private final static String CATEGORY = "test category";
    
    @Config(name = "testVar", category = CATEGORY)
    public static boolean test = true;

    @Config(name = "testVar2", category = CATEGORY, max = 30)
    public static int test2 = 45;

    @Config(name = "testVar3", category = CATEGORY, validValues = {"10","20","30"})
    public static int test3 = 30;

    @Config(min = 200D)
    public static double[] argieBargie = new double[]{300D,378D,0.1D};
    
    public ConfigTest()
    {
        Configuration config = new Configuration(new File(CONFIG_FOLDER + MOD_ID + ".cfg"));
        ConfigProcessor.processAnnotations(MOD_ID, config, ConfigTest.class);
    }
    
    @Test
    public void testGetConfig()
    {
        ConfigProcessor.ModConfiguration modConfiguration = ConfigProcessor.getConfig(MOD_ID);
        assertFalse((Boolean) modConfiguration.getValue(CATEGORY, "testVar").getValue());
        assertEquals(modConfiguration.getValue(CATEGORY, "testVar2").getValue(), 30);
        assertEquals(test3, 30);
        assertArrayEquals(argieBargie, new double[]{300D,378D,200D},0.01);
    }
}
