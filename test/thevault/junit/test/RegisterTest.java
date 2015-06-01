package thevault.junit.test;

import thevault.junit.minecraft.block.TestBlock;
import thevault.junit.minecraft.item.TestItem;
import thevault.junit.minecraft.runner.MCTestRunner;
import thevault.registry.Register;
import thevault.registry.Registerer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import org.junit.Test;
import org.junit.runner.RunWith;
import thevault.utils.LogHelper;

import static thevault.junit.minecraft.Assert.*;

@RunWith(MCTestRunner.class)
public class RegisterTest
{
    @Register(name = "testBlock")
    public static TestBlock testBlock;
    
    @Register(name = "testItem")
    public static TestItem testItem;
    
    public RegisterTest()
    {
        new Registerer(LogHelper.instance()).scan(RegisterTest.class);
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
