package thevault;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import thevault.entity.EntityWitherSkeleton;
import thevault.entity.SkeletonEventHandler;
import thevault.reference.Metadata;
import thevault.reference.Reference;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION_FULL)
public class BaseMod
{
    @Mod.Instance(Reference.ID)
    public static BaseMod instance = new BaseMod();

    @Mod.Metadata(Reference.ID)
    public static ModMetadata metadata;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        metadata = Metadata.init(metadata);
        MinecraftForge.EVENT_BUS.register(new SkeletonEventHandler());
        int entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityWitherSkeleton.class, "witherSkeleton", entityID, 0x00003D, 0x751947);
        EntityRegistry.registerModEntity(EntityWitherSkeleton.class, "witherSkeleton", entityID, instance, 64, 3, true);
        return;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event)
    {
    }
}
