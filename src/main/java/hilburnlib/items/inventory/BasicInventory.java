package hilburnlib.items.inventory;

import hilburnlib.reference.NBTTags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BasicInventory implements IInventory
{
    private ItemStack[] inventory;
    private int stackLimit;

    public BasicInventory(int size)
    {
        this(new ItemStack[size]);
    }

    public BasicInventory(ItemStack[] inventory)
    {
        this(64, inventory);
    }

    public BasicInventory(int size, int limit)
    {
        this(limit,new ItemStack[size]);
    }

    public BasicInventory(int limit, ItemStack[] inventory)
    {
        this.stackLimit = limit;
        this.inventory = inventory;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {

    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return stackLimit;
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return false;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return true;
    }

    public void writeToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setTag(NBTTags.INVENTORY,InventoryUtils.getInventoryCompound(inventory));
    }

    public void readFromNBT(NBTTagCompound tagCompound)
    {
        inventory = InventoryUtils.getInventoryFromCompound(tagCompound.getCompoundTag(NBTTags.INVENTORY));
    }
}
