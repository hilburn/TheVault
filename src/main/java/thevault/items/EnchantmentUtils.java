package thevault.items;

import thevault.collections.EnchantmentHashMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class EnchantmentUtils
{
    public static EnchantmentHashMap getEnchantments(ItemStack is)
    {
        Map<Integer, Integer> enchantments = EnchantmentHelper.getEnchantments(is);
        if (enchantments == null)
            return null;
        EnchantmentHashMap ench = new EnchantmentHashMap();
        for (Map.Entry<Integer, Integer> entry : enchantments.entrySet())
        {
            Enchantment e = Enchantment.enchantmentsList[entry.getKey()];
            ench.put(e, entry.getValue().byteValue());
        }
        return ench;
    }

    public static void applyEnchantments(ItemStack is, EnchantmentHashMap enchantmentMap)
    {
        for (Enchantment enchantment : enchantmentMap.keySet())
        {
            int level = enchantmentMap.get(enchantment);
            if (level > 0)
            {
                if (is.getItem() == Items.enchanted_book)
                {
                    Items.enchanted_book.addEnchantment(is, new EnchantmentData(enchantment, level));
                } else
                {
                    is.addEnchantment(enchantment, level);
                }
            }
        }
    }

}
