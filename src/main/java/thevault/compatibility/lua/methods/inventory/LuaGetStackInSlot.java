package thevault.compatibility.lua.methods.inventory;

import thevault.compatibility.lua.conversion.ConversionRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class LuaGetStackInSlot extends LuaInventoryMethod
{
    public LuaGetStackInSlot()
    {
        super("getStackInSlot", "(int slot)", Number.class);
    }

    @Override
    public Object[] action(TileEntity te, Object[] args) throws Exception
    {
        ItemStack stack = ((IInventory)te).getStackInSlot(((Number)args[0]).intValue());
        if (stack != null)
        {
            return new Object[]{ConversionRegistry.getInstance().toLua(stack)};
        }
        return new Object[0];
    }
}
