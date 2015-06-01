package thevault.junit.minecraft.world;

import net.minecraft.world.WorldProvider;

public class TestWorldProvider extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return TestWorld.NAME;
    }
}
