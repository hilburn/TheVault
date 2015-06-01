package thevault.junit.test;

import thevault.config.Sync;
import org.junit.Test;

import static thevault.junit.minecraft.Assert.*;

public class SyncTest
{
    public static class TestClass
    {
      public static int iTest = 1;
      public static boolean bTest = true;
      public static char cTest = 's';
      public static long lTest = 10000000000000000L;
      public static String sTest = "Hello";
      public static byte[] byteTest = new byte[] {1, 2, 3};
    }
    
    @Test
    public void testSync()
    {
        byte[] array = Sync.getSyncMessage(TestClass.class);
        Sync.loadSyncMessage(array);
        assertEquals(TestClass.iTest, 1);
        assertEquals(TestClass.bTest, true);
        assertEquals(TestClass.cTest, 's');
        assertEquals(TestClass.lTest, 10000000000000000L);
        assertEquals(TestClass.sTest, "Hello");
        assertArrayEquals(TestClass.byteTest, new byte[] {1, 2, 3});
    }
}
