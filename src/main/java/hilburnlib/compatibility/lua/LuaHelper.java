package hilburnlib.compatibility.lua;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LuaHelper
{
    public static Map<String, Object> stackToMap(ItemStack stack)
    {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        if (stack == null || stack.getItem() == null) return null;
        GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(stack.getItem());
        if (id == null) return null;
        result.put("item", id.toString());
        result.put("quantity", stack.stackSize);
        result.put("damage", stack.getItemDamage());
        result.put("name", stack.getDisplayName());
        if (stack.hasTagCompound())
            result.put("nbt", stack.getTagCompound().toString());
        return result;
    }

    public static ForgeDirection getDirection(String string)
    {
        if (string.equalsIgnoreCase("unknown")) return ForgeDirection.UNKNOWN;
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if (string.equalsIgnoreCase(direction.name())) return direction;
        }
        return null;
    }
}
