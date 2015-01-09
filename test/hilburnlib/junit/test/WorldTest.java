package hilburnlib.junit.test;

import hilburnlib.junit.minecraft.runner.MCTestRunner;
import hilburnlib.junit.minecraft.world.TestWorld;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(MCTestRunner.class)
public class WorldTest
{
    private TestWorld world;
    
    public WorldTest()
    {
        world = new TestWorld();
    }
    
    @Test
    public void testTilePlacement()
    {
        world.setBlock(0, 0, 0, Blocks.chest);
        assertEquals(Blocks.chest, world.getBlock(0, 0, 0));
        assertEquals(TileEntityChest.class, world.getTileEntity(0, 0, 0).getClass());
    }

    @Test
    public void testBlockPlacement()
    {
        world.setBlock(0, 0, 0, Blocks.sand);
        assertEquals(Blocks.sand, world.getBlock(0, 0, 0));
    }

    @Test
    public void testGenAir()
    {
        world.clear();
        assertEquals(Blocks.air, world.getBlock(0, 0, 0));
    }

    @Test
    public void testGenSuperFlat()
    {
        world.clear();
        world.genSuperFlat();
        assertEquals(Blocks.bedrock, world.getBlock(0, 0, 0));
        assertEquals(Blocks.dirt, world.getBlock(0, 1, 0));
        assertEquals(Blocks.grass, world.getBlock(0, 3, 0));
    }

    @Test
    public void testGenCustom()
    {
        world.clear();
        world.genCustom("minecraft:bedrock,5*minecraft:sand,minecraft:dirt");
        assertEquals(Blocks.bedrock, world.getBlock(0, 0, 0));
        assertEquals(Blocks.sand, world.getBlock(0, 1, 0));
        assertEquals(Blocks.sand, world.getBlock(0, 2, 0));
        assertEquals(Blocks.sand, world.getBlock(0, 3, 0));
        assertEquals(Blocks.sand, world.getBlock(0, 4, 0));
        assertEquals(Blocks.sand, world.getBlock(0, 5, 0));
        assertEquals(Blocks.dirt, world.getBlock(0, 6, 0));
        assertEquals(Blocks.air, world.getBlock(0, 7, 0));
    }
}
