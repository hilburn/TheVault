package thevault.parsing.lua;

import thevault.parsing.ILuaParsing;

import java.util.ArrayList;
import java.util.List;

public class ParsingRegistry implements thevault.parsing.IParsing
{
    private static ParsingRegistry INSTANCE = new ParsingRegistry();
    private static List<ILuaParsing> luaConversion = new ArrayList<>();

    public static ParsingRegistry getInstance()
    {
        return INSTANCE;
    }

    static
    {
        luaConversion.add(new ItemStackParsing());
        luaConversion.add(new ArrayParsing());
    }

    @Override
    public Object toLua(Object o)
    {
        for (ILuaParsing conversion : luaConversion)
        {
            Object result = conversion.toLua(o);
            if (result!=null) return result;
        }
        return null;
    }

    @Override
    public <T> T fromLua(Object o)
    {
        for (ILuaParsing conversion : luaConversion)
        {
            T result = conversion.fromLua(o);
            if (result!=null) return result;
        }
        return null;
    }
}
