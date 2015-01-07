package hilburnlib.collections;

import gnu.trove.set.hash.TCustomHashSet;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import net.minecraft.item.ItemStack;

/**
 * A HashSet implementation of {@link hilburnlib.collections.ItemStackMap}
 */
public class ItemStackSet extends TCustomHashSet<ItemStack>
{
    public ItemStackSet()
    {
        super(new ItemStackHashingStrategy());
    }
}
