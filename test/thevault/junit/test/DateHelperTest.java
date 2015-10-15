package thevault.junit.test;

import thevault.java.DateHelper;
import org.junit.Test;

import java.util.Calendar;

import static thevault.junit.minecraft.Assert.*;

public class DateHelperTest
{

    @Test
    public void testDates()
    {
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        assertTrue(new DateHelper.StaticDate(currentDay, currentMonth).isMatch());
        assertFalse(new DateHelper.StaticDate(currentDay-1, currentMonth).isMatch());
        assertFalse(new DateHelper.StaticDate(currentDay+1, currentMonth+1).isMatch());
        assertTrue(new DateHelper.RangeDate(currentDay-1, currentDay+3, currentMonth).isMatch());
        assertTrue(new DateHelper.RangeDate(new DateHelper.StaticDate(currentDay+4, currentMonth),40).isMatch());
        assertTrue(new DateHelper.RangeDate(currentDay-33, currentDay-26, currentMonth+1).isMatch());
    }
}