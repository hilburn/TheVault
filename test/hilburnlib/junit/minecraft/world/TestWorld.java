package hilburnlib.junit.minecraft.world;

import cpw.mods.fml.common.registry.GameRegistry;
import hilburnlib.position.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
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
    private boolean hasGen;
    
    public TestWorld()
    {
        super(null, NAME, new TestWorldProvider(), new WorldSettings(new WorldInfo(new NBTTagCompound())), new Profiler());
        tileEntities = new ArrayList<>();
        worldGen = new HashMap<>();
        worldMap = new HashMap<>();
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
            if (hasGen && worldGen.get(y) != null)
            {
                String[] sBlock = worldGen.get(y).split(":");
                blockData.block = GameRegistry.findBlock(sBlock[0], sBlock[1]);
            }
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
        tileEntities.clear();
        worldMap.clear();
        worldGen.clear();
        hasGen = false;
    }
    
    public void genSuperFlat()
    {
        genCustom(TerrainGen.superFlat);
    }

    /**
     * Sets a custom gen to the world {@link TerrainGen}
     * @param genString  the gen string eg. "minecraft:bedrock,2*minecraft:dirt,minecraft:grass"
     */
    public void genCustom(String genString)
    {
        clear();
        hasGen = true;
        String[] sBlocks = genString.split(",");
        int layer = 0;
        for (String sBlock : sBlocks)
        {
            String[] splittedBlock = sBlock.split("\\*");
            if (splittedBlock.length == 1) worldGen.put(layer++, splittedBlock[0]);
            else if (splittedBlock.length == 2)
            {
                for (int i = Integer.parseInt(splittedBlock[0]); i > 0 ;i--)
                    worldGen.put(layer++, splittedBlock[1]);
            } else 
            {
                throw new IllegalArgumentException("Use format [amount*]modId:block");
            }
        }
    }
    
    public static final class TerrainGen
    {
        public static final String superFlat = "minecraft:bedrock,2*minecraft:dirt,minecraft:grass";
        public static final String tunnelersDream = "minecraft:bedrock,230*minecraft:stone,5*minecraft:dirt,minecraft:grass";
        public static final String waterWorld = "minecraft:bedrock,5*minecraft:stone,5*minecraft:dirt,5*minecraft:sand,90*minecraft:water";
        public static final String overWorld = "minecraft:bedrock,59*minecraft:stone,3*minecraft:dirt,minecraft:grass";
        public static final String snowyKingdom = "inecraft:bedrock,59*minecraft:stone,3*minecraft:dirt,minecraft:grass,minecraft:snow_layer";
        public static final String bottomlessPit = "2*minecraft:cobblestone,3*minecraft:dirt,minecraft:grass";
        public static final String dessert = "minecraft:bedrock,3*minecraft:stone,52*minecraft:sandstone,8*minecraft:sand";
        public static final String redstoneReady = "minecraft:bedrock,3*minecraft:stone,52*minecraft:sandstone";
    }
}
