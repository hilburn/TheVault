package hilburnlib.junit.test;

import hilburnlib.junit.minecraft.block.TestBlock;
import hilburnlib.junit.minecraft.item.TestItem;
import hilburnlib.junit.minecraft.runner.MCTestRunner;
import hilburnlib.registry.Register;
import hilburnlib.registry.Registerer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(MCTestRunner.class)
public class RegisterTest
{
    @Register(name = "testBlock")
    public static TestBlock testBlock;
    
    @Register(name = "testItem")
    public static TestItem testItem;
    
    public RegisterTest()
    {
        Registerer.scan(RegisterTest.class);      
    }
    
    @Test
    public void testBlock()
    {
        assertNotNull(testBlock);
        assertEquals(Material.circuits, testBlock.getMaterial());
    }

    @Test
    public void testItem()
    {
        assertNotNull(testItem);
        assertEquals(CreativeTabs.tabMisc, testItem.getCreativeTab());
    }
}
