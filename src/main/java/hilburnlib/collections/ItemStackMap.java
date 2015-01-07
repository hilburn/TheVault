package hilburnlib.collections;

import gnu.trove.map.hash.TCustomHashMap;
import hilburnlib.collections.strategy.ItemStackHashingStrategy;
import net.minecraft.item.ItemStack;

/**
 * A HashMap<ItemStack, V></ItemStack,> that implements a proper hashing function on ItemStacks, allowing them to be stored and queried in a HashMap
 * Example usage:
 *      ItemStackMap<ItemStack></ItemStack> stackMap = new ItemStackMap<ItemStack></ItemStack>();
 *      stackMap.put(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.stone));
 *      stackMap.contains(new ItemStack(Blocks.cobblestone)); (returns true)
 * @param <V>
 */
public class ItemStackMap<V> extends TCustomHashMap<ItemStack, V>
{
    public ItemStackMap()
    {
        super(new ItemStackHashingStrategy());
    }
}
