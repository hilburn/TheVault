package thevault.compatibility.lua.events;

import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.peripheral.IComputerAccess;
import thevault.base.tiles.TilePeripheralBase;
import thevault.compatibility.ModList;
import thevault.reference.Mods;
import li.cil.oc.api.machine.Context;
import net.minecraft.tileentity.TileEntity;

public abstract class LuaEvent
{

    private String name;

    public LuaEvent(String name)
    {
        this.name = name;
    }

    public void announce(TileEntity te, Object... message)
    {
        if (!(te instanceof TilePeripheralBase)) return;
        TilePeripheralBase cTE = (TilePeripheralBase)te;
        if (ModList.computercraft.isLoaded())
            computerCraftAnnounce(cTE, message);
        if (ModList.opencomputers.isLoaded())
            openComputersAnnounce(cTE, message);
    }

    @Optional.Method(modid = Mods.COMPUTERCRAFT)
    public void computerCraftAnnounce(TilePeripheralBase te, Object... message)
    {
        for (Object computer : te.getComputers())
        {
            ((IComputerAccess)computer).queueEvent(name, message);
        }
    }

    @Optional.Method(modid = Mods.OPENCOMPUTERS)
    public void openComputersAnnounce(TilePeripheralBase te, Object... message)
    {
        for (Object context : te.getContext())
        {
            ((Context)context).signal(name, message);
        }
    }
}
