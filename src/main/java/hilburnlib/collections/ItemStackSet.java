package hilburnlib.collections;

import gnu.trove.set.hash.TCustomHashSet;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import net.minecraft.item.ItemStack;

public class ItemStackSet extends TCustomHashSet<ItemStack>
{
    public ItemStackSet()
    {
        super(new ItemStackHashingStrategy());
    }
}
