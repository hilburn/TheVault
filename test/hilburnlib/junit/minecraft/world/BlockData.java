package hilburnlib.junit.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class BlockData
{
    public Block block = Blocks.air;
    public TileEntity tileEntity = null;
    public int metaData = 0;
    
    
    /**
     * Init Blocks and Items only do it once when a second world is created the registry will already be filled 
     */
    public static boolean init = false;
    public static void initBlocksAndItems()
    {
        if (!init)
        {
            Block.registerBlocks();
            Item.registerItems();
            init = true;
        }        
    }
}
