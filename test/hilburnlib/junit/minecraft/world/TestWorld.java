package hilburnlib.junit.minecraft.world;

import cpw.mods.fml.common.registry.GameRegistry;
import hilburnlib.position.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWorld extends World
{
    public static final String NAME = "TestWorld";
    
    private List<TileEntity> tileEntities;
    private Map<BlockCoord, BlockData> worldMap;
    private Map<Integer, String> worldGen;
    private boolean superFlat;
    
    public TestWorld()
    {
        super(null, NAME, new TestWorldProvider(), new WorldSettings(new WorldInfo(new NBTTagCompound())), new Profiler());
        this.tileEntities = new ArrayList<>();
        this.worldMap = new HashMap<>();
        superFlat = false;
        BlockData.initBlocksAndItems();
    }

    @Override
    public void updateEntities()
    {
        for (TileEntity tileEntity : tileEntities)
        {
            if (tileEntity.isInvalid()) tileEntities.remove(tileEntity);
            else tileEntity.updateEntity();
        }
    }
    
    public BlockData getBlockData(int x, int y, int z)
    {
        BlockCoord blockCoord = new BlockCoord(x, y, z);
        BlockData blockData = worldMap.get(blockCoord);
        if (blockData == null)
        {
            blockData = new BlockData();
            if (superFlat && y == 0) blockData.block = Blocks.bedrock;
            if (superFlat && (y == 1 || y == 2)) blockData.block = Blocks.dirt;
            if (superFlat && y == 3) blockData.block = GameRegistry.findBlock("minecraft", "grass");
            worldMap.put(blockCoord, blockData);
        }
        return blockData;
    }

    @Override
    public Block getBlock(int x, int y, int z)
    {
        return getBlockData(x, y, z).block;
    }

    @Override
    public int getBlockMetadata(int x, int y, int z)
    {
        return getBlockData(x, y, z).metaData;
    }

    @Override
    public boolean setBlock(int x, int y, int z, Block block, int meta, int notify)
    {
        boolean flagAdd = false;
        if (block == null) block = Blocks.air;
        Block oldBlock = getBlock(x, y, z);
        if (oldBlock != block || getBlockMetadata(x, y, z) != meta)
        {
            BlockData blockData = getBlockData(x, y ,z);
            blockData.block = block;
            blockData.metaData = meta;
            if (block != oldBlock) flagAdd = true;
            
            TileEntity oldTileEntity = blockData.tileEntity;
            TileEntity tileEntity = block.createTileEntity(this, meta);
            if (tileEntity != oldTileEntity)
            {
                if (oldTileEntity != null) oldTileEntity.invalidate();
                blockData.tileEntity = tileEntity;
                if (tileEntity != null)
                {
                    tileEntity.setWorldObj(this);
                    tileEntity.xCoord = x;
                    tileEntity.yCoord = y;
                    tileEntity.zCoord = z;
                    tileEntity.validate();
                    if (tileEntity.canUpdate()) tileEntities.add(tileEntity);
                }
            }
            if (flagAdd) block.onBlockAdded(this, x, y, z);
            if (notify != 0) notifyBlockChange(x, y, z, block);
        }
        return true;
    }

    @Override
    public boolean setBlockMetadataWithNotify(int x, int y, int z, int meta, int notify)
    {
        BlockData blockData = getBlockData(x, y, z);
        blockData.metaData = meta;
        notifyBlockChange(x, y, z, blockData.block);
        return true;
    }

    @Override
    public TileEntity getTileEntity(int x, int y, int z)
    {
        return getBlockData(x, y, z).tileEntity;
    }

    @Override
    public void notifyBlocksOfNeighborChange(int x, int y, int z, final Block updatedBlock)
    {
        Block block = getBlock(x, y, z);
        if (block != Blocks.air)
            block.onNeighborBlockChange(this, x, y, z, updatedBlock);
    }

    @Override
    protected IChunkProvider createChunkProvider()
    {
        return null;
    }

    @Override
    protected int func_152379_p()
    {
        return 0;
    }

    @Override
    public Entity getEntityByID(int p_73045_1_)
    {
        return null;
    }
    
    public void clear()
    {
        worldMap = new HashMap<>();
        superFlat = false;
    }
    
    public void genSuperFlat()
    {
        clear();
        superFlat = true;
    }
}
