package thevault.recipes;

import net.minecraft.item.ItemStack;

import java.util.Map;

public interface IRecipeWrapper
{

    /**
     * @return ItemStackMap of the recipe components
     */
    public Map<ItemStack,? extends Number> getComponents();

    /**
     * @return Output ItemStack of the recipe this wraps
     */
    public ItemStack getOutputItem();

    public IRecipeWrapper getWrapped(Object recipe);

    public boolean canWrap(Object recipe);
}
