package hilburnlib.java.math.vector;

import com.google.common.io.ByteArrayDataInput;
import hilburnlib.java.math.vector.interfaces.IVector2;
import net.minecraft.nbt.NBTTagCompound;

public class Vector2 implements Cloneable, IVector2
{
    public double x;
    public double z;

    public Vector2()
    {
        this(0, 0);
    }

    public Vector2(double x, double z)
    {
        this.x = x;
        this.z = z;
    }

    public Vector2(IVector2 vec)
    {
        this.x = vec.x();
        this.z = vec.z();
    }

    public Vector2(ByteArrayDataInput data)
    {
        this(data.readDouble(), data.readDouble());
    }

    public Vector2(NBTTagCompound nbt)
    {
        this(nbt.getDouble("x"), nbt.getDouble("z"));
    }

    public static double distance(Vector2 point1, Vector2 point2)
    {
        return point1.clone().distance(point2);
    }

    public static double slope(Vector2 point1, Vector2 point2)
    {
        double xDifference = point1.x - point2.x;
        double yDiference = point1.z - point2.z;
        return yDiference / xDifference;
    }

    @Override
    public double x()
    {
        return this.x;
    }

    @Override
    public double z()
    {
        return this.z();
    }

    public int intX()
    {
        return (int)Math.floor(this.x);
    }

    public int intY()
    {
        return (int)Math.floor(this.z);
    }

    /**
     * Makes a new copy of this Vector. Prevents variable referencing problems.
     */
    @Override
    public Vector2 clone()
    {
        return new Vector2(this.x, this.z);
    }

    /**
     * ---------------------- MAGNITUDE FUNCTIONS ----------------------------
     */
    public double getMagnitude()
    {
        return Math.sqrt(this.getMagnitudeSquared());
    }

    public double getMagnitudeSquared()
    {
        return this.x * this.x + this.z * this.z;
    }

    public Vector2 normalize()
    {
        double d = this.getMagnitude();

        if (d != 0)
        {
            this.scale(1D / d);
        }

        return this;
    }

    public Vector2 midPoint(Vector2 pos)
    {
        return new Vector2((x + pos.x) / 2, (z + pos.z) / 2);
    }

    public double distance(Vector2 target)
    {
        Vector2 difference = this.clone().subtract(target);
        return difference.getMagnitude();
    }

    public Vector2 add(Vector2 par1)
    {
        this.x += par1.x;
        this.z += par1.z;
        return this;
    }

    public Vector2 add(double par1)
    {
        this.x += par1;
        this.z += par1;
        return this;
    }

    public Vector2 subtract(Vector2 par1)
    {
        this.x -= par1.x;
        this.z -= par1.z;
        return this;
    }

    public Vector2 invert()
    {
        this.scale(-1);
        return this;
    }

    public Vector2 scale(double amount)
    {
        this.x *= amount;
        this.z *= amount;
        return this;
    }

    public Vector2 round()
    {
        return new Vector2(Math.round(this.x), Math.round(this.z));
    }

    public Vector2 ceil()
    {
        return new Vector2(Math.ceil(this.x), Math.ceil(this.z));
    }

    public Vector2 floor()
    {
        return new Vector2(Math.floor(this.x), Math.floor(this.z));
    }

    @Override
    public int hashCode()
    {
        long x = Double.doubleToLongBits(this.x);
        long y = Double.doubleToLongBits(this.z);
        return 31 * (int)(x ^ (x >>> 32)) + (int)(y ^ (y >>> 32));
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Vector2)
        {
            Vector2 vector = (Vector2)o;
            return this.x == vector.x && this.z == vector.z;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "Vector2 [" + this.x + "," + this.z + "]";
    }
}