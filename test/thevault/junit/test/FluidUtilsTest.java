package thevault.junit.test;

import thevault.fluids.FluidUtils;

import thevault.junit.minecraft.item.TestItem;
import thevault.junit.minecraft.runner.MCTestRunner;
import thevault.registry.Register;
import thevault.registry.Registerer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;

import static thevault.junit.minecraft.Assert.*;

@RunWith(MCTestRunner.class)
public class FluidUtilsTest
{
    @Register(name = "testItemFluid")
    public static TestItem testItem;
    
    public FluidUtilsTest()
    {
        Registerer.scan(FluidUtilsTest.class);
    }
    
    @Test
    public void testNBTWriteRead()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        FluidUtils.writeFluidToNBT(nbtTagCompound, FluidRegistry.WATER);
        assertEquals(FluidRegistry.WATER, FluidUtils.getFluidFromNBT(nbtTagCompound));
    }
    
    @Test
    public void testContainerItem()
    {
        ItemStack itemStack = new ItemStack(testItem);
        assertEquals(new ItemStack(Items.flower_pot).getItem(), FluidUtils.getEmptyContainer(itemStack).getItem());
        itemStack = new ItemStack(Items.potionitem);
        assertEquals(new ItemStack(Items.glass_bottle).getItem(), FluidUtils.getEmptyContainer(itemStack).getItem());
    }
}
