package hilburnlib.junit.test;

import hilburnlib.junit.MCTestRunner;
import hilburnlib.junit.minecraft.world.TestWorld;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MCTestRunner.class)
public class WorldTest
{
    @Test
    public void testTilePlacement()
    {
        TestWorld world = new TestWorld();
        world.setBlock(0, 0, 0, Blocks.chest);
        Assert.assertEquals(Blocks.chest, world.getBlock(0, 0, 0));
        Assert.assertEquals(TileEntityChest.class, world.getTileEntity(0, 0, 0).getClass());
    }

    @Test
    public void testBlockPlacement()
    {
        TestWorld world = new TestWorld();
        world.setBlock(0, 0, 0, Blocks.sand);
        Assert.assertEquals(Blocks.sand, world.getBlock(0, 0, 0));
    }

    @Test
    public void testGenAir()
    {
        TestWorld world = new TestWorld();
        Assert.assertEquals(Blocks.air, world.getBlock(0, 0, 0));
    }

    @Test
    public void testGenSuperFlat()
    {
        TestWorld world = new TestWorld();
        world.genSuperFlat();
        Assert.assertEquals(Blocks.bedrock, world.getBlock(0, 0, 0));
        Assert.assertEquals(Blocks.dirt, world.getBlock(0, 1, 0));
        Assert.assertEquals(Blocks.grass, world.getBlock(0, 3, 0));
    }
}
