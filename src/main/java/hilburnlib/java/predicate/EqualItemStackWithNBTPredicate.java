package hilburnlib.java.predicate;

import net.minecraft.item.ItemStack;

/**
 * Predicate that checks if both {@link net.minecraft.item.ItemStack} objects are equal
 */
public class EqualItemStackWithNBTPredicate extends EqualItemStackPredicate
{
    @Override
    public boolean compare(ItemStack a, ItemStack b)
    {
        return super.compare(a, b) && a.getTagCompound().equals(b.getTagCompound());
    }
}
