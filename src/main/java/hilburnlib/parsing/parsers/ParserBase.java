package hilburnlib.parsing.parsers;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ParserBase<T>
{
    public static final String VAL = "V";
    public static final String TYPE = "T";
    protected byte key;
    protected Class<T> type;

    protected ParserBase(int key, Class<T> type)
    {
        this.key = (byte)key;
        this.type = type;
    }
    
    public Class<T> getType()
    {
        return this.type;
    }

    //################Lua########################

    public abstract Object toLua(T t);

    public abstract T fromLua(Object o);

    //################NBT########################

    public abstract NBTBase toNBT(T t);

    public abstract T fromNBT(NBTTagCompound tag);
}
