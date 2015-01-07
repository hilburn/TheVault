package hilburnlib.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import hilburnlib.config.ConfigProcessor;
import net.minecraft.client.gui.GuiScreen;

/**
 * Example subClass
 * <code>
 * public final class MyModGuiConfig extends ModBaseGuiConfig
 * {
 *      public MyModGuiConfig(GuiScreen guiScreen)
 *      {
 *          super(guiScreen, myModConfiguration)
 *      }
 * }
 * </code> 
 * Make sure you have one constructor with the parameter GuiScreen
 */
public abstract class ModBaseGuiConfig extends GuiConfig
{
    public ModBaseGuiConfig(GuiScreen guiScreen, ConfigProcessor.ModConfiguration modConfiguration)
    {
        super(guiScreen,
                modConfiguration.getConfigElements(),
                modConfiguration.modId,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(modConfiguration.getConfigPath()));
    }
}
