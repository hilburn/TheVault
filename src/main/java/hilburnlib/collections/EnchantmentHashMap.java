package hilburnlib.collections;

import gnu.trove.map.custom_hash.TObjectByteCustomHashMap;
import hilburnlib.collections.strategy.EnchantmentHashingStrategy;
import net.minecraft.enchantment.Enchantment;

/**
 * A simple Custom HashMap that uses the enchantment id as the hash - allowing it to be easily saved
 */
public class EnchantmentHashMap extends TObjectByteCustomHashMap<Enchantment>
{
    public EnchantmentHashMap()
    {
        super(new EnchantmentHashingStrategy());
    }
}
