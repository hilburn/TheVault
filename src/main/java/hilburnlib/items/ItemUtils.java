package hilburnlib.items;

import hilburnlib.reference.NBTTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import scala.actors.threadpool.Arrays;

import java.util.List;

public class ItemUtils
{
    public static ItemStack getItemStack(Item item, NBTTagCompound tag) {
        return getItemStack(item,1,0,tag);
    }

    public static ItemStack getItemStack(Item item, int size, NBTTagCompound tag) {
        return getItemStack(item, size, 0, tag);
    }
    
    public static ItemStack getItemStack(Item item, int size, int damage, NBTTagCompound tag) {
        return getItemStack(new ItemStack(item, size, damage), tag);
    }

    public static ItemStack getItemStack(Block block, NBTTagCompound tag) {
        return getItemStack(block,1,0,tag);
    }

    public static ItemStack getItemStack(Block block, int size, NBTTagCompound tag) {
        return getItemStack(block, size, 0, tag);
    }

    public static ItemStack getItemStack(Block block, int size, int damage, NBTTagCompound tag) {
        return getItemStack(new ItemStack(block, size, damage),tag);
    }

    public static ItemStack getItemStack(ItemStack stack, NBTTagCompound tag)
    {
        stack.stackTagCompound = tag;
        return stack;
    }

    public static NBTTagList inventoryToNBTList(List<ItemStack> items)
    {
        return inventoryToNBTList(items.toArray(new ItemStack[items.size()]));
    }

    public static NBTTagList inventoryToNBTList(ItemStack... items)
    {
        NBTTagList result = new NBTTagList();
        for (int i = 0; i<items.length;i++)
        {
            ItemStack item = items[i];
            if (item==null) continue;
            NBTTagCompound slot = new NBTTagCompound();
            slot.setByte(NBTTags.SLOT,(byte)i);
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
        if (tagCompound == null || !tagCompound.hasKey(tag,NBTTags.TAG_LIST)) return new ItemStack[0];
        return nbtListToInventory(tagCompound.getTagList(tag,NBTTags.TAG_COMPOUND), inventory);
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
