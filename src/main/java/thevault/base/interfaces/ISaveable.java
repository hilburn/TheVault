package thevault.base.interfaces;

import net.minecraft.nbt.NBTTagCompound;

public interface ISaveable<T>
{
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound);
}
