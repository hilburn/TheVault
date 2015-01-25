package hilburnlib.parsing.parsers;

import hilburnlib.parsing.IParsing;
import hilburnlib.parsing.ParsingManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class ArrayParser extends ParserBase
{
    protected ArrayParser()
    {
        super(100);
    }

    @Override
    public Object toLua(Object o)
    {
        if (!o.getClass().isArray()) return null;
        Map<Number,Object> result = new HashMap<>();
        for (int i=0;i< Array.getLength(o); i++)
        {
            Object value = ParsingManager.toLua(Array.get(o, i));
            if (value!=null) result.put(i+1, value);
        }
        return result;
    }

    @Override
    public NBTBase toNBT(Object o)
    {
        return null;
    }

    @Override
    public <T>T fromNBT(NBTTagCompound o)
    {
        return null;
    }

    @Override
    public <T>T fromLua(Object o)
    {
        return null;
    }
}
