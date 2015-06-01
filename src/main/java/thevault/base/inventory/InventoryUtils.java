package thevault.base.inventory;

import thevault.reference.NBTTags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.List;

public class InventoryUtils
{
    public static int locateItem(ItemStack stack, ItemStack[] inventory)
    {
        return locateItem(stack, inventory, false);
    }

    public static int locateItem(ItemStack stack, ItemStack[] inventory, boolean matchSize)
    {
        for (int i = 0; i < inventory.length; i++)
        {
            ItemStack iStack = inventory[i];
            if (iStack != null && stack.isItemEqual(stack))
            {
                if (!matchSize || stack.stackSize >= iStack.stackSize) return i;
            }
        }
        return -1;
    }

    public static boolean isValidMerge(ItemStack current, ItemStack toAdd, int maxStackSize)
    {
        if (current == null) return true;
        if (current.isItemEqual(toAdd) && ItemStack.areItemStackTagsEqual(current, toAdd))
        {
            int newSize = current.stackSize + toAdd.stackSize;
            return newSize < current.getMaxStackSize() && newSize < maxStackSize;
        }
        return false;
    }

    public static boolean containsItem(ItemStack stack, ItemStack[] inventory)
    {
        return containsItem(stack, inventory, false);
    }

    public static boolean containsItem(ItemStack stack, ItemStack[] inventory, boolean matchSize)
    {
        return !(locateItem(stack, inventory, matchSize) < 0);
    }

    public static NBTTagCompound getInventoryCompound(ItemStack[] items)
    {
        NBTTagCompound result = new NBTTagCompound();
        result.setInteger(NBTTags.CAPACITY, items.length);
        result.setTag(NBTTags.INVENTORY, inventoryToNBTList(items));
        return result;
    }

    public static ItemStack[] getInventoryFromCompound(NBTTagCompound tagCompound)
    {
        int size = tagCompound.getInteger(NBTTags.CAPACITY);
        ItemStack[] result = new ItemStack[size];
        return nbtListToInventory(tagCompound, result);
    }

    public static NBTTagList inventoryToNBTList(List<ItemStack> items)
    {
        return inventoryToNBTList(items.toArray(new ItemStack[items.size()]));
    }

    public static NBTTagList inventoryToNBTList(ItemStack... items)
    {
        NBTTagList result = new NBTTagList();
        for (int i = 0; i < items.length; i++)
        {
            ItemStack item = items[i];
            if (item == null) continue;
            NBTTagCompound slot = new NBTTagCompound();
            slot.setByte(NBTTags.SLOT, (byte)i);
            item.writeToNBT(slot);
            result.appendTag(slot);
        }
        return result;
    }

    public static ItemStack[] nbtListToInventory(NBTTagCompound tagCompound, ItemStack[] inventory)
    {
        return nbtListToInventory(tagCompound, NBTTags.INVENTORY, inventory);
    }

    public static ItemStack[] nbtListToInventory(NBTTagCompound tagCompound, String tag, ItemStack[] inventory)
    {
        if (tagCompound == null || !tagCompound.hasKey(tag, NBTTags.TAG_LIST)) return new ItemStack[0];
        return nbtListToInventory(tagCompound.getTagList(tag, NBTTags.TAG_COMPOUND), inventory);
    }

    public static ItemStack[] nbtListToInventory(NBTTagList tagList, ItemStack[] inventory)
    {
        if (inventory == null) return new ItemStack[0];
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound slot = tagList.getCompoundTagAt(i);
            byte pos = slot.getByte(NBTTags.SLOT);
            if (pos > 0 && pos < inventory.length)
            {
                inventory[pos] = ItemStack.loadItemStackFromNBT(slot);
            }
        }
        return inventory;
    }
}
