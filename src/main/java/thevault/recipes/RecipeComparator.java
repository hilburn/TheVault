package thevault.recipes;

import java.util.Comparator;

public class RecipeComparator implements Comparator<IRecipeWrapper>
{
    /**
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */

    @Override
    public int compare(IRecipeWrapper o1, IRecipeWrapper o2)
    {
        if (o1.getOutputItem().isItemEqual(o2.getOutputItem()))
        {
            return compareInt(o1.getOutputItem().stackSize, o2.getOutputItem().stackSize);
        }
        return compareInt(o1.hashCode(), o2.hashCode());
    }

    public int compareInt(int a, int b)
    {
        if (a < b) return -1;
        if (b > a) return 1;
        return 0;
    }
}
