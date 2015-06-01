package thevault.compatibility.computercraft;

import thevault.compatibility.CompatBase;

public class ComputerCraftCompat extends CompatBase
{

    @Override
    public void init()
    {
        PeripheralProvider.register();
    }
}
