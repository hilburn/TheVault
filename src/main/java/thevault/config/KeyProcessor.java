package thevault.config;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.settings.KeyBinding;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class KeyProcessor
{
    public static void processAnnotations(Class<?> clazz, String modId)
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            for (Field f : clazz.getFields())
            {
                if (Modifier.isStatic(f.getModifiers()))
                {
                    RegisterKey annotation = f.getAnnotation(RegisterKey.class);
                    if (annotation == null) continue;
                    String unlocalizedCategory = annotation.unlocalizedCategory();
                    if (unlocalizedCategory.isEmpty()) unlocalizedCategory = "key.categories." + modId;
                    KeyBinding keyBinding = new KeyBinding(annotation.unlocalizedName(), annotation.keyCode(), unlocalizedCategory);
                    try
                    {
                        f.set(null, keyBinding);
                        ClientRegistry.registerKeyBinding(keyBinding);
                    } catch (IllegalAccessException e)
                    {
                       // Should never happen
                    }
                }
            }
        }
    }
}
