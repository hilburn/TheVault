package hilburnlib.parsing;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Property;

public interface IParsing
{
    //################Lua########################

    Object toLua(Object o);

    <T> T fromLua(Object o);

    //################NBT########################

    NBTBase toNBT(Object o);

    <T> T fromNBT(NBTTagCompound o);
}
