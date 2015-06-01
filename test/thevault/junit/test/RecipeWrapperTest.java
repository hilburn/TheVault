package thevault.junit.test;

import thevault.collections.ItemStackMap;
import thevault.collections.ItemStackSet;
import thevault.junit.minecraft.runner.MCTestRunner;
import thevault.recipes.FurnaceWrapper;
import thevault.recipes.IRecipeWrapper;
import thevault.recipes.RecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(MCTestRunner.class)
public class RecipeWrapperTest
{
    public static List<IRecipeWrapper> recipeWrappers = new ArrayList<>();
    public static ItemStackMap<Double> recipeValues = new ItemStackMap<>(true);
    public static ItemStackMap<List<IRecipeWrapper>> wrappedRecipes = new ItemStackMap<>(true);

    public RecipeWrapperTest()
    {
        recipeWrappers.add(new RecipeWrapper());
    }

    @Test
    public void testRecipeWrapper()
    {
        List recipes = CraftingManager.getInstance().getRecipeList();
        for (Object recipe: recipes)
        {
            IRecipeWrapper recipeWrapper = null;
            for (IRecipeWrapper customWrapper:recipeWrappers)
            {
                if (customWrapper.canWrap(recipe))
                {
                    recipeWrapper = customWrapper.getWrapped(recipe);
                    break;
                }
            }
            if (recipeWrapper != null)
            {
                addRecipe(recipeWrapper);
            }
        }
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        for (Map.Entry<ItemStack,ItemStack> recipe : smelting.entrySet())
        {
            addRecipe(new FurnaceWrapper(recipe.getKey(),recipe.getValue()));
        }
        for (List<IRecipeWrapper> wrappers : wrappedRecipes.values())
        {
            cullRecipes(wrappers);
        }
        return;
    }

    public void addRecipe(IRecipeWrapper recipe)
    {
        List<IRecipeWrapper> wrappers = wrappedRecipes.get(recipe.getOutputItem());
        if (wrappers==null) wrappers = new ArrayList<>();
        wrappers.add(recipe);
        wrappedRecipes.put(recipe.getOutputItem(),wrappers);
    }

    public IRecipeWrapper cullRecipes(List<IRecipeWrapper> toCull)
    {
        return cullRecipes(toCull, new ItemStackSet(true), true);
    }

    public IRecipeWrapper cullRecipes(List<IRecipeWrapper> toCull, boolean cullHighest)
    {
        return cullRecipes(toCull, new ItemStackSet(true), cullHighest);
    }

    public IRecipeWrapper cullRecipes(List<IRecipeWrapper> toCull, ItemStackSet outputs, boolean cullHighest)
    {
        if (toCull.size()==0) return null;
        IRecipeWrapper result = null;
        Double val = null;
        for (Iterator<IRecipeWrapper> itr = toCull.iterator(); itr.hasNext();)
        {
            IRecipeWrapper toCheck = itr.next();
            double checkVal = getRecipeValue(toCheck, outputs, cullHighest);
            if (val == null || (checkVal<val)==cullHighest)
            {
                result = toCheck;
                val = checkVal;
            }
        }
        return result;
    }

    public double getRecipeValue(IRecipeWrapper recipe, ItemStackSet outputs, boolean cullHighest)
    {
        ItemStack output = recipe.getOutputItem();
        if (recipeValues.containsKey(output)) return recipeValues.get(output);
        double value = 0;
        ItemStackSet nextOutput = outputs.copy();
        nextOutput.add(output);
        for (ItemStack component : recipe.getComponents().keySet())
        {
            Double multiplier = recipe.getComponents().get(component).doubleValue();
            if (outputs.contains(component))
            {
                //TODO this is not a good solution to loops - have a think when sober
                return cullHighest?multiplier:0D;
            }
            if (recipeValues.contains(component))
                value+=recipeValues.get(component)*multiplier;
            else if (wrappedRecipes.contains(component))
            {
                IRecipeWrapper culled = cullRecipes(wrappedRecipes.get(component), nextOutput, cullHighest);
                wrappedRecipes.put(component,Arrays.asList(culled));
                if (recipeValues.contains(component))
                    value += recipeValues.get(component);
                else value += cullHighest?multiplier:0D;
            }
            else
            {
                recipeValues.put(component,cullHighest?0D:1D);
                value += cullHighest?0D:multiplier;
            }
        }
        value /= output.stackSize;
        recipeValues.put(output,value);
        return value;
    }




}