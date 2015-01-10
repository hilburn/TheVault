package hilburnlib.base.tiles;

import hilburnlib.base.interfaces.ITickableInventory;
import net.minecraft.item.ItemStack;

public abstract class TileTickingInventoryBase extends TileInventoryBase implements ITickableInventory
{
    protected int[] tickingSlots;

    protected TileTickingInventoryBase(int slots)
    {
        super(slots);
    }

    protected TileTickingInventoryBase(int slots, int[] tickingSlots)
    {
        super(slots);
        this.tickingSlots = tickingSlots;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        for (int slot : getTickingSlots())
        {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack!=null)
            {
                stack.getItem().onUpdate(stack,worldObj,null,0,false);
            }
        }
    }

    @Override
    public int[] getTickingSlots()
    {
        return tickingSlots;
    }

    public void setTickingSlots(int[] tickingSlots)
    {
        this.tickingSlots = tickingSlots;
    }
}
