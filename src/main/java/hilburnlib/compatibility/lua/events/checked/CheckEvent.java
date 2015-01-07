package hilburnlib.compatibility.lua.events.checked;

import hilburnlib.base.tiles.TileEntityPeripheralBase;
import hilburnlib.compatibility.lua.events.LuaEvent;

public abstract class CheckEvent extends LuaEvent
{
    public CheckEvent(String name)
    {
        super(name);
    }

    public abstract boolean triggerEvent(TileEntityPeripheralBase te);

    public boolean checkEvent(TileEntityPeripheralBase te)
    {
        return true;
    }
}
