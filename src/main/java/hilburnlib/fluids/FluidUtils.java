package hilburnlib.fluids;

import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class FluidUtils
{
    public static ItemStack getEmptyContainer(ItemStack stack) {
        if(stack.getItem().hasContainerItem(stack)) {
            return stack.getItem().getContainerItem(stack);
        }
        else if(stack.getItem() instanceof ItemPotion && stack.stackTagCompound == null) {
            return new ItemStack(Items.glass_bottle);
        }
        else {
            return null;
        }
    }
}
