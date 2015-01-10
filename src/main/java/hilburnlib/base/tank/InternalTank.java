package hilburnlib.base.tank;

import hilburnlib.base.interfaces.ISaveable;
import hilburnlib.reference.NBTTags;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * Simplified IFluidTank - improved NBT Handling and lower overhead than {@link net.minecraftforge.fluids.FluidTank}
 */
public class InternalTank implements IFluidTank, ISaveable<InternalTank>
{
    protected FluidStack fluid;
    protected int capacity;

    public InternalTank(int capacity)
    {
        this(null, capacity);
    }

    public InternalTank(FluidStack stack, int capacity)
    {
        this.fluid = stack;
        this.capacity = capacity;
    }

    public InternalTank(NBTTagCompound tagCompound)
    {
        if (!tagCompound.hasKey(NBTTags.FLUID_NULL))
        {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(tagCompound);

            if (fluid != null)
            {
                setFluid(fluid);
            }
        }
        setCapacity(tagCompound.getInteger(NBTTags.CAPACITY));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setInteger(NBTTags.CAPACITY, capacity);
        if (fluid == null)
        {
            tagCompound.setBoolean(NBTTags.FLUID_NULL, true);
        } else
        {
            fluid.writeToNBT(tagCompound);
        }
        return tagCompound;
    }

    /**
     * @return FluidStack representing the fluid in the tank, null if the tank is empty.
     */
    @Override
    public FluidStack getFluid()
    {
        return fluid;
    }

    /**
     * @return Current amount of fluid in the tank.
     */
    @Override
    public int getFluidAmount()
    {
        return fluid != null ? fluid.amount : 0;
    }

    public boolean isValidFluid(FluidStack fluidStack)
    {
        return fluid == null || fluid.isFluidEqual(fluidStack);
    }

    public boolean isValidFluid(Fluid fluid)
    {
        return this.fluid == null || this.fluid.getFluid() == fluid && this.fluid.tag == null;
    }

    /**
     * @return Capacity of this fluid tank.
     */
    @Override
    public int getCapacity()
    {
        return capacity;
    }

    @Override
    public FluidTankInfo getInfo()
    {
        return new FluidTankInfo(this);
    }

    /**
     * @param resource FluidStack attempting to fill the tank.
     * @param doFill   If false, the fill will only be simulated.
     * @return Amount of fluid that was accepted by the tank.
     */
    @Override
    public int fill(FluidStack resource, boolean doFill)
    {
        if (resource == null)
        {
            return 0;
        }

        if (!doFill)
        {
            if (fluid == null)
            {
                return Math.min(capacity, resource.amount);
            }

            if (!fluid.isFluidEqual(resource))
            {
                return 0;
            }

            return Math.min(capacity - fluid.amount, resource.amount);
        }

        if (fluid == null)
        {
            fluid = new FluidStack(resource, Math.min(capacity, resource.amount));
            return fluid.amount;
        }

        if (!fluid.isFluidEqual(resource))
        {
            return 0;
        }
        int filled = capacity - fluid.amount;

        if (resource.amount < filled)
        {
            fluid.amount += resource.amount;
            filled = resource.amount;
        } else
        {
            fluid.amount = capacity;
        }
        return filled;
    }

    /**
     * @param maxDrain Maximum amount of fluid to be removed from the container.
     * @param doDrain
     * @return Amount of fluid that was removed from the tank.
     */
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain)
    {
        if (fluid == null)
        {
            return null;
        }

        int drained = maxDrain;
        if (fluid.amount < drained)
        {
            drained = fluid.amount;
        }

        FluidStack stack = new FluidStack(fluid, drained);
        if (doDrain)
        {
            fluid.amount -= drained;
            if (fluid.amount <= 0)
            {
                fluid = null;
            }
        }
        return stack;
    }

    public void setFluid(FluidStack fluid)
    {
        this.fluid = fluid;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
}
