package hilburnlib.collections.strategy;

import gnu.trove.strategy.HashingStrategy;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentHashingStrategy implements HashingStrategy<Enchantment>
{
    @Override
    public int computeHashCode(Enchantment enchantment)
    {
        return enchantment.effectId;
    }

    @Override
    public boolean equals(Enchantment o1, Enchantment o2)
    {
        return o1.equals(o2);
    }
}
