package hilburnlib.compatibility;

import cpw.mods.fml.common.Loader;

import java.util.List;


/**
 * Designed to make compatibility easier, reducing the number of Loader.isModLoaded({@code modId}) checks, and to bundle a readable name with the mod.
 */
public class ModCompat
{
    private final String modId;
    private final String modName;
    private final CompatBase compatClass;
    private final boolean loaded;

    /**
     * Because sometimes it's useful to keep your Vanilla integration all in one place
     */
    public ModCompat(CompatBase compatClass)
    {
        this("minecraft", "Minecraft", compatClass, true);
    }

    public ModCompat(String modId, CompatBase compatClass)
    {
        this(modId, modId, compatClass);
    }

    public ModCompat(String modId)
    {
        this(modId, modId, null, Loader.isModLoaded(modId));
    }

    public ModCompat(String modId, String modName)
    {
        this(modId, modName, null, Loader.isModLoaded(modId));
    }

    public ModCompat(String modId, String modName, CompatBase compatClass)
    {
        this(modId, modName, compatClass, Loader.isModLoaded(modId));
    }

    private ModCompat(String modId, String modName, CompatBase compatClass, boolean loaded)
    {
        this.modId = modId;
        this.modName = modName;
        this.compatClass = compatClass;
        this.loaded = loaded;
    }

    public String getModId()
    {
        return modId;
    }

    public String getModName()
    {
        return modName;
    }

    public boolean isLoaded()
    {
        return loaded;
    }

    private void load()
    {
        compatClass.load(this);
    }
}
