package hilburnlib.junit.test;

import hilburnlib.base.tank.InternalTank;
import hilburnlib.junit.minecraft.runner.MCTestRunner;
import hilburnlib.junit.minecraft.world.BlockData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(MCTestRunner.class)
public class InternalTankTest
{

    InternalTank tank;

    public InternalTankTest()
    {
        tank = new InternalTank(new FluidStack(FluidRegistry.WATER,2000),3000);
    }

    @Test
    public void testGetFluid()
    {
        assertTrue(tank.getFluid()!=null);
        assertTrue(tank.getFluid().isFluidStackIdentical(new FluidStack(FluidRegistry.WATER,2000)));
    }

    @Test
    public void testGetFluidAmount()
    {
        assertTrue(tank.getFluidAmount()>0);
    }

    @Test
    public void testNBTReadWrite()
    {
        NBTTagCompound tagCompound = tank.writeToNBT(new NBTTagCompound());
        InternalTank newTank = new InternalTank(0).readFromNBT(tagCompound);
        assertEquals(tank.getCapacity(),newTank.getCapacity());
        assertTrue((tank.getFluid()==null && newTank.getFluid()==null) || tank.getFluid().isFluidStackIdentical(newTank.getFluid()));
    }

    @Test
    public void testIsValidFluid()
    {
        assertTrue(tank.isValidFluid(FluidRegistry.WATER));
        assertFalse(tank.isValidFluid(new FluidStack(FluidRegistry.LAVA,1)));
    }

    @Test
    public void testGetInfo()
    {
        assertEquals(tank.getInfo().capacity,tank.getCapacity());
        assertTrue(tank.getInfo().fluid.isFluidStackIdentical(tank.getFluid()));
    }

    @Test
    public void testFill()
    {
        assertTrue(tank.fill(new FluidStack(FluidRegistry.LAVA,1),true)==0);
        assertTrue(tank.fill(new FluidStack(FluidRegistry.WATER,500),false)==500);
        assertTrue(tank.fill(new FluidStack(FluidRegistry.WATER,1500),true)==1000);
        assertTrue(tank.getCapacity()==tank.getFluidAmount());
    }

    @Test
    public void testDrain()
    {
        tank.setFluid(new FluidStack(FluidRegistry.WATER,750));
        assertTrue(tank.drain(500, true).amount == 500);
        assertTrue(tank.drain(500, true).amount == 250);
        assertTrue(tank.fill(new FluidStack(FluidRegistry.LAVA,100),true)>0);
    }

    @Test
    public void testSetFluid()
    {
        tank.setFluid(new FluidStack(FluidRegistry.WATER,50));
        assertTrue(tank.getFluidAmount()==50);
    }

    @Test
    public void testSetCapacity()
    {
        tank.setCapacity(5000);
        assertTrue(tank.getCapacity()==5000);
    }
}