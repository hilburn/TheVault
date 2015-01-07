package hilburnlib.compatibility.lua.events.checked;

import hilburnlib.base.tiles.TileEntityPeripheralBase;
import hilburnlib.utils.Timer;

public abstract class TimedCheckEvent extends CheckEvent
{
    private Timer timer;

    public TimedCheckEvent(String name, Timer timer)
    {
        super(name);
        this.timer = timer;
    }

    @Override
    public boolean checkEvent(TileEntityPeripheralBase te)
    {
        return timer.update();
    }
}
