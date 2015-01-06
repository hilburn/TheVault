package hilburnlib.registry;

import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Register
{
    public String name();
    public @Nullable String unlocalizedName();
    public @Nullable Class<? extends TileEntity> tileentity();
}
