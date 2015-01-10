package hilburnlib.position;

import net.minecraftforge.common.util.ForgeDirection;

public class DirectionUtil
{
    public ForgeDirection closest(float x, float y, float z)
    {
        float absX = Math.abs(x);
        float absY = Math.abs(y);
        float absZ = Math.abs(z);

        if (absX >= absY && absX >= absZ)
        {
            return x > 0 ? ForgeDirection.EAST : ForgeDirection.WEST;
        }
        if (absY >= absX && absY >= absZ)
        {
            return y > 0 ? ForgeDirection.UP : ForgeDirection.DOWN;
        }
        return z > 0 ? ForgeDirection.SOUTH : ForgeDirection.NORTH;
    }
}
