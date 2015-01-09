package hilburnlib.junit.test;

import hilburnlib.config.Config;
import hilburnlib.config.ConfigProcessor;
import hilburnlib.junit.runner.MCTestRunner;
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
    public static int test2 = 45; //TODO Fix that the max is used to clamp
    
    public ConfigTest()
    {
        Configuration config = new Configuration(new File(CONFIG_FOLDER + MOD_ID + ".cfg"));
        ConfigProcessor.processAnnotations(MOD_ID, config, ConfigTest.class);
    }
    
    @Test
    public void testGetConfig()
    {
        ConfigProcessor.ModConfiguration modConfiguration = ConfigProcessor.getConfig(MOD_ID);
        assertArrayEquals(modConfiguration.getValue(CATEGORY, "testVar").getPropertyValue(), new String[]{ "true" });
        assertArrayEquals(modConfiguration.getValue(CATEGORY, "testVar2").getPropertyValue(), new String[]{ "30" });
    }
}
