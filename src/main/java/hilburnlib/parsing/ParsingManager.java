package hilburnlib.parsing;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Property;

import java.util.ArrayList;
import java.util.List;

public class ParsingManager
{
    private static final List<IParsing> parsers = new ArrayList<>();

    public static Object toLua(Object o)
    {
        for (IParsing parser:parsers)
        {
            Object result = parser.toLua(o);
            if (result!=null) return result;
        }
        return null;
    }

    public static <T> T fromLua(Object o)
    {
        for (IParsing parser:parsers)
        {
            T result = parser.fromLua(o);
            if (result!=null) return result;
        }
        return null;
    }

    public static NBTBase toNBT(Object o)
    {
        for (IParsing parser:parsers)
        {
            NBTBase result = parser.toNBT(o);
            if (result!=null) return result;
        }
        return null;
    }

    public static <T> T fromNBT(NBTTagCompound o)
    {
        for (IParsing parser:parsers)
        {
            T result = parser.fromNBT(o);
            if (result!=null) return result;
        }
        return null;
    }
}
