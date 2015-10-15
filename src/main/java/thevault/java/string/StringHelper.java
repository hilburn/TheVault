package thevault.java.string;

import thevault.java.array.ArrayHelper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper
{
    public static final DecimalFormat INTEGER_FORMAT = new DecimalFormat("#,###.##", DecimalFormatSymbols.getInstance(Locale.UK));
    public static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("#,###.00", DecimalFormatSymbols.getInstance(Locale.UK));
    public static final DecimalFormat PERCENT_INT_FORMAT = new DecimalFormat("#,###%", DecimalFormatSymbols.getInstance(Locale.UK));
    public static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#,###.#%", DecimalFormatSymbols.getInstance(Locale.UK));
    public static final DecimalFormat ENGINEERING_FORMAT = new DecimalFormat("##0.##E0", DecimalFormatSymbols.getInstance(Locale.UK));
    public static final Map<String,String> SCIENTIFIC_PREFIXES = new HashMap<>();
    public static final Pattern MANTISSA_PATTERN = Pattern.compile("(-?\\d{1,3}(?:\\.\\d+?)?)E(-?\\d+)");

    static
    {
        SCIENTIFIC_PREFIXES.put("24", "Y");
        SCIENTIFIC_PREFIXES.put("18", "E");
        SCIENTIFIC_PREFIXES.put("15", "P");
        SCIENTIFIC_PREFIXES.put("12", "T");
        SCIENTIFIC_PREFIXES.put("9", "G");
        SCIENTIFIC_PREFIXES.put("6", "M");
        SCIENTIFIC_PREFIXES.put("3", "k");
        SCIENTIFIC_PREFIXES.put("-3", "m");
        SCIENTIFIC_PREFIXES.put("-6", "µ");
        SCIENTIFIC_PREFIXES.put("-9", "n");
        SCIENTIFIC_PREFIXES.put("-12", "p");
        SCIENTIFIC_PREFIXES.put("-15", "f");
        SCIENTIFIC_PREFIXES.put("-18", "a");
        SCIENTIFIC_PREFIXES.put("-21", "z");
        SCIENTIFIC_PREFIXES.put("-24", "y");
    }

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
        Matcher matcher = MANTISSA_PATTERN.matcher(engineeringFormat(number));
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
        String result = SCIENTIFIC_PREFIXES.get(exponent);
        return result == null?"":result;
    }

    /**
     * Concat all objects with given separator
     *
     * @param separator eg. ", "
     * @param objects
     * @return one string
     */
    public static String toString(String separator, Object... objects)
    {
        StringBuilder result = new StringBuilder();
        String value;
        for (Object object : objects)
        {
            if (object != null && object.getClass().isArray())
            {
                StringBuilder intermediate = new StringBuilder();
                intermediate.append("[");
                for (Object o : ArrayHelper.convertToArray(object))
                {
                    intermediate.append(toString(separator, o)).append(separator);
                }
                value = intermediate.substring(0, intermediate.length() - separator.length()) + "]";
            }
            else
            {
                value = String.valueOf(object);
            }
            if (value.equals("%"))
            {
                value = "%%"; // fixes issue wit formatter
            }
            result.append(value).append(separator);
        }
        return result.substring(0, result.length() - separator.length());
    }
}
