package hilburnlib.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import hilburnlib.utils.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Registerer
{
    public static void scan(Class<?> targetClass)
    {
        for (Field field : targetClass.getFields())      
        {
            Register registerAnnotation = field.getAnnotation(Register.class);
            if (registerAnnotation == null) continue;
            if (Modifier.isStatic(field.getModifiers()))
            {
                if(Item.class.isAssignableFrom(field.getDeclaringClass()))
                {
                    try
                    {
                        field.set(null, field.getDeclaringClass().newInstance());
                        GameRegistry.registerItem((Item)field.get(null), registerAnnotation.name());
                    } catch (IllegalAccessException | InstantiationException e)
                    {
                        LogHelper.warn("Failed to register item " + registerAnnotation.name());
                    }
                } else if (Block.class.isAssignableFrom(field.getDeclaringClass()))
                {
                    try
                    {
                        field.set(null, field.getDeclaringClass().newInstance());
                        GameRegistry.registerBlock((Block) field.get(null), registerAnnotation.name());
                        if (registerAnnotation.tileentity() != null)
                            GameRegistry.registerTileEntity(registerAnnotation.tileentity(), registerAnnotation.name());
                    } catch (IllegalAccessException | InstantiationException e)
                    {
                        LogHelper.warn("Failed to register block " + registerAnnotation.name());
                    }
                }
            }
        }
    }
}
