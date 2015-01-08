package hilburnlib.position;

import hilburnlib.java.util.ICopy;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class BlockCoord implements ICopy<BlockCoord>
{
    private int x, y ,z;

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public BlockCoord offset(int side)
    {
        return this.offset(ForgeDirection.getOrientation(side));
    }

    public BlockCoord offset(ForgeDirection direction)
    {
        x+=direction.offsetX;
        y+=direction.offsetY;
        z+=direction.offsetZ;
        return this;
    }

    public BlockCoord offset(int side, int distance)
    {
        return this.offset(ForgeDirection.getOrientation(side),distance);
    }

    public BlockCoord offset(ForgeDirection direction, int distance)
    {
        x+=direction.offsetX*distance;
        y+=direction.offsetY*distance;
        z+=direction.offsetZ*distance;
        return this;
    }

    public BlockCoord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof BlockCoord)
        {
            BlockCoord b = (BlockCoord)obj;
            return b.x == this.x && b.y == this.y && b.z == this.z;
        }
        return false;
    }

    @Override
    public BlockCoord copy()
    {
        return new BlockCoord(x,y,z);
    }

    @Override
    public int hashCode()
    {
        int result = (x ^ (x >>> 16));
        result = 15 * result + (y ^ (y >>> 16));
        result = 15 * result + (z ^ (z >>> 16));
        return result;
    }

    //######Block Stuff######

    public boolean setBlock(World world, Block block)
    {
        return setBlock(world, block, 0, 3);
    }

    public boolean setBlock(World world, Block block, int metadata, int updateType)
    {
        return world.setBlock(x,y,z,block, metadata, updateType);
    }

    public Block getBlock(World world)
    {
        return world.getBlock(x,y,z);
    }

    public int getMetadata(World world)
    {
        return world.getBlockMetadata(x,y,z);
    }

    public TileEntity getTileEntity(World world)
    {
        return world.getTileEntity(x,y,z);
    }

    public boolean isUnbreakable(World world)
    {
        return getBlock(world).getBlockHardness(world,x,y,z)==-1;
    }

    public boolean isPowered(World world)
    {
        return world.isBlockIndirectlyGettingPowered(x,y,z);
    }

    //######Fluid stuff#######

    public IFluidBlock getFluidBlock(World world)
    {
        Block block = getBlock(world);
        if (block instanceof IFluidBlock) return (IFluidBlock) block;
        return null;
    }

    public boolean canDrain(World world)
    {
        IFluidBlock block = getFluidBlock(world);
        return block != null && canDrain(world, block);
    }

    public boolean canDrain(World world, IFluidBlock block)
    {
        return block.canDrain(world,x,y,z);
    }

    public FluidStack drain(World world, boolean drain)
    {
        IFluidBlock block = getFluidBlock(world);
        if (block != null) return drain(world, drain,block);
        return null;
    }

    public FluidStack drain(World world, boolean drain, IFluidBlock block)
    {
        return block.drain(world,x,y,z,drain);
    }
}
