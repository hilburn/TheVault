package thevault.base.blocks;

import thevault.utils.LogHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AbstractTileBlock extends BlockContainer
{
    private final Class<? extends TileEntity> clazz;

    public AbstractTileBlock(String name, Class<? extends TileEntity> clazz, Material material)
    {
        super(material);
        this.setBlockName(name);
        this.clazz = clazz;
    }

    public AbstractTileBlock(String name, Class<? extends TileEntity> clazz)
    {
        this(name, clazz, Material.iron);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param world
     * @param metadata
     */
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        try
        {
            return clazz.newInstance();
        } catch (Exception e)
        {
            LogHelper.instance().crash(e, "Failed to initialise " + clazz);
            return null;
        }
    }
}
