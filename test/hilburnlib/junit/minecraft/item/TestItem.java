package hilburnlib.junit.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TestItem extends Item
{
    public TestItem()
    {
        setCreativeTab(CreativeTabs.tabMisc);
        setContainerItem(Items.flower_pot);
    }
}
