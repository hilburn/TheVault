package hilburnlib.java.reflection;

import hilburnlib.base.blocks.AbstractTileBlock;
import hilburnlib.java.util.ICopy;
import hilburnlib.position.BlockCoord;
import hilburnlib.recipes.IRecipeWrapper;
import net.minecraft.block.Block;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassScraperTest
{

    @Test
    public void testGetGeneralizations() throws Exception
    {
        assertTrue(ClassScraper.getGeneralizations(ClassScraper.class).contains(Object.class));
        assertFalse(ClassScraper.getGeneralizations(ClassScraper.class).contains(Integer.class));
        assertTrue(ClassScraper.getGeneralizations(AbstractTileBlock.class).contains(Block.class));
    }

    @Test
    public void testClassInstanceOf() throws Exception
    {
        assertTrue(ClassScraper.classInstanceOf(BlockCoord.class, ICopy.class));
        assertFalse(ClassScraper.classInstanceOf(BlockCoord.class, ICopy.class, IRecipeWrapper.class));
    }
}