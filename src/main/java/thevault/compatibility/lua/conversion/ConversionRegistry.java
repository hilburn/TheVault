package thevault.compatibility.lua.conversion;

import java.util.ArrayList;
import java.util.List;

public class ConversionRegistry implements ILuaConversion
{
    private static ConversionRegistry INSTANCE = new ConversionRegistry();
    private static List<ILuaConversion> luaConversion = new ArrayList<>();

    public static ConversionRegistry getInstance()
    {
        return INSTANCE;
    }

    static
    {
        luaConversion.add(new ItemStackConversion());
        luaConversion.add(new ArrayConversion());
    }

    @Override
    public Object toLua(Object o)
    {
        for (ILuaConversion conversion : luaConversion)
        {
            Object result = conversion.toLua(o);
            if (result!=null) return result;
        }
        return null;
    }

    @Override
    public <T> T fromLua(Object o)
    {
        for (ILuaConversion conversion : luaConversion)
        {
            T result = conversion.fromLua(o);
            if (result!=null) return result;
        }
        return null;
    }
}
