package hilburnlib.utils;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldUtils
{
    /**
     * Draw a line of blocks
     *
     * @param x0 start point x
     * @param y0 start point y
     * @param z0 start point z
     * @param x1 end point x
     * @param y1 end point y
     * @param z1 end point z
     * @param world the world to put it in
     * @param block the block to draw with
     */
    public static void drawLine(int x0 ,int y0, int z0, int x1, int y1, int z1, World world, Block block)
    {
        drawWall(x0, y0, z0, x1, y1, z1, 1, world, block);
    }


    /**
     * Draws a wall of given height
     *
     * @param x0 start point x
     * @param y0 start point y
     * @param z0 start point z
     * @param x1 end point x
     * @param y1 end point y
     * @param z1 end point z
     * @param height wall height
     * @param world the world to put it in
     * @param block the block to draw it with
     */
    public static void drawWall(int x0, int y0, int z0, int x1, int y1, int z1, int height, World world, Block block)
    {
        int x, delta_x, step_x;
        int y, delta_y, step_y;
        int z, delta_z, step_z;
        boolean swap_xy, swap_xz;
        int drift_xy, drift_xz;
        int cx, cy, cz;
        int temp;

        //'steep' xy Line, make longest delta x plane
        swap_xy = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        if (swap_xy)
        {
            temp = x0;
            x0 = y0;
            y0 = temp;

            temp = x1;
            x1 = y1;
            y1 = temp;
        }

        //do same for xz
        swap_xz = Math.abs(z1 - z0) > Math.abs(x1 - x0);
        if (swap_xz)
        {
            temp = x0;
            x0 = z0;
            z0 = temp;

            temp = x1;
            x1 = z1;
            z1 = temp;
        }

        //delta is Length in each plane
        delta_x = Math.abs(x1 - x0);
        delta_y = Math.abs(y1 - y0);
        delta_z = Math.abs(z1 - z0);

        //drift controls when to step in 'shallow' planes
        //starting value keeps Line centred
        drift_xy = (delta_x / 2);
        drift_xz = (delta_x / 2);

        //direction of line
        step_x = x0 > x1 ? -1 : +1;
        step_y = y0 > y1 ? -1 : +1;
        step_z = z0 > z1 ? -1 : +1;

        //starting point
        y = y0;
        z = z0;

        //step through longest delta (which we have swapped to x)
        for (x = x0; x <= x1; x += step_x)
        {
            //copy position
            cx = x;
            cy = y;
            cz = z;

            //unswap (in reverse)
            if (swap_xz)
            {
                temp = cx;
                cx = cz;
                cz = temp;
            }
            if (swap_xy)
            {
                temp = cx;
                cx = cy;
                cy = temp;
            }

            //passes through this point
            for (int i = 0; i < height; i++)
            {
                world.setBlock(cx, cy + i, cz, block);
            }

            //update progress in other planes
            drift_xy = drift_xy - delta_y;
            drift_xz = drift_xz - delta_z;

            //step in y plane
            if (drift_xy < 0)
            {
                y = y + step_y;
                drift_xy = drift_xy + delta_x;
            }

            //same in z
            if (drift_xz < 0)
            {
                z = z + step_z;
                drift_xz = drift_xz + delta_x;
            }
        }
    }

    /**
     * Draws a circle of given radius
     *
     * @param x center X
     * @param y center Y
     * @param z center Z
     * @param r radius
     * @param world world to place in
     * @param block block to draw with
     */
    public static void drawCircle(int x, int y, int z, int r, World world, Block block)
    {
        drawCircleWall(x, y, z, r, 1, world, block);
    }

    /**
     * Draws a circle wall of given radius and height
     *
     * @param x center X
     * @param y center Y
     * @param z center Z
     * @param r radius
     * @param height height of the wall
     * @param world world to place in
     * @param block block to draw with
     */
    public static void drawCircleWall(int x, int y, int z, int r, int height, World world, Block block)
    {
        double step = 1D/r;
        for (double angle = 0; angle < 2*Math.PI; angle += step)
        {
            int cx = (int)Math.round(x + r * Math.cos(angle));
            int cz = (int)Math.round(z + r * Math.sin(angle));
            for (int h = 0; h < height; h++)
                world.setBlock(cx, y + h, cz, block);
        }
    }
}
