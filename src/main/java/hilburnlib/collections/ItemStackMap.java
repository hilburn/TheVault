package hilburnlib.collections;

import gnu.trove.map.hash.TCustomHashMap;
import net.minecraft.item.ItemStack;

public class ItemStackMap<V> extends TCustomHashMap<ItemStack, V>
{
    public ItemStackMap()
    {
        super(new ItemStackHashingStrategy());
    }
}
