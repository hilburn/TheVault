package thevault.compatibility.jabba;

import thevault.compatibility.CompatBase;
import net.minecraft.tileentity.TileEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class JabbaCompat extends CompatBase
{
    private static ArrayList<Class> extensions;
    private static ArrayList<String> extensionsNames;
    private static HashMap<String, Class> map;

    static
    {
        try
        {
            Class dolly = Class.forName("mcp.mobius.betterbarrels.common.items.dolly.ItemBarrelMover");
            Field classExtensions = dolly.getDeclaredField("classExtensions");
            Field classExtensionsNames = dolly.getDeclaredField("classExtensionsNames");
            Field classMap = dolly.getDeclaredField("classMap");
            classExtensions.setAccessible(true);
            classExtensionsNames.setAccessible(true);
            classMap.setAccessible(true);
            extensions = (ArrayList<Class>) classExtensions.get(null);
            extensionsNames = (ArrayList<String>) classExtensionsNames.get(null);
            map = (HashMap<String, Class>) classMap.get(null);
        } catch (Exception e)
        {}
    }

    @Override
    protected void init()
    {
    }

    public void registerWithDolly(Class<? extends TileEntity> tileEntity)
    {
        extensions.add(tileEntity);
        extensionsNames.add(tileEntity.getSimpleName());
        map.put(tileEntity.getSimpleName(),tileEntity);
    }
}
