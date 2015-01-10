package hilburnlib.java.predicate;

import net.minecraft.item.ItemStack;

public class EqualItemStackPredicate implements Predicate<ItemStack>
{
    @Override
    public boolean compare(ItemStack a, ItemStack b)
    {
        return a.isItemEqual(b) && a.stackSize == b.stackSize && ItemStack.areItemStacksEqual(a, b);
    }
}
