package hilburnlib.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import hilburnlib.utils.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Registerer
{
    public static void scan(Class<?> targetClass)
    {
        Side side = FMLCommonHandler.instance().getSide();
        for (Field field : targetClass.getFields())      
        {
            Register registerAnnotation = field.getAnnotation(Register.class);
            if (registerAnnotation == null) continue;
            if (Modifier.isStatic(field.getModifiers()))
            {
                if(Item.class.isAssignableFrom(field.getDeclaringClass()))
                {
                    registerItem(side, field, registerAnnotation);
                } else if (Block.class.isAssignableFrom(field.getDeclaringClass()))
                {
                    registerBlock(side, field, registerAnnotation);
                }
            } else
            {
                LogHelper.warn("Can't register non-static field " + field.getName());
            }
        }
    }
    
    public static void scanWithSubClasses(Class<?> clazz)
    {
        scan(clazz);
        for (Class<?> sub : clazz.getClasses())
            scanWithSubClasses(sub);
    }
    
    private static void registerItem(Side side, Field field, Register registerAnnotation)
    {
        try
        {
            field.set(null, field.getDeclaringClass().newInstance());
            Item item = (Item)field.get(null);
            GameRegistry.registerItem(item, registerAnnotation.name());
            if (side == Side.CLIENT)
            {
                if (registerAnnotation.IItemRenderer() != null)
                    MinecraftForgeClient.registerItemRenderer(item, registerAnnotation.IItemRenderer().newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e)
        {
            LogHelper.warn("Failed to register item " + registerAnnotation.name());
        }
    }
    
    private static void registerBlock(Side side, Field field, Register registerAnnotation)
    {
        try
        {
            field.set(null, field.getDeclaringClass().newInstance());
            Block block = (Block) field.get(null);
            GameRegistry.registerBlock(block, registerAnnotation.itemBlock(), registerAnnotation.name());
            if (registerAnnotation.tileEntity() != null)
                GameRegistry.registerTileEntity(registerAnnotation.tileEntity(), registerAnnotation.name());
            if (side == Side.CLIENT)
            {
                if (registerAnnotation.SBRH() != null)
                    RenderingRegistry.registerBlockHandler(block.getRenderType(), registerAnnotation.SBRH().newInstance());
                if (registerAnnotation.tileEntity() != null && registerAnnotation.TESR() != null)
                    ClientRegistry.bindTileEntitySpecialRenderer(registerAnnotation.tileEntity(), registerAnnotation.TESR().newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e)
        {
            LogHelper.warn("Failed to register block " + registerAnnotation.name());
        }
    }
}
