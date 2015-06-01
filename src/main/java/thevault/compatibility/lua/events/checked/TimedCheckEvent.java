package thevault.compatibility.lua.events.checked;

import thevault.base.tiles.TilePeripheralBase;
import thevault.utils.Timer;

public abstract class TimedCheckEvent extends CheckEvent
{
    private Timer timer;

    public TimedCheckEvent(String name, Timer timer)
    {
        super(name);
        this.timer = timer;
    }

    @Override
    public boolean checkEvent(TilePeripheralBase te)
    {
        return timer.update();
    }
}
