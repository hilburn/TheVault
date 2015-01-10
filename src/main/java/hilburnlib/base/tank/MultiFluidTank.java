package hilburnlib.base.tank;

import hilburnlib.base.interfaces.ISaveable;
import hilburnlib.reference.NBTTags;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

public class MultiFluidTank implements IFluidHandler, ISaveable<MultiFluidTank>
{
    static final String EXTRACT_ANY = "extract_any";

    protected List<InternalTank> tanks = new ArrayList<>();
    protected int maxTanks;
    protected int maxCapacity;
    protected int filled;
    protected boolean extractAny;

    public MultiFluidTank()
    {
        this(1000, Integer.MAX_VALUE, true);
    }

    public MultiFluidTank(int maxCapacity, boolean extractAny)
    {
        this(maxCapacity, Integer.MAX_VALUE, extractAny);
    }

    public MultiFluidTank(int maxCapacity, int maxTanks, boolean extractAny)
    {
        this.maxTanks = maxTanks;
        this.maxCapacity = maxCapacity;
        this.extractAny = extractAny;
    }

    public MultiFluidTank(NBTTagCompound tagCompound)
    {
        maxCapacity = tagCompound.getInteger(NBTTags.CAPACITY);
        maxTanks = tagCompound.getInteger(NBTTags.SLOT);
        extractAny = tagCompound.getBoolean(EXTRACT_ANY);
        if (!tagCompound.hasKey(NBTTags.FLUID_NULL))
        {
            NBTTagList tagList = tagCompound.getTagList(NBTTags.FLUID, NBTTags.TAG_COMPOUND);
            for (int i = 0; i < tagList.tagCount(); i++)
            {
                tanks.add(new InternalTank(tagList.getCompoundTagAt(i)));
            }
            getVolumeFilled();
        }
    }

    /**
     * Fills fluid into internal tanks, distribution is left entirely to the IFluidHandler.
     *
     * @param from     Orientation the Fluid is pumped in from.
     * @param resource FluidStack representing the Fluid and maximum amount of fluid to be filled.
     * @param doFill   If false, fill will only be simulated.
     * @return Amount of resource that was (or would have been, if simulated) filled.
     */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        int maxFill = maxCapacity - filled;
        if (resource == null || maxFill == 0) return 0;
        FluidStack maxAdd = resource.copy();
        maxAdd.amount = Math.min(maxFill, resource.amount);
        for (InternalTank tank : tanks)
        {
            if (tank.isValidFluid(resource))
            {
                int filled = tank.fill(maxAdd, doFill);
                if (doFill) this.filled += filled;
                return filled;
            }
        }
        if (doFill) tanks.add(new InternalTank(maxAdd, this.maxCapacity));
        return maxAdd.amount;
    }

    /**
     * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
     *
     * @param from     Orientation the Fluid is drained to.
     * @param resource FluidStack representing the Fluid and maximum amount of fluid to be drained.
     * @param doDrain  If false, drain will only be simulated.
     * @return FluidStack representing the Fluid and amount that was (or would have been, if
     * simulated) drained.
     */
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (tanks.size() > 0)
        {
            for (int i = 0; i < (extractAny ? tanks.size() : 1); i++)
            {
                if (tanks.get(i).isValidFluid(resource))
                {
                    FluidStack stack = tanks.get(i).drain(resource.amount, doDrain);
                    if (doDrain)
                    {
                        this.filled -= stack.amount;
                        if (tanks.get(i).getFluid() == null) tanks.remove(i);
                    }
                    return stack;
                }
            }
        }
        return null;
    }

    /**
     * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
     * <p/>
     * This method is not Fluid-sensitive.
     *
     * @param from     Orientation the fluid is drained to.
     * @param maxDrain Maximum amount of fluid to drain.
     * @param doDrain  If false, drain will only be simulated.
     * @return FluidStack representing the Fluid and amount that was (or would have been, if
     * simulated) drained.
     */
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        if (tanks.size() > 0)
        {
            FluidStack stack = tanks.get(0).drain(maxDrain, doDrain);
            if (doDrain)
            {
                this.filled -= stack.amount;
                if (tanks.get(0).getFluid() == null) tanks.remove(0);
            }
            return stack;
        }
        return null;
    }

    /**
     * Returns true if the given fluid can be inserted into the given direction.
     * <p/>
     * More formally, this should return true if fluid is able to enter from the given direction.
     *
     * @param from
     * @param fluid
     */
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return filled < maxCapacity;
    }

    /**
     * Returns true if the given fluid can be extracted from the given direction.
     * <p/>
     * More formally, this should return true if fluid is able to leave from the given direction.
     *
     * @param from
     * @param fluid
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        for (InternalTank tank : tanks)
            if (tank.isValidFluid(fluid)) return true;
        return false;
    }

    /**
     * Returns an array of objects which represent the internal tanks. These objects cannot be used
     * to manipulate the internal tanks. See {@link net.minecraftforge.fluids.FluidTankInfo}.
     *
     * @param from Orientation determining which tanks should be queried.
     * @return Info for the relevant internal tanks.
     */
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        FluidTankInfo[] result = new FluidTankInfo[tanks.size()];
        for (int i = 0; i < result.length; result[i] = tanks.get(i++).getInfo()) ;
        return result;
    }

    public int getVolumeFilled()
    {
        int result = 0;
        for (int i = 0; i < tanks.size(); i++, result += tanks.get(i).getCapacity()) ;
        return result;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setInteger(NBTTags.CAPACITY, maxCapacity);
        tagCompound.setInteger(NBTTags.SLOT, maxTanks);
        tagCompound.setBoolean(EXTRACT_ANY, extractAny);
        if (tanks.size() == 0)
        {
            tagCompound.setBoolean(NBTTags.FLUID_NULL, true);
        } else
        {
            NBTTagList tagList = new NBTTagList();
            for (InternalTank tank : tanks)
            {
                tagList.appendTag(tank.writeToNBT(new NBTTagCompound()));
            }
            tagCompound.setTag(NBTTags.FLUID, tagList);
        }
        return tagCompound;
    }
}
