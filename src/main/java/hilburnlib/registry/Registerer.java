package hilburnlib.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import hilburnlib.utils.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Registerer
{
    public static void scan(Class<?> targetClass)
    {
        Side side = FMLCommonHandler.instance().getSide();
        for (Field field : targetClass.getFields())      
        {
            Register registerAnnotation = field.getAnnotation(Register.class);
            Class clazz = field.getType();
            if (registerAnnotation == null) continue;
            if (Modifier.isStatic(field.getModifiers()))
            {
                if(Item.class.isAssignableFrom(clazz))
                {
                    registerItem(side, field, registerAnnotation, clazz);
                } else if (Block.class.isAssignableFrom(clazz))
                {
                    registerBlock(side, field, registerAnnotation, clazz);
                }
                else
                {
                    LogHelper.warn("Can only register Blocks and Items - " + field.getName() + " unrecognised");
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
    
    private static void registerItem(Side side, Field field, Register registerAnnotation, Class<? extends Item> clazz)
    {
        try
        {
            Item item = getConstructed(clazz,registerAnnotation.name());
            field.set(null, item);
            GameRegistry.registerItem(item, registerAnnotation.name());
            if (side == Side.CLIENT)
            {
                if (registerAnnotation.IItemRenderer() != IItemRenderer.class)
                    MinecraftForgeClient.registerItemRenderer(item, registerAnnotation.IItemRenderer().newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e)
        {
            LogHelper.warn("Failed to register item " + registerAnnotation.name());
        }
    }
    
    private static void registerBlock(Side side, Field field, Register registerAnnotation, Class<? extends Block> clazz)
    {
        try
        {
            Block block = getConstructed(clazz,registerAnnotation.name());
            field.set(null, block);
            GameRegistry.registerBlock(block, registerAnnotation.itemBlock(), registerAnnotation.name());
            if (registerAnnotation.tileEntity() != TileEntity.class)
                GameRegistry.registerTileEntity(registerAnnotation.tileEntity(), registerAnnotation.name());
            if (side == Side.CLIENT)
            {
                if (registerAnnotation.SBRH() != ISimpleBlockRenderingHandler.class)
                    RenderingRegistry.registerBlockHandler(block.getRenderType(), registerAnnotation.SBRH().newInstance());
                if (registerAnnotation.tileEntity() != TileEntity.class && registerAnnotation.TESR() != TileEntitySpecialRenderer.class)
                    ClientRegistry.bindTileEntitySpecialRenderer(registerAnnotation.tileEntity(), registerAnnotation.TESR().newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e)
        {
            LogHelper.warn("Failed to register block " + registerAnnotation.name());
        }
    }

    private static <T> T getConstructed(Class clazz, String name) throws IllegalAccessException, InstantiationException
    {
        try
        {
            return (T)clazz.getConstructor(String.class).newInstance(name);
        } catch (Exception e)
        {
            return (T) clazz.newInstance();
        }
    }
}
