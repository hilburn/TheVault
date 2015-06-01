package thevault.parsing.lua;

import thevault.parsing.ILuaParsing;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class ArrayParsing implements thevault.parsing.IParsing
{

    @Override
    public Object toLua(Object o)
    {
        if (o.getClass().isArray()) {
            Map<Number, Object> ret = new HashMap<>();
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object value = Array.get(o, i);
                ret.put(i + 1, ParsingRegistry.getInstance().toLua(value));
            }
            return ret;
        }
        return null;
    }

    @Override
    public <T> T fromLua(Object o)
    {
        return null;
    }
}
