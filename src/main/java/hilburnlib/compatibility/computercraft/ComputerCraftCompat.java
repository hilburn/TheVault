package hilburnlib.compatibility.computercraft;

import hilburnlib.compatibility.CompatBase;

public class ComputerCraftCompat extends CompatBase
{

    @Override
    public void init()
    {
        PeripheralProvider.register();
    }
}
