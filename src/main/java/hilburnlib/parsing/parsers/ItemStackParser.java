package hilburnlib.parsing.parsers;

import cpw.mods.fml.common.registry.GameRegistry;
import hilburnlib.reference.NBTTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemStackParser extends ParserBase
{

    ItemStackParser()
    {
        super(50);
    }

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
            if (stack.hasTagCompound())
                result.put(NBTTags.NBT, stack.getTagCompound().toString());
            return result;
        }
        return null;
    }

    @Override
    public <T> T fromLua(Object o)
    {
        if (o instanceof Map)
        {
            Map stack = (Map)o;
            if (stack.containsKey(NBTTags.ITEM) && stack.containsKey(NBTTags.AMOUNT) && stack.containsKey(NBTTags.DAMAGE))
            {
                String[] split = ((String)stack.get(NBTTags.ITEM)).split(":");
                Item item = GameRegistry.findItem(split[0],split[1]);
                if (item==null) return null;
                int stackSize = ((Number)stack.get(NBTTags.AMOUNT)).intValue();
                int damage = ((Number)stack.get(NBTTags.DAMAGE)).intValue();
                return (T)new ItemStack(item,stackSize,damage);
            }
        }
        return null;
    }

    @Override
    public NBTBase toNBT(Object o)
    {
        if (o instanceof ItemStack)
        {
            NBTTagCompound result = new NBTTagCompound();
            result.setTag(VAL,((ItemStack)o).writeToNBT(new NBTTagCompound()));
            result.setByte(TYPE,key);
            return result;
        }
        return null;
    }

    @Override
    public <T> T fromNBT(NBTTagCompound o)
    {
        if (o.getByte(TYPE)!=key) return null;
        return (T) ItemStack.loadItemStackFromNBT(o.getCompoundTag(VAL));
    }
}
