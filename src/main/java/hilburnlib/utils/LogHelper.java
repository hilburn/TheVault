package hilburnlib.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import org.apache.logging.log4j.Level;

public class LogHelper
{
    /**
     * General logging method
     *
     * @param level Level of the log
     * @param obj   object to log
     */
    public static void log(Level level, Object obj)
    {
        FMLLog.log(Loader.instance().activeModContainer().getName(), level, String.valueOf(obj));
    }

    /**
     * Used for logging on debug level
     *
     * @param obj object to log
     */
    public static void debug(Object obj)
    {
        log(Level.DEBUG, obj);
    }

    /**
     * Used for logging on info level
     *
     * @param obj object to log
     */
    public static void info(Object obj)
    {
        log(Level.INFO, obj);
    }

    public static void warn(Object obj)
    {
        log(Level.WARN, obj);
    }

    public static void crash(Exception e, String message)
    {
        FMLCommonHandler.instance().raiseException(e, message, true);
    }

    public static void error(Exception e, String message)
    {
        FMLCommonHandler.instance().raiseException(e, message, false);
    }
}
