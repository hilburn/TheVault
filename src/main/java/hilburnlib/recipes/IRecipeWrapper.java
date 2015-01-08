package hilburnlib.recipes;

import hilburnlib.collections.ItemStackMap;
import net.minecraft.item.ItemStack;

public interface IRecipeWrapper
{
    public ItemStackMap getComponents();
    public ItemStack getOutputItem();
    public int getOutputStackSie();
}
