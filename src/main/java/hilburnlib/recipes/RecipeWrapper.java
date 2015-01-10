package hilburnlib.recipes;

import hilburnlib.collections.ItemStackMap;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import hilburnlib.items.ItemUtils;
import hilburnlib.java.collection.CollectionHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.List;
import java.util.Map;

public class RecipeWrapper implements IRecipeWrapper
{
    protected static final ItemStackHashingStrategy HASHING_STRATEGY = new ItemStackHashingStrategy();
    ItemStackMap<Integer> components = new ItemStackMap<>();
    ItemStack output;

    protected RecipeWrapper(ItemStack stack)
    {
        output = stack;
    }

    public RecipeWrapper(ShapedRecipes recipe)
    {
        this(recipe.getRecipeOutput());
        addInputs(recipe.recipeItems);
    }

    public RecipeWrapper(ShapelessRecipes recipe)
    {
        this(recipe.getRecipeOutput());
        addInputs(recipe.recipeItems);
    }

    public RecipeWrapper(ShapedOreRecipe recipe)
    {
        this(recipe.getRecipeOutput());
        addOreInputs(recipe.getInput());
    }

    public RecipeWrapper(ItemStack smeltingIn, ItemStack smeltingOut)
    {
        this(smeltingOut);
        addInputs(smeltingIn);
    }

    public RecipeWrapper(ShapelessOreRecipe recipe)
    {
        this(recipe.getRecipeOutput());
        addOreInputs(recipe.getInput());
    }

    protected void addOreInputs(List<Object> stacks)
    {
        addOreInputs(CollectionHelper.toArray(stacks));
    }

    protected void addOreInputs(Object... stacks)
    {
        for (Object component : stacks)
        {
            add(getItemStack(component));
        }
    }

    protected void addInputs(List<ItemStack> stacks)
    {
        addInputs(CollectionHelper.toArray(stacks));
    }

    protected void addInputs(ItemStack... stacks)
    {
        for (ItemStack itemStack : stacks)
        {
            add(itemStack);
        }
    }

    protected void add(ItemStack itemStack)
    {
        if (ItemUtils.isValid(itemStack))
        {
            ItemUtils.addItem(components, itemStack, itemStack.stackSize);
        }
    }

    protected static ItemStack getItemStack(Object obj)
    {
        if (obj == null) return null;
        if (obj instanceof ItemStack) return (ItemStack)obj;
        if (obj instanceof List && ((List)obj).size() > 0)
        {
            return getItemStack(((List)obj).get(0));
        }
        if (obj instanceof String) return OreDictionary.getOres((String)obj).get(0);
        return null;
    }

    public static RecipeWrapper[] getSmeltingRecipes(Map.Entry<ItemStack, ItemStack> smeltingRecipe)
    {
        return new RecipeWrapper[]{new RecipeWrapper(smeltingRecipe.getKey(), smeltingRecipe.getValue()), new RecipeWrapper(smeltingRecipe.getValue(), smeltingRecipe.getKey())};
    }

    @Override
    public int hashCode()
    {
        return HASHING_STRATEGY.computeHashCode(output);
    }

    @Override
    public ItemStackMap getComponents()
    {
        return components;
    }

    @Override
    public ItemStack getOutputItem()
    {
        return output;
    }
}
