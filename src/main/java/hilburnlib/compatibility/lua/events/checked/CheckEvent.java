package hilburnlib.compatibility.lua.events.checked;

import hilburnlib.compatibility.lua.events.LuaEvent;
import hilburnlib.tiles.TileEntityPeripheralBase;

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
