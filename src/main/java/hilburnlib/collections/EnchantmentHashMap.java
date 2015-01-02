package hilburnlib.collections;

import gnu.trove.map.custom_hash.TObjectByteCustomHashMap;
import hilburnlib.collections.strategy.EnchantmentHashingStrategy;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentHashMap extends TObjectByteCustomHashMap<Enchantment>
{
    public EnchantmentHashMap()
    {
        super(new EnchantmentHashingStrategy());
    }
}
