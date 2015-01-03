package hilburnlib.reference;

public class Constants
{
    public static final class Ticks
    {
        public static final int SECOND = 20;
        public static final int MINUTE = SECOND * 60;
        public static final int HOUR = MINUTE * 60;
        public static final int DAY = HOUR * 24;
        public static final int WEEK = DAY * 7;
    }

    public static final class ColorModifier
    {
        public static final String PREFIX = "\u00A7";//§
        public static final String BLACK = PREFIX + "0";
        public static final String DARK_BLUE = PREFIX + "1";
        public static final String DARK_GREEN = PREFIX + "2";
        public static final String DARK_CYAN = PREFIX + "3";
        public static final String DARK_RED = PREFIX + "4";
        public static final String PURPLE = PREFIX + "5";
        public static final String ORANGE = PREFIX + "6";
        public static final String LIGHT_GRAY = PREFIX + "7";
        public static final String DRAK_GRAY = PREFIX + "8";
        public static final String LILAC = PREFIX + "9";
        public static final String LIGHT_GREEN = PREFIX + "a";
        public static final String LIGHT_CYAN = PREFIX + "b";
        public static final String LIGHT_RED = PREFIX + "c";
        public static final String PINK = PREFIX + "d";
        public static final String YELLOW = PREFIX + "e";
        public static final String WHITE = PREFIX + "f";
        public static final String OBFUSCATED = PREFIX + "k";
        public static final String BOLD = PREFIX + "l";
        public static final String STRIKE_THROUGH = PREFIX + "m";
        public static final String UNDERLINE = PREFIX + "n";
        public static final String ITALIC = PREFIX + "o";
        public static final String RESET = PREFIX + "r";
    }
    
    public static final class Color
    {
        public static final int BLACK = -16777216;
        public static final int BLUE = -16776961;
        public static final int CYAN = -16711681;
        public static final int DARK_GRAY = -12303292;
        public static final int GRAY = -7829368;
        public static final int GREEN = -16711936;
        public static final int LIGHT_GREY = -3355444;
        public static final int MAGENTA = -65281;
        public static final int RED = -65536;
        public static final int TRANSPARENT = 0;
        public static final int WHITE = -1;
        public static final int YELLOW = -256;
        
        public static int RGB(int r, int g, int b)
        {
            return RGBA(r, g, b, 255);
        }
        
        public static int RGBA(int r, int g, int b, int a)
        {
            return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | ((b & 255));
        }
    }
}
