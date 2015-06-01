package thevault.java.math.vector;

import thevault.java.math.vector.interfaces.IRotation;
import thevault.java.math.vector.interfaces.IVector3;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.util.ForgeDirection;

public class EulerAngle implements IRotation, IVector3
{
    public double yaw, pitch, roll;

    public EulerAngle()
    {
    }

    public EulerAngle(ForgeDirection dir)
    {
        switch (dir)
        {
            case DOWN:
                pitch = -90D;
                break;
            case UP:
                pitch = 90D;
                break;
            case NORTH:
                yaw = 0D;
                break;
            case SOUTH:
                yaw = 180D;
                break;
            case WEST:
                yaw = 90D;
                break;
            case EAST:
                yaw = 270D;
                break;
        }
    }

    public EulerAngle(double yaw, double pitch, double roll)
    {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public EulerAngle(double[] angles)
    {
        this(angles[0], angles[1], angles[2]);
    }

    public EulerAngle(EulerAngle angle)
    {
        this(angle.yaw(), angle.pitch(), angle.roll());
    }

    public EulerAngle(double yaw, double pitch)
    {
        this(yaw, pitch, 0);
    }

    public EulerAngle(Entity entity)
    {
        this(entity.rotationYaw, entity.rotationPitch);
    }

    public static double getAngleDifference(double angleA, double angleB)
    {
        return Math.abs(angleA - angleB);
    }

    public static double clampAngleTo360(double var)
    {
        return clampAngle(var, -360, 360);
    }

    public static double clampAngleTo180(double var)
    {
        return clampAngle(var, -180, 180);
    }

    public static double clampAngle(double var, double min, double max)
    {
        while (var < min)
            var += 360;

        while (var > max)
            var -= 360;

        return var;
    }

    @Override
    public double yaw()
    {
        return yaw;
    }

    @Override
    public double pitch()
    {
        return pitch;
    }

    @Override
    public double roll()
    {
        return roll;
    }

    public double yawRadians()
    {
        return Math.toRadians(yaw());
    }

    public double pitchRadians()
    {
        return Math.toRadians(pitch());
    }

    public double rollRadians()
    {
        return Math.toRadians(roll());
    }

    @Override
    public double x()
    {
        return -Math.sin(yawRadians()) * Math.cos(pitchRadians());
    }

    @Override
    public double y()
    {
        return Math.sin(pitchRadians());
    }

    @Override
    public double z()
    {
        return -Math.cos(yawRadians()) * Math.cos(pitchRadians());
    }

    public double[] toArray()
    {
        return new double[]{yaw(), pitch(), roll()};
    }

    public double[] toRadianArray()
    {
        return new double[]{yawRadians(), pitchRadians(), rollRadians()};
    }

    public EulerAngle set(int i, double value)
    {
        switch (i)
        {
            case 0:
                yaw = value;
                break;
            case 1:
                pitch = value;
                break;
            case 2:
                roll = value;
                break;
        }
        return this;
    }

    /**
     * gets the difference in degrees between the two angles
     */
    public EulerAngle difference(EulerAngle other)
    {
        return new EulerAngle(yaw() - other.yaw(), pitch() - other.pitch(), roll() - other.roll());
    }

    public EulerAngle absoluteDifference(EulerAngle other)
    {
        return new EulerAngle(getAngleDifference(yaw(), other.yaw()), getAngleDifference(pitch(), other.pitch()), getAngleDifference(roll(), other.roll()));
    }

    public boolean isWithin(EulerAngle other, double margin)
    {
        for (int i = 0; i < 3; i++)
            if (absoluteDifference(other).toArray()[i] > margin)
                return false;

        return true;
    }

    @Override
    public EulerAngle clone()
    {
        return new EulerAngle(this.yaw(), this.pitch(), this.roll());
    }

    @Override
    public int hashCode()
    {
        long x = Double.doubleToLongBits(yaw());
        long y = Double.doubleToLongBits(pitch());
        long z = Double.doubleToLongBits(roll());
        int hash = (int)(x ^ (x >>> 32));
        hash = 31 * hash + (int)(y ^ (y >>> 32));
        hash = 31 * hash + (int)(z ^ (z >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof EulerAngle)
        {
            EulerAngle angle = (EulerAngle)o;
            return yaw() == angle.yaw() && pitch() == angle.pitch() && roll() == angle.roll();
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "Angle [" + this.yaw() + "," + this.pitch() + "," + this.roll() + "]";
    }
}
