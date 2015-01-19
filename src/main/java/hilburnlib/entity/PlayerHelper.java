package hilburnlib.entity;

import cpw.mods.fml.common.Loader;
import hilburnlib.reference.Mods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.FakePlayer;
import thaumcraft.common.lib.FakeThaumcraftPlayer;

public class PlayerHelper
{
    public static final boolean thaumcraftLoaded = Loader.isModLoaded(Mods.THAUMCRAFT);
    public static boolean isCreative(EntityPlayer player)
    {
        return player.capabilities.isCreativeMode;
    }

    public static boolean isFakePlayer(EntityPlayer player)
    {
        return player instanceof FakePlayer || (thaumcraftLoaded && player instanceof FakeThaumcraftPlayer);
    }
}
