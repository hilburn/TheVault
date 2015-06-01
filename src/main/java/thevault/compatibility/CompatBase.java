package thevault.compatibility;

import thevault.utils.LogHelper;

public abstract class CompatBase
{
    private ModCompat mod;

    public boolean load(ModCompat mod)
    {
        this.mod = mod;
        if (mod.isLoaded())
        {
            LogHelper.instance().info("Loading compatibility for " + mod.getModName());
            init();
            return true;
        } else
        {
            LogHelper.instance().info(mod.getModName() + " not loaded - skipping");
        }
        return false;
    }

    protected abstract void init();
}
