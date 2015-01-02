package hilburnlib.collections;

import gnu.trove.map.hash.TCustomHashMap;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import net.minecraft.item.ItemStack;

public class ItemStackMap<V> extends TCustomHashMap<ItemStack, V>
{
    public ItemStackMap()
    {
        super(new ItemStackHashingStrategy());
    }
}
