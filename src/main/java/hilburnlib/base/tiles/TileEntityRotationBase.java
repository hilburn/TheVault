package hilburnlib.base.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityRotationBase extends TileEntity
{
    private int front = 2;
    private int top = 1;

    /** Returns an int index that maps the face to be rendered to the rotated index
     * Assumes that index 1 is top and 2 is front {@code ForgeDirection.UP} and {@code ForgeDirection.NORTH} respectively
     * @param face  face to be rendered
     * @return  face icon to use
     */
    public int getRotatedIndex(int face)
    {
        //TODO: this
        return 0;
    }

    public ForgeDirection getFront()
    {
        return ForgeDirection.getOrientation(front);
    }

    public ForgeDirection getTop()
    {
        return ForgeDirection.getOrientation(top);
    }
}
