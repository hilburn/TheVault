package hilburnlib.java.string;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper
{

    public final static DecimalFormat INTEGER_FORMAT = new DecimalFormat("#,###.##");
    public final static DecimalFormat DOUBLE_FORMAT = new DecimalFormat("#,###.00");
    public final static DecimalFormat PERCENT_INT_FORMAT = new DecimalFormat("#,###%");
    public final static DecimalFormat PERCENT_FORMAT = new DecimalFormat("#,###.#%");
    public static final DecimalFormat ENGINEERING_FORMAT = new DecimalFormat("##0.##E0");
    public static final String SCIENTIFIC_PREFIXES = "24Y21Z18E15P12T9G6M3k-3m-6µ-9n-12p-15f-18a-21z-24y";
    public static final Pattern MANTISSA_PATTERN = Pattern.compile("(-?\\d{1,3}(?:\\.\\d+?)?)E(-?\\d+)");

    /**
     * @param sentence the string to search in
     * @param word     the string to search for
     * @return returns true if and only if {@code word} is a distinct word within {@code sentence}, as opposed to just a sub-string
     */
    public static boolean containsWord(String sentence, String word)
    {
        return containsWord(sentence, word, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Case sensitive word search
     *
     * @param sentence the string to search in
     * @param word     the string to search for
     * @return returns true if and only if {@code word} is a distinct word within {@code sentence}, as opposed to just a generic sub-string
     */
    public static boolean containsWordStrict(String sentence, String word)
    {
        return containsWord(sentence, word, 0);
    }

    public static boolean containsWord(String sentence, String word, int modifiers)
    {
        return Pattern.compile("(?:^|\\s)" + word + "(?:$|\\s)", modifiers).matcher(sentence).find();
    }

    public static String engineeringFormat(Number number)
    {
        return ENGINEERING_FORMAT.format(number);
    }

    public static String getEngineeringUnits(Number number, String unit)
    {
        Matcher matcher = MANTISSA_PATTERN.matcher(ENGINEERING_FORMAT.format(number));
        if (matcher.find())
        {
            return matcher.group(1) + getPrefix(matcher.group(2)) + unit;
        }
        throw new IndexOutOfBoundsException();
    }

    public static String getPrefix(int exponent)
    {
        return getPrefix(String.valueOf(exponent));
    }

    public static String getPrefix(String exponent)
    {
        Matcher matcher = Pattern.compile(exponent + "([^\\d])").matcher(SCIENTIFIC_PREFIXES);
        if (matcher.find())
        {
            return matcher.group(1);
        }
        return "";
    }
}
