package hilburnlib.fluids;

import hilburnlib.reference.NBTTags;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidUtils
{
    public static ItemStack getEmptyContainer(ItemStack stack)
    {
        if (stack.getItem().hasContainerItem(stack))
        {
            return stack.getItem().getContainerItem(stack);
        } else if (stack.getItem() instanceof ItemPotion && stack.stackTagCompound == null)
        {
            return new ItemStack(Items.glass_bottle);
        } else
        {
            return null;
        }
    }

    public static Fluid getFluidFromNBT(NBTTagCompound NBT)
    {
        String name = NBT.getString(NBTTags.FLUID);
        if (name == null || name.isEmpty() || name.equals(NBTTags.FLUID_NULL))
            return null;
        return FluidRegistry.getFluid(name);
    }

    public static void writeFluidToNBT(NBTTagCompound NBT, Fluid fluid)
    {
        String name = fluid != null ? fluid.getName() : NBTTags.FLUID_NULL;
        NBT.setString(NBTTags.FLUID, name);
    }
}
