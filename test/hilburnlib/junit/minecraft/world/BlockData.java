package hilburnlib.junit.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockData
{
    public Block block = Blocks.air;
    public TileEntity tileEntity = null;
    public int metaData = 0;
    
    
    private static boolean init = false;
    private static int FREE_ID = 200;
    
    /**
     * ONLY FOR USAGE IN JUNIT TESTS
     * Init Blocks and Items 
     * only do it once when a second world is created the registry will already be filled
     */
    public static void initBlocksAndItems()
    {
        if (!init)
        {
            Block.registerBlocks();
            Item.registerItems();
            init = true;
        }        
    }

    /**
     * ONLY FOR USAGE IN JUNIT TESTS
     * @param block block to register
     * @param name block name
     * @return the registered block
     */
    public static Block registerBlock(Block block, String name)
    {
        return registerBlock(block, ItemBlock.class, name);
    }

    /**
     * ONLY FOR USAGE IN JUNIT TESTS
     * @param block block to register
     * @param itemClass the ItemBlockClass
     * @param name block name
     * @return the registered block
     */
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemClass, String name)
    {
        initBlocksAndItems();
        if(!Block.blockRegistry.containsKey(name))
        {
            int id = FREE_ID++;
            Block.blockRegistry.addObject(id, name, block);
            try
            {
                Constructor con = itemClass.getConstructor(Block.class);
                ItemBlock itemBlock = (ItemBlock) con.newInstance(block);
                Item.itemRegistry.addObject(id, name, itemBlock);
            }
            catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new IllegalArgumentException("Block " + name + " is already defined");
        }
        return Block.getBlockFromName(name);
    }
}
