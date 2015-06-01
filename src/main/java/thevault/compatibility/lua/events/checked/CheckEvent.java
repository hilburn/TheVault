package thevault.compatibility.lua.events.checked;

import thevault.base.tiles.TilePeripheralBase;
import thevault.compatibility.lua.events.LuaEvent;

public abstract class CheckEvent extends LuaEvent
{
    public CheckEvent(String name)
    {
        super(name);
    }

    public abstract boolean triggerEvent(TilePeripheralBase te);

    public boolean checkEvent(TilePeripheralBase te)
    {
        return true;
    }
}
