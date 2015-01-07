package hilburnlib.base.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidContainerItem;

public class FluidContainerStackable extends Item implements IFluidContainerItem
{
    private static int BUCKET_VOLUME = 1000;
    private final int capacity;
    private final int stackCapacity;

    public FluidContainerStackable(int capacity)
    {
        this(capacity,64);
    }

    public FluidContainerStackable(int capacity, int maxStackSize)
    {
        this.capacity = capacity;
        this.stackCapacity = capacity * maxStackSize;
        this.setMaxStackSize(maxStackSize);
    }

    /**
     * Places the fluid's block into the {@code world}
     * @param world
     * @param x
     * @param y
     * @param z
     * @param container an ItemStack holding a FluidContainerStackable
     * @return true for successful placement
     */
    protected boolean placeIntoWorld(World world, int x, int y, int z, ItemStack container)
    {
        if (container==null)
        {
            return false;
        }
        FluidStack fluidStack = getFluid(container);
        Block block = fluidStack.getFluid().getBlock();
        if (block == null || fluidStack.amount<BUCKET_VOLUME || fluidStack.getFluid() == null || !fluidStack.getFluid().canBePlacedInWorld())
        {
            return false;
        }
        if (world.setBlock(x,y,z,block))
        {
            drain(container,BUCKET_VOLUME,true);
            return true;
        }
        return false;
    }

    /**
     * Picks up a fluid block from the {@code world}
     * @param world
     * @param x
     * @param y
     * @param z
     * @param container an ItemStack holding a FluidContainerStackable
     * @return true for successful pickup
     */
    protected boolean pickUpFromWorld(World world, int x, int y, int z, ItemStack container)
    {
        if (container!=null)
        {
            Block block = world.getBlock(x,y,z);
            if (block instanceof IFluidBlock)
            {
                IFluidBlock fluidBlock = (IFluidBlock) block;
                if (fluidBlock.canDrain(world, z, y, z))
                {
                    FluidStack fluidStack = fluidBlock.drain(world,x,y,z,false);
                    if (fill(container,fluidStack,false)==fluidStack.amount)
                    {
                        fill(container,fluidStack,true);
                        fluidBlock.drain(world,x,y,z,true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public FluidStack getFluid(ItemStack container)
    {
        NBTTagCompound tagCompound = container.stackTagCompound;
        if (tagCompound == null || !tagCompound.hasKey("FluidName"))
        {
            return null;
        }
        String fluidName = tagCompound.getString("FluidName");
        if (fluidName ==null || FluidRegistry.getFluid(fluidName)== null)
        {
            return null;
        }
        FluidStack result = new FluidStack(FluidRegistry.getFluidID(fluidName), container.stackSize * capacity);
        return result;
    }

    @Override
    public int getCapacity(ItemStack container)
    {
        return stackCapacity;
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill)
    {
        if (resource == null)
        {
            return 0;
        }

        int amountToFill = roundDownAmount(resource.amount);
        FluidStack fluidStack = getFluid(container);

        if (!doFill)
        {

            if (fluidStack == null)
            {
                return Math.min(stackCapacity, amountToFill);
            }

            if (!fluidStack.isFluidEqual(resource))
            {
                return 0;
            }

            return Math.min(stackCapacity - fluidStack.amount, amountToFill);
        }

        if (container.stackTagCompound == null)
        {
            container.stackTagCompound = new NBTTagCompound();
        }

        if (fluidStack == null)
        {
            amountToFill = Math.min(amountToFill,stackCapacity);
            setFluid(container, new FluidStack(resource.fluidID,amountToFill));
            return amountToFill;
        }

        if (!fluidStack.isFluidEqual(resource))
        {
            return 0;
        }

        int filled = stackCapacity - fluidStack.amount;
        if (amountToFill < filled)
        {
            fluidStack.amount += amountToFill;
            filled = amountToFill;
        }
        else
        {
            fluidStack.amount = stackCapacity;
        }
        setFluid(container,fluidStack);
        return filled;
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
    {
        FluidStack fluidStack = getFluid(container);
        if (fluidStack == null)
        {
            return null;
        }

        int currentAmount = fluidStack.amount;
        fluidStack.amount = Math.min(currentAmount, roundDownAmount(maxDrain));
        if (doDrain)
        {
            if (currentAmount == fluidStack.amount)
            {
                container.stackTagCompound.removeTag("FluidName");

                if (container.stackTagCompound.hasNoTags())
                {
                    container.stackTagCompound = null;
                }
                return fluidStack;
            }
            setFluid(container, new FluidStack(fluidStack.fluidID, currentAmount - fluidStack.amount));
        }
        return fluidStack;
    }

    private ItemStack setFluid(ItemStack container, FluidStack fluid)
    {
        if (container==null || fluid==null || fluid.amount<=0)
        {
            return null;
        }
        container.stackSize = fluid.amount/capacity;
        container.stackTagCompound = getNBT(fluid);
        return container;
    }

    private int roundDownAmount(int amount)
    {
        return amount - (amount % capacity);
    }

    private static NBTTagCompound getNBT(FluidStack fluid)
    {
        NBTTagCompound result = new NBTTagCompound();
        result.setString("FluidName", FluidRegistry.getFluidName(fluid.fluidID));
        if (fluid.tag != null)
        {
            result.setTag("Tag", fluid.tag);
        }
        return result;
    }
}
