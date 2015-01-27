package hilburnlib.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs
{
    private ItemStack iconItemStack;
    
    public CreativeTab(String label, Item iconItem)
    {
        this(label, new ItemStack(iconItem));
    }

    public CreativeTab(String label, Item iconItem, int meta)
    {
        this(label, new ItemStack(iconItem, 1, meta));
    }

    public CreativeTab(String label, ItemStack iconItemStack)
    {
        super(label);
        this.iconItemStack = iconItemStack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getTabIconItem()
    {
        return iconItemStack.getItem();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getIconItemStack()
    {
        return this.iconItemStack;
    }
}
