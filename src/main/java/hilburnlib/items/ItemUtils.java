package hilburnlib.items;

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
    
    public static NBTTagCompound addObjectToNBTTagCompound(NBTTagCompound nbt, String name, Object o)
    {
        nbt.setByteArray(name, ByteArrayHelper.toByteArray(o));
        return nbt;
    }
    
    public static <T> T getObjectToNBTTagCompound(NBTTagCompound nbt, String name)
    {
        return ByteArrayHelper.fromByteArray(nbt.getByteArray(name));
    }
}
