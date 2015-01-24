package hilburnlib.compatibility.lua.conversion;

import net.minecraftforge.common.util.ForgeDirection;

public class ForgeDirectionConversion implements ILuaConversion
{
    @Override
    public Object toLua(Object o)
    {
        if (o instanceof ForgeDirection)
            return (((ForgeDirection)o).name());
        return null;
    }

    @Override
    public <T> T fromLua(Object o)
    {
        if (o instanceof String)
        {
            String string = (String)o;
            if (string.equalsIgnoreCase("unknown")) return (T)ForgeDirection.UNKNOWN;
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            {
                if (string.equalsIgnoreCase(direction.name())) return (T)direction;
            }
        }
        return null;
    }
}
