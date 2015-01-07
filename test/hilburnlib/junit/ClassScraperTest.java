package hilburnlib.junit;

import hilburnlib.reflection.ClassScraper;
import org.junit.Assert;
import org.junit.Test;

public class ClassScraperTest
{
    @Test
    public void classInstanceOfTest()
    {
        Assert.assertTrue(ClassScraper.classInstanceOf(Double.class, Object.class));
        Assert.assertFalse(ClassScraper.classInstanceOf(Object.class, Double.class));
        Assert.assertTrue(ClassScraper.classInstanceOf(Double.class, Object.class, Number.class, Comparable.class));
        Assert.assertFalse(ClassScraper.classInstanceOf(Double.class, Object.class, String.class, Comparable.class));
    }
}
