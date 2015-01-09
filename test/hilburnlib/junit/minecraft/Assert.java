package hilburnlib.junit.minecraft;

import net.minecraft.item.ItemStack;

public class Assert extends org.junit.Assert
{
    public static void assertEquals(ItemStack expected, ItemStack actual)
    {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, ItemStack expected, ItemStack actual)
    {
        if (!(expected.getItem() == actual.getItem() && expected.getItemDamage() == actual.getItemDamage() & expected.stackSize == actual.stackSize))
            failNotEquals(message, expected, actual);
    }

    private static void failNotEquals(String message, Object expected, Object actual) {
        fail(format(message, expected, actual));
    }

    private static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if(message != null && !message.equals("")) {
            formatted = message + " ";
        }

        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        return expectedString.equals(actualString)?formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: " + formatClassAndValue(actual, actualString):formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
    }

    private static String formatClassAndValue(Object value, String valueString) {
        String className = value == null?"null":value.getClass().getName();
        return className + "<" + valueString + ">";
    }
}
