package hilburnlib.compatibility.lua.events.checked;

import hilburnlib.base.tiles.TilePeripheralBase;
import hilburnlib.compatibility.lua.events.LuaEvent;

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
