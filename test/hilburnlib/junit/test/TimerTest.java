package hilburnlib.junit.test;

import hilburnlib.utils.Timer;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimerTest
{
    @Test
    public void testTimer()
    {
        Timer timer = new Timer(10);
        for (int ii = 0; ii < 2; ii++)
        {
            for (int i = 0; i < 10; i++)
                assertFalse(timer.update());
            assertTrue(timer.update());
        }
    }

    @Test
    public void testTimerWithNBTWriteRead()
    {
        Timer timer = new Timer(10);
        for (int ii = 0; ii < 2; ii++)
        {
            for (int i = 0; i < 10; i++)
                assertFalse(timer.update());
            assertTrue(timer.update());
            timer = Timer.nbtToTimer(timer.writeToNBT());
        }
    }
}
