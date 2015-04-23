package hilburnlib.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import hilburnlib.utils.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
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
            Class clazz = field.getType();
            if (registerAnnotation == null) continue;
            if (!registerAnnotation.dependency().isEmpty() && !Loader.isModLoaded(registerAnnotation.dependency())) continue;
            if (Modifier.isStatic(field.getModifiers()))
            {
                if (Item.class.isAssignableFrom(clazz))
                {
                    registerItem(side, field, registerAnnotation, clazz);
                } else if (Block.class.isAssignableFrom(clazz))
                {
                    registerBlock(side, field, registerAnnotation, clazz);
                } else
                {
                    LogHelper.warn("Can only register Blocks and Items - " + field.getName() + " unrecognised");
                }
            } else
            {
                LogHelper.warn("Can't register non-static field " + field.getName());
            }
        }
    }

    private static void registerItem(Side side, Field field, Register registerAnnotation, Class<? extends Item> clazz)
    {
        try
        {
            Item item;
            if ((item = (Item)field.get(null)) == null)
            {
                item = getConstructed(clazz);
                field.set(null, item);
            }
            if (!registerAnnotation.unlocalizedName().isEmpty()) item.setUnlocalizedName(registerAnnotation.unlocalizedName());
            GameRegistry.registerItem(item, getName(registerAnnotation).isEmpty() ? item.getUnlocalizedName() : getName(registerAnnotation));
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

    private static String getName(Register registerAnnotation)
    {
        return registerAnnotation.name().isEmpty() ? registerAnnotation.unlocalizedName() : registerAnnotation.name();
    }

    private static void registerBlock(Side side, Field field, Register registerAnnotation, Class<? extends Block> clazz)
    {
        try
        {
            Block block;
            if ((block = (Block)field.get(null)) == null)
            {
                block = getConstructed(clazz);
                field.set(null, block);
            }
            if (!registerAnnotation.unlocalizedName().isEmpty()) block.setBlockName(registerAnnotation.unlocalizedName());
            GameRegistry.registerBlock(block, registerAnnotation.itemBlock(), getName(registerAnnotation).isEmpty() ? block.getUnlocalizedName() : getName(registerAnnotation));
            if (registerAnnotation.tileEntity() != TileEntity.class)
                GameRegistry.registerTileEntity(registerAnnotation.tileEntity(), registerAnnotation.name());
            if (side == Side.CLIENT)
            {
                if (registerAnnotation.SBRH() != ISimpleBlockRenderingHandler.class)
                    RenderingRegistry.registerBlockHandler(block.getRenderType(), registerAnnotation.SBRH().newInstance());
                else if (registerAnnotation.tileEntity() != TileEntity.class && registerAnnotation.TESR() != TileEntitySpecialRenderer.class)
                    ClientRegistry.bindTileEntitySpecialRenderer(registerAnnotation.tileEntity(), registerAnnotation.TESR().newInstance());
                if (registerAnnotation.IItemRenderer() != IItemRenderer.class)
                    MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), registerAnnotation.IItemRenderer().newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e)
        {
            LogHelper.warn("Failed to register block " + registerAnnotation.name());
        }
    }

    private static <T> T getConstructed(Class clazz)
    {
        try
        {
            return (T)clazz.newInstance();

        } catch (Exception e)
        {
            return null;
        }
    }
}
