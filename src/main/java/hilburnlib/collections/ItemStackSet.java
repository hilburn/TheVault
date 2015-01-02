package hilburnlib.collections;

import gnu.trove.set.hash.TCustomHashSet;
import net.minecraft.item.ItemStack;

public class ItemStackSet extends TCustomHashSet<ItemStack>
{
    public ItemStackSet()
    {
        super(new ItemStackHashingStrategy());
    }
}
