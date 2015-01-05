package hilburnlib.utils;

import net.minecraftforge.common.util.ForgeDirection;

public class BlockCoord
{
    private int x, y ,z;

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public BlockCoord offset(ForgeDirection direction)
    {
        x+=direction.offsetX;
        y+=direction.offsetY;
        z+=direction.offsetZ;
        return this;
    }

    public BlockCoord offset(ForgeDirection direction, int distance)
    {
        x+=direction.offsetX*distance;
        y+=direction.offsetY*distance;
        z+=direction.offsetZ*distance;
        return this;
    }

    public BlockCoord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof BlockCoord)
        {
            BlockCoord b = (BlockCoord)obj;
            return b.x == this.x && b.y == this.y && b.z == this.z;
        }
        return false;
    }
}
