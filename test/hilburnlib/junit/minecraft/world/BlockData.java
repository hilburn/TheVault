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
}
