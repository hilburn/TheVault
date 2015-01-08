package hilburnlib.java.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper
{
    /**
     * @param sentence the string to search in
     * @param word     the string to search for
     * @return returns true if and only if {@code word} is a distinct word within {@code sentence}, as opposed to just a sub-string
     */
    public static boolean containsWord(String sentence, String word)
    {
        return Pattern.compile("(?:^|\\s)" + word + "(?:$:\\s)").matcher(sentence).find();
    }
}
