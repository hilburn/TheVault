package hilburnlib.position;

import net.minecraftforge.common.util.ForgeDirection;

public class RotationUtil
{
    public static ForgeDirection rotateClockwise(ForgeDirection direction)
    {
        return ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[0][direction.ordinal()]);
    }

    public static ForgeDirection rotateAntiClockwise(ForgeDirection direction)
    {
        return ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[1][direction.ordinal()]);
    }
}
