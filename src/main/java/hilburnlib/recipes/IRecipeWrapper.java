package hilburnlib.recipes;

import hilburnlib.collections.ItemStackMap;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface IRecipeWrapper
{

    /**
     * @return  ItemStackMap of the recipe components
     */
    public ItemStackMap getComponents();

    /**
     * @return  Output ItemStack of the recipe this wraps
     */
    @Nonnull
    public ItemStack getOutputItem();
}
