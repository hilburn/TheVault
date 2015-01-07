package hilburnlib.entity;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerHelper
{
    public static boolean isCreative(EntityPlayer player)
    {
        return player.capabilities.isCreativeMode;
    }
}
