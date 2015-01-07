package hilburnlib.java;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A utility class to check for holidays and dates.
 */
public class DateHelper
{
    private static final Calendar CALENDAR = Calendar.getInstance();
    static
    {
        CALENDAR.setLenient(false); //Necessary or Feb 29th will return true for March 1st
    }

    public static final DateWatcher CHRISTMAS = new StaticDate(25,Calendar.DECEMBER);
    public static final DateWatcher CHRISTMAS_EVE = new StaticDate(24,Calendar.DECEMBER);
    public static final DateWatcher BOXING_DAY = new StaticDate(26,Calendar.DECEMBER);
    public static final DateWatcher HALLOWEEN = new StaticDate(31,Calendar.OCTOBER);
    public static final DateWatcher VALENTINES = new StaticDate(14,Calendar.FEBRUARY);
    public static final DateWatcher LEAP_YEAR = new StaticDate(29,Calendar.FEBRUARY);
    public static final DateWatcher NEW_YEAR = new StaticDate(1,Calendar.JANUARY);
    public static final DateWatcher ST_PATRICK = new StaticDate(17,Calendar.MARCH);
    public static final DateWatcher THANKSGIVING = new CustomDate()
    {

        @Override
        void getDate()
        {
            int year = date.get(Calendar.YEAR);
            int month = Calendar.NOVEMBER;
            date = Calendar.getInstance();
            date.set(year, month, 1);
            int day = date.get(Calendar.DAY_OF_WEEK);
            day = 26 - day + (day>Calendar.THURSDAY?7:0);
            date.set(year,month,day);
        }
    };
    public static final DateWatcher THHANKSGIVING_CA = new CustomDate()
    {
        @Override
        void getDate()
        {
            int year = date.get(Calendar.YEAR);
            int month = Calendar.OCTOBER;
            date = Calendar.getInstance();
            date.set(year, month, 1);
            int day = date.get(Calendar.DAY_OF_WEEK);
            day = 8 - day + (day>Calendar.MONDAY?7:0);
            date.set(year,month,day);
        }
    };
    public static final DateWatcher EASTER_SUNDAY = new CustomDate()
    {
        @Override
        void getDate()
        {
            date = GregorianCalendar.getInstance();
            int year = date.get(Calendar.YEAR);
            int a = year % 19;
            int b = year / 100;
            int c = year % 100;
            int d = b / 4;
            int e = b % 4;
            int f = (b + 8) / 25;
            int g = (b - f + 1) / 3;
            int h = (19 * a + b - d - g + 15) % 30;
            int i = c / 4;
            int k = c % 4;
            int l = (32 + 2 * e + 2 * i - h - k) % 7;
            int m = (a + 11 * h + 22 * l) / 451;
            int n = (h + l - 7 * m + 114) / 31;
            int p = (h + l - 7 * m + 114) % 31;
            date.clear();
            date.set(year, n - 1, p + 1);
        }
    };
    public static final DateWatcher GOOD_FRIDAY = new OffsetDate((CustomDate)EASTER_SUNDAY,-2);

    public static abstract class DateWatcher
    {
        public abstract boolean isMatch();
    }

    public static class StaticDate extends DateWatcher
    {
        private final int day;
        private final int month;
        public StaticDate(int day, int month)
        {
            this.day=day;
            this.month=month;
        }

        @Override
        public boolean isMatch()
        {
            CALENDAR.getTime();
            return CALENDAR.get(Calendar.DAY_OF_MONTH) == this.day && CALENDAR.get(Calendar.MONTH) == month;
        }
    }

    public static class OffsetDate extends CustomDate
    {

        public OffsetDate(StaticDate date, int daysOffset)
        {
            this.date = Calendar.getInstance();
            this.date.set(this.date.get(Calendar.YEAR),date.month,date.day+daysOffset);
        }

        public OffsetDate(CustomDate date, int daysOffset)
        {
            this.date = date.date;
            this.date.set(Calendar.DAY_OF_MONTH,this.date.get(Calendar.DAY_OF_MONTH)+daysOffset);
        }

        @Override
        public boolean isMatch()
        {
            CALENDAR.getTime();
            return date.equals(CALENDAR);
        }

        @Override
        void getDate()
        {}
    }

    public static abstract class CustomDate extends DateWatcher
    {
        protected Calendar date;
        @Override
        public boolean isMatch()
        {
            if (date==null) getDate();
            return CALENDAR.equals(date);
        }

        abstract void getDate();
    }

    public static class RangeDate extends DateWatcher
    {
        private final Calendar start;
        private final Calendar end;

        public RangeDate(int start, int end, int month)
        {
            this.start = getCalendar(start,month);
            this.end = getCalendar(end,month);
        }

        public RangeDate(StaticDate staticDate, int leeway)
        {
            this(staticDate,leeway,leeway);
        }

        public RangeDate(StaticDate staticDate, int startLeeway, int endLeeway)
        {
            this.start = getCalendar(staticDate.day-startLeeway,staticDate.month);
            this.end = getCalendar(staticDate.day+endLeeway,staticDate.month);
        }

        @Override
        public boolean isMatch()
        {
            CALENDAR.getTime();
            return start.before(CALENDAR) && end.after(CALENDAR);
        }

        private Calendar getCalendar(int day, int month)
        {
            Calendar result = Calendar.getInstance();
            int year = result.get(Calendar.YEAR);
            result.clear();
            result.set(year,month,day);
            return result;
        }
    }
}
