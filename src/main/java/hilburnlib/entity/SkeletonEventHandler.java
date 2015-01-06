package hilburnlib.entity;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class SkeletonEventHandler
{
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void skeletonJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.entity.getClass() == EntitySkeleton.class && ((EntitySkeleton)event.entity).getSkeletonType()==1)
        {
            event.setCanceled(true);
            event.world.spawnEntityInWorld(new EntityWitherSkeleton((EntitySkeleton)event.entity));
        }
    }
}
