package hilburnlib.client.gui;

import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.Set;

/**
 * Extend this class and implement the getModGuiConfig method
 * to return the class of your implementation of ModBaseGuiConfig
 * Example subClass
 * <code>
 * public final class MyGuiFactory extends GuiBaseFactory
 * {
 *    public Class&lt;? extends ModBaseGuiConfig> getModGuiConfig()
 *    {
 *        return MyModGuiConfig.class
 *    }
 * }
 * </code> 
 */
public abstract class GuiBaseFactory implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft minecraftInstance)
    {

    }

    public abstract Class<? extends ModBaseGuiConfig> getModGuiConfig();

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return getModGuiConfig();
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }
}
