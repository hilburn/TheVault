package hilburnlib.junit.test;

import hilburnlib.config.Config;
import hilburnlib.config.ConfigProcessor;
import net.minecraftforge.common.config.Configuration;
import org.junit.Test;

import java.io.File;

public class ConfigTest
{
    @Config(name = "testVar", category = "test category")
    static boolean test = true;

    @Config(name = "testVar2", category = "test category", max = 30)
    static int test2 = 45;

    @Test
    public void initConfig()
    {
        //TODO initialize a config file without it breaking
        //Configuration config = new Configuration(new File("testConfig.cfg"));
        //ConfigProcessor.processAnnotations("testMod",config,ConfigTest.class);
    }
}
