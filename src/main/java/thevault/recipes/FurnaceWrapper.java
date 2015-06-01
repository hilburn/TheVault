package thevault.recipes;

import thevault.collections.ItemStackMap;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Map;

public class FurnaceWrapper implements IRecipeWrapper
{
    private ItemStack output;
    private ItemStackMap<Integer> input = new ItemStackMap<>();

    public FurnaceWrapper()
    {}

    public FurnaceWrapper(ItemStack... itemStacks)
    {
        input.put(itemStacks[0],1);
        output = itemStacks[1];
    }

    /**
     * @return ItemStackMap of the recipe components
     */
    @Override
    public Map<ItemStack, ? extends Number> getComponents()
    {
        return input;
    }

    /**
     * @return Output ItemStack of the recipe this wraps
     */
    @Nonnull
    @Override
    public ItemStack getOutputItem()
    {
        return output;
    }

    @Override
    public IRecipeWrapper getWrapped(Object recipe)
    {
        return new FurnaceWrapper((ItemStack[])recipe);
    }

    @Override
    public boolean canWrap(Object recipe)
    {
        return recipe instanceof ItemStack[] && ((ItemStack[])recipe).length==2;
    }
}
