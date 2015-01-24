package hilburnlib.compatibility.lua.conversion;

import cpw.mods.fml.common.registry.GameRegistry;
import hilburnlib.reference.NBTTags;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemStackConversion implements ILuaConversion
{

    @Override
    public Object toLua(Object o)
    {
        if (o instanceof ItemStack)
        {
            ItemStack stack = (ItemStack)o;
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            if (stack.getItem() == null) return null;
            GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(stack.getItem());
            if (id == null) return null;
            result.put(NBTTags.ITEM, id.toString());
            result.put(NBTTags.AMOUNT, stack.stackSize);
            result.put(NBTTags.DAMAGE, stack.getItemDamage());
            result.put(NBTTags.NAME, stack.getDisplayName());
            if (stack.hasTagCompound())
                result.put(NBTTags.NBT, stack.getTagCompound().toString());
            return result;
        }
        return null;
    }

    @Override
    public <T> T fromLua(Object o)
    {
        return null;
    }
}
