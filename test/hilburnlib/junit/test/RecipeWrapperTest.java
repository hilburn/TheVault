package hilburnlib.junit.test;

import hilburnlib.collections.ItemStackMap;
import hilburnlib.collections.ItemStackSet;
import hilburnlib.junit.minecraft.runner.MCTestRunner;
import hilburnlib.recipes.IRecipeWrapper;
import hilburnlib.recipes.RecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
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
                List<IRecipeWrapper> wrappers = wrappedRecipes.get(recipeWrapper.getOutputItem());
                if (wrappers==null) wrappers = new ArrayList<>();
                wrappers.add(recipeWrapper);
                wrappedRecipes.put(recipeWrapper.getOutputItem(),wrappers);
            }
        }
        for (List<IRecipeWrapper> wrappers : wrappedRecipes.values())
        {
            cullRecipes(wrappers,false);
        }
        return;
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
            if (nextOutput.contains(component))
            {
                //TODO this is not a good solution to loops - have a think when sober
                return cullHighest?1:0;
            }
            if (recipeValues.contains(component))
                value+=recipeValues.get(component)*recipe.getComponents().get(component).doubleValue();
            else if (wrappedRecipes.contains(component))
            {
                IRecipeWrapper culled = cullRecipes(wrappedRecipes.get(component), nextOutput, cullHighest);
                wrappedRecipes.put(component,Arrays.asList(culled));
                if (recipeValues.contains(component))
                    value += recipeValues.get(component)*recipe.getComponents().get(component).doubleValue();
                else value += cullHighest?1D:0D;
            }
            else
            {
                recipeValues.put(component,cullHighest?0D:1D);
                value += cullHighest?0D:1D*recipe.getComponents().get(component).doubleValue();
            }
        }
        value /= output.stackSize;
        recipeValues.put(output,value);
        return value;
    }




}