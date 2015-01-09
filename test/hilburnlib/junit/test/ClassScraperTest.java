package hilburnlib.junit.test;

import hilburnlib.java.reflection.ClassScraper;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassScraperTest
{
    @Test
    public void classInstanceOfTest()
    {
        assertTrue(ClassScraper.classInstanceOf(Double.class, Object.class));
        assertFalse(ClassScraper.classInstanceOf(Object.class, Double.class));
        assertTrue(ClassScraper.classInstanceOf(Double.class, Object.class, Number.class, Comparable.class));
        assertFalse(ClassScraper.classInstanceOf(Double.class, Object.class, String.class, Comparable.class));
    }
}
