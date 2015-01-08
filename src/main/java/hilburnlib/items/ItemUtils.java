package hilburnlib.items;

import hilburnlib.collections.ItemStackMap;
import hilburnlib.nbt.NBTHelper;
import hilburnlib.utils.ByteArrayHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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

    public static ItemStack getItemStack(ItemStack stack, int newSize)
    {
        ItemStack result = stack.copy();
        result.stackSize = newSize;
        return result;
    }

    public static boolean isValid(ItemStack stack)
    {
        return !(stack==null || stack.getItem()==null);
    }
    
    public static NBTTagCompound addObjectToNBTTagCompound(NBTTagCompound nbt, String name, Object o)
    {
        nbt.setTag(name, NBTHelper.writeToNBT(o));
        return nbt;
    }
    
    public static <T> T getObjectFromNBTTagCompound(NBTTagCompound nbt, String name)
    {
        return NBTHelper.readFromNBT(nbt.getCompoundTag(name));
    }

    public static ItemStackMap<Float> mergeMaps(ItemStackMap<Float> map, ItemStackMap<Float> merge)
    {
        for (ItemStack key:merge.keySet())
        {
            addItem(map,key,merge.get(key));
        }
        return map;
    }

    public static void addItem(ItemStackMap<Float> map, ItemStack key, float value)
    {
        if (map.contains(key)) map.put(key,map.get(key) + value);
        else map.put(key,value);
    }
}
