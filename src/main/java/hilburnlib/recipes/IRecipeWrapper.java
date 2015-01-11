package hilburnlib.recipes;

import hilburnlib.collections.ItemStackMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import javax.annotation.Nonnull;
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
    @Nonnull
    public ItemStack getOutputItem();

    public IRecipeWrapper getWrapped(Object recipe);

    public boolean canWrap(Object recipe);
}
