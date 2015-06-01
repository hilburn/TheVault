package thevault.java.predicate;

import net.minecraft.item.ItemStack;

/**
 * Predicate that checks if both {@link net.minecraft.item.ItemStack} objects are equal
 * ignores NBT Data
 */
public class EqualItemStackPredicate implements Predicate<ItemStack>
{
    @Override
    public boolean compare(ItemStack a, ItemStack b)
    {
        return a.isItemEqual(b) && a.stackSize == b.stackSize && ItemStack.areItemStacksEqual(a, b);
    }
}
