package thevault.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Storage object for {@link Block} and it's metadata int value
 */
public class MetaDataBlock
{
    private Block block;
    private int metaData;

    public MetaDataBlock(Block block, int metaData)
    {
        this.block = block;
        this.metaData = metaData;
    }

    public Block getBlock()
    {
        return block;
    }

    public int getMetaData()
    {
        return metaData;
    }

    public MetaDataBlock(Block block)
    {
        this(block, 0);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof MetaDataBlock)
            return this.block == ((MetaDataBlock) obj).block && this.metaData == ((MetaDataBlock) obj).metaData;
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return block.hashCode() ^ metaData;
    }

    @Override
    public String toString()
    {
        return GameRegistry.findUniqueIdentifierFor(block).toString() + ":" + metaData;
    }
}
