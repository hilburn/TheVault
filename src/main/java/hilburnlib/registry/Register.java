package hilburnlib.registry;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

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
    public @Nullable Class<? extends TileEntity> tileEntity();
    public @Nullable Class<? extends TileEntitySpecialRenderer> TESR();
    public @Nullable Class<IItemRenderer> IItemRenderer();
    public @Nullable Class<ISimpleBlockRenderingHandler> SBRH();
    public Class<? extends ItemBlock> itemBlock() default ItemBlock.class;
}
