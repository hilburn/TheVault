package thevault.parsing.parsers;

import thevault.parsing.ParsingManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class ArrayParser extends ParserBase<Object[]>
{
    protected ArrayParser()
    {
        super(100, Object[].class);
    }

    @Override
    public Object toLua(Object[] t)
    {
        if (!t.getClass().isArray()) return null;
        Map<Number,Object> result = new HashMap<>();
        for (int i=0;i< Array.getLength(t); i++)
        {
            Object value = ParsingManager.toLua(Array.get(t, i));
            if (value!=null) result.put(i+1, value);
        }
        return result;
    }

    @Override
    public NBTBase toNBT(Object[] o)
    {
        return null;
    }

    @Override
    public Object[] fromNBT(NBTTagCompound o)
    {
        return null;
    }

    @Override
    public Object[] fromLua(Object o)
    {
        return null;
    }
}
