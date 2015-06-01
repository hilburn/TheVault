package thevault.base.inventory;

import thevault.base.interfaces.ISaveable;
import thevault.reference.NBTTags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BasicInventory implements IInventory, ISaveable<BasicInventory>
{
    private ItemStack[] inventory;
    private int stackLimit;
    private String name;
    private IInventory owner;

    public BasicInventory()
    {
    }

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
        this(limit, new ItemStack[size]);
    }

    public BasicInventory(int limit, ItemStack[] inventory)
    {
        this.stackLimit = limit;
        this.inventory = inventory;
    }

    public BasicInventory(NBTTagCompound tagCompound)
    {
        readFromNBT(tagCompound);
    }

    public BasicInventory(int slots, IInventory owner)
    {
        this(slots);
        this.owner = owner;
    }

    public BasicInventory(NBTTagCompound tagCompound, IInventory owner)
    {
        readFromNBT(tagCompound);
        this.owner = owner;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return isSlotFilled(slot) ? inventory[slot] : null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack split;
            if (this.inventory[slot].stackSize <= quantity)
            {
                split = this.inventory[slot];
                this.inventory[slot] = null;
                return split;
            } else
            {
                split = this.inventory[slot].splitStack(quantity);
                if (this.inventory[slot].stackSize == 0)
                {
                    this.inventory[slot] = null;
                }
                this.markDirty();
                return split;
            }
        } else
        {
            return null;
        }
    }

    public boolean isSlotFilled(int slot)
    {
        return slot < getInventoryStackLimit() && inventory[slot] != null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventory[slot] = stack;
        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return name;
    }

    public void setInventoryName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return name != null;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return stackLimit;
    }

    @Override
    public void markDirty()
    {
        owner.markDirty();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
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
        return slot < getSizeInventory() && InventoryUtils.isValidMerge(inventory[slot], itemStack, this.getInventoryStackLimit());
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        NBTTagCompound result = new NBTTagCompound();
        result.setInteger(NBTTags.CAPACITY, inventory.length);
        if (hasCustomInventoryName()) result.setString(NBTTags.NAME, name);
        result.setTag(NBTTags.INVENTORY, InventoryUtils.inventoryToNBTList(inventory));
        return result;
    }

    public BasicInventory readFromNBT(NBTTagCompound tagCompound)
    {
        int size = tagCompound.getInteger(NBTTags.CAPACITY);
        if (tagCompound.hasKey(NBTTags.NAME)) this.name = tagCompound.getString(NBTTags.NAME);
        ItemStack[] result = new ItemStack[size];
        inventory = InventoryUtils.nbtListToInventory(tagCompound, result);
        return this;
    }
}
