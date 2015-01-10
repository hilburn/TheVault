package hilburnlib.java.predicate;

import net.minecraft.item.ItemStack;

public class EqualItemStackPredicate implements Predicate<ItemStack>
{
    @Override
    public boolean compare(ItemStack a, ItemStack b)
    {
        return a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage() && a.stackSize == b.stackSize;
    }
}
