package hilburnlib.recipes;

import hilburnlib.collections.ItemStackMap;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import hilburnlib.items.ItemUtils;
import hilburnlib.java.collection.CollectionHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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

    public RecipeWrapper(){};

    public RecipeWrapper(ItemStack stack)
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
        for (ItemStack itemStack : stacks)
        {
            add(itemStack);
        }
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

    @Override
    public int hashCode()
    {
        return HASHING_STRATEGY.computeHashCode(output);
    }

    @Override
    public Map<ItemStack,Integer> getComponents()
    {
        return components;
    }

    @Override
    public ItemStack getOutputItem()
    {
        return output;
    }

    @Override
    public IRecipeWrapper getWrapped(Object recipe)
    {
        if (recipe instanceof ShapedRecipes)
            return new RecipeWrapper((ShapedRecipes)recipe);
        else if (recipe instanceof ShapelessRecipes)
            return new RecipeWrapper((ShapelessRecipes)recipe);
        else if (recipe instanceof ShapedOreRecipe)
            return new RecipeWrapper((ShapedOreRecipe)recipe);
        else if (recipe instanceof ShapelessOreRecipe)
            return new RecipeWrapper((ShapelessOreRecipe)recipe);
        return null;
    }

    @Override
    public boolean canWrap(Object recipe)
    {
        if (!(recipe instanceof IRecipe) || invalidOutput(((IRecipe)recipe).getRecipeOutput())) return false;
        return recipe instanceof ShapedRecipes || recipe instanceof ShapelessRecipes || recipe instanceof ShapedOreRecipe || recipe instanceof ShapelessRecipes;
    }

    public boolean invalidOutput(ItemStack stack)
    {
        return stack == null || stack.stackSize<1 || stack.getItemDamage()<0;
    }
}
