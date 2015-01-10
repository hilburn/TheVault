package hilburnlib.compatibility.lua.events.triggered.peripheral;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import hilburnlib.base.tiles.TilePeripheralBase;
import hilburnlib.compatibility.lua.events.triggered.TriggerEvent;

public abstract class PeripheralTriggerEvent<T extends Event & IPeripheralTriggerEvent> extends TriggerEvent<T>
{
    private final TilePeripheralBase peripheral;

    public PeripheralTriggerEvent(String name, EventBus bus, TilePeripheralBase peripheral)
    {
        super(name, bus);
        this.peripheral = peripheral;
    }

    @Override
    public boolean applies(T event)
    {
        return event.getTileEntity() == peripheral;
    }

    @Override
    public void announce(T event)
    {
        this.announce(peripheral, constructMessage(event));
    }
}
