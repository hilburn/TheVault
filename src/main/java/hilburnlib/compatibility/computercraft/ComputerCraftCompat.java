package hilburnlib.compatibility.computercraft;

import minechemV6Base.compatibility.CompatBase;

public class ComputerCraftCompat extends CompatBase {

    @Override
    public void init()
    {
        PeripheralProvider.register();
    }
}
