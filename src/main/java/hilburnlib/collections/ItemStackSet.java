package hilburnlib.collections;

import gnu.trove.set.hash.TCustomHashSet;
import gnu.trove.strategy.HashingStrategy;
import hilburnlib.collections.strategy.FlatItemStackHashingStrategy;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import hilburnlib.java.util.ICopy;
import net.minecraft.item.ItemStack;

/**
 * A HashSet implementation of {@link hilburnlib.collections.ItemStackMap}
 */
public class ItemStackSet extends TCustomHashSet<ItemStack> implements ICopy<ItemStackSet>
{
    private static final HashingStrategy HASHING_STRATEGY = new ItemStackHashingStrategy();
    private static final HashingStrategy FLAT_HASHING_STRATEGY = new FlatItemStackHashingStrategy();

    public ItemStackSet()
    {
        this(false);
    }

    public ItemStackSet(boolean flat)
    {
        super(flat?FLAT_HASHING_STRATEGY:HASHING_STRATEGY);
    }

    @Override
    public ItemStackSet copy()
    {
        ItemStackSet copy = new ItemStackSet();
        copy.strategy = this.strategy;
        copy.addAll(this);
        return copy;
    }
}
