package hilburnlib.base.items;

import hilburnlib.entity.PlayerHelper;
import hilburnlib.java.math.MathHelper;
import hilburnlib.position.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidContainerItem;

public class FluidContainerStackable extends Item implements IFluidContainerItem
{
    protected static int BUCKET_VOLUME = 1000;
    private final int capacity;
    private final int stackCapacity;

    public FluidContainerStackable(int capacity)
    {
        this(capacity, 64);
    }

    public FluidContainerStackable(int capacity, int maxStackSize)
    {
        this.capacity = capacity;
        this.stackCapacity = capacity * maxStackSize;
        this.setMaxStackSize(maxStackSize);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack container, World world, EntityPlayer player)
    {
        MovingObjectPosition movingObjectPosition = this.getMovingObjectPositionFromPlayer(world, player, false);
        if (movingObjectPosition == null)
        {
            return container;
        }
        if (movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            BlockCoord bc = new BlockCoord(movingObjectPosition.blockX, movingObjectPosition.blockY, movingObjectPosition.blockZ).offset(movingObjectPosition.sideHit);
            if (!world.isRemote)
            {
                boolean creative = PlayerHelper.isCreative(player);
                if (pickUpFromWorld(world, bc, container, creative) || placeIntoWorld(world, bc, container, creative)) ;
            }
        }
        return container;
    }

    /**
     * Places the fluid's block into the {@code world}
     *
     * @param world     minecraft world object
     * @param bc        coordinates of the location
     * @param container an ItemStack holding a FluidContainerStackable
     * @return true for successful placement
     */
    protected boolean placeIntoWorld(World world, BlockCoord bc, ItemStack container, boolean isCreative)
    {
        if (container == null)
        {
            return false;
        }
        FluidStack fluidStack = getFluid(container);
        if (fluidStack == null)
        {
            return false;
        }
        Block block = fluidStack.getFluid().getBlock();
        if (block == null || (block.equals(bc.getBlock(world)) && bc.canDrain(world)) || (fluidStack.amount < BUCKET_VOLUME && !isCreative) || fluidStack.getFluid() == null || !fluidStack.getFluid().canBePlacedInWorld())
        {
            return false;
        }
        if (bc.setBlock(world, block))
        {
            if (!isCreative) drain(container, BUCKET_VOLUME, true);
            return true;
        }
        return false;
    }

    /**
     * Picks up a fluid block from the {@code world}
     *
     * @param world     the minecraft world
     * @param bc        coordinates of the location
     * @param container an ItemStack holding a FluidContainerStackable
     * @return true for successful pickup
     */
    protected boolean pickUpFromWorld(World world, BlockCoord bc, ItemStack container, boolean isCreative)
    {
        if (container != null)
        {
            Block block = bc.getBlock(world);
            if (block instanceof IFluidBlock)
            {
                IFluidBlock fluidBlock = (IFluidBlock)block;
                if (bc.canDrain(world, fluidBlock))
                {
                    FluidStack fluidStack = bc.drain(world, false, fluidBlock);
                    if (fill(container, fluidStack, false) == fluidStack.amount)
                    {
                        fill(container, fluidStack, true);
                        bc.drain(world, true, fluidBlock);
                        return true;
                    } else if (isCreative)
                    {
                        fill(container, fluidStack, true);
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
        if (fluidName == null || FluidRegistry.getFluid(fluidName) == null)
        {
            return null;
        }
        return new FluidStack(FluidRegistry.getFluidID(fluidName), container.stackSize * capacity);
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
            amountToFill = Math.min(amountToFill, stackCapacity);
            setFluid(container, new FluidStack(resource.fluidID, amountToFill));
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
        } else
        {
            fluidStack.amount = stackCapacity;
        }
        setFluid(container, fluidStack);
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
            fluidStack = new FluidStack(fluidStack.fluidID, currentAmount - fluidStack.amount);
            setFluid(container, fluidStack);
        }
        return fluidStack;
    }

    private ItemStack setFluid(ItemStack container, FluidStack fluid)
    {
        if (container == null || fluid == null)
        {
            return null;
        }
        container.stackSize = MathHelper.clamp(fluid.amount / capacity, 1, container.getMaxStackSize());
        container.stackTagCompound = getNBT(fluid);
        return container;
    }

    private int roundDownAmount(int amount)
    {
        return amount - (amount % capacity);
    }

    private static NBTTagCompound getNBT(FluidStack fluid)
    {
        if (fluid.amount <= 0) return null;
        NBTTagCompound result = new NBTTagCompound();
        result.setString("FluidName", FluidRegistry.getFluidName(fluid.fluidID));
        if (fluid.tag != null)
        {
            result.setTag("Tag", fluid.tag);
        }
        return result;
    }
}
