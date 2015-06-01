package thevault.collections;

import gnu.trove.set.hash.TCustomHashSet;
import gnu.trove.strategy.HashingStrategy;
import thevault.collections.strategy.FlatItemStackHashingStrategy;
import thevault.collections.strategy.ItemStackHashingStrategy;
import thevault.java.util.ICopy;
import net.minecraft.item.ItemStack;

/**
 * A HashSet implementation of {@link thevault.collections.ItemStackMap}
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
