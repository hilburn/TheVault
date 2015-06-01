package thevault.parsing;

import thevault.parsing.parsers.ParserBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class ParsingManager
{
    private static final Map<Class, ParserBase> parsers = new HashMap<>();

    public static Object toLua(Object o)
    {
        ParserBase parser = parsers.get(o.getClass());
        if (parser != null)
        {
            Object result = parser.toLua(o);
            if (result!=null) return result;
        }
        return null;
    }

    public static <T> T fromLua(Class<T> type, Object o)
    {
        ParserBase parser = parsers.get(type);
        if (parser != null)
        {
            Object result = parser.fromLua(o);
            if (result!=null) return (T)result;
        }
        return null;
    }

    public static NBTBase toNBT(Object o)
    {
        ParserBase parser = parsers.get(o.getClass());
        if (parser != null)
        {
            NBTBase result = parser.toNBT(o);
            if (result!=null) return result;
        }
        return null;
    }

    public static <T> T fromNBT(Class<T> type, NBTTagCompound o)
    {
        ParserBase parser = parsers.get(type);
        if (parser != null)
        {
            Object result = parser.fromNBT(o);
            if (result!=null) return (T)result;
        }
        return null;
    }
}
