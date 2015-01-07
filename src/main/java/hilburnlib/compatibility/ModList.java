package hilburnlib.compatibility;

import hilburnlib.compatibility.computercraft.ComputerCraftCompat;
import hilburnlib.reference.Mods;

/**
 * Example Enum Implementation of ModCompat to automatically register compatibility modules
 */
public enum ModList
{
    computercraft(new ModCompat(Mods.COMPUTERCRAFT, "ComputerCraft", new ComputerCraftCompat())),
    opencomputers(new ModCompat(Mods.OPENCOMPUTERS, "OpenComputers"));

    private final ModCompat modCompat;

    ModList(ModCompat modCompat)
    {
        this.modCompat = modCompat;
    }

    public boolean isLoaded()
    {
        return modCompat.isLoaded();
    }
}
