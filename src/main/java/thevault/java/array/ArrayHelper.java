package thevault.java.array;

import thevault.java.math.MathHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ArrayHelper
{
    /**
     * Converts an {@link java.lang.Object} to an array of the type
     * @param array the {@link java.lang.Object} representation of the array
     * @return an {@link java.lang.Object}[]
     */
    public static Object[] convertToArray(Object array) {
        Class ofArray = array.getClass().getComponentType();
        if (ofArray.isPrimitive())
        {
            List arrayList = new ArrayList();
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++)
            {
                arrayList.add(Array.get(array, i));
            }
            return arrayList.toArray();
        }
        else
        {
            return (Object[]) array;
        }
    }

    /**
     * Clear all null values from an array
     *
     * @param array the array
     * @param type  the {@link java.lang.Class} of the array
     * @param <T>   the type of the array as state above
     * @return a null less array
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public static <T> T[] removeNulls(T[] array, Class<T> type)
    {
        List<T> list = new LinkedList<T>();
        for (T value : array)
        {
            if (value != null)
            {
                list.add(value);
            }
        }
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    /**
     * Takes a sub array from element start to element end (end is excluded)
     *
     * @param array the base array
     * @param start start element (included)
     * @param end   end element (excluded)
     * @param <T>   type of the array
     * @return the sub array of type T
     */
    public static <T> T[] subArray(T[] array, int start, int end)
    {
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * Takes a sub array starting from element start with given size
     * It will not go out of bounds so sub array can be smaller than the given size
     *
     * @param array the base array
     * @param start the start element (included)
     * @param size  the size of the sub array to take
     * @param <T>   type of the array
     * @return a sub array of type T
     */
    public static <T> T[] subArrayOfSize(T[] array, int start, int size)
    {
        return subArray(array, start, MathHelper.clamp(start + size + 1, 0, array.length));
    }

    /**
     * Takes a sub array from a given element to the end of the array
     *
     * @param array the base array
     * @param from  start element (included)
     * @param <T>   type if the array
     * @return the sub array of given type
     */
    public static <T> T[] subArrayFrom(T[] array, int from)
    {
        return subArray(array, from, array.length);
    }

    /**
     * Takes a sub array from the start with given size
     * It will not go out of bounds so sub array can be smaller than the given size
     *
     * @param array the base array
     * @param size  size of the sub array to take
     * @param <T>   type of the array
     * @return sub array of given type
     */
    public static <T> T[] subArray(T[] array, int size)
    {
        return subArray(array, 0, size);
    }

    public static Integer[] toObjectArray(int[] array)
    {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Double[] toObjectArray(double[] array)
    {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Float[] toObjectArray(float[] array)
    {
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Byte[] toObjectArray(byte[] array)
    {
        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Short[] toObjectArray(short[] array)
    {
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Long[] toObjectArray(long[] array)
    {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Boolean[] toObjectArray(boolean[] array)
    {
        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static Character[] toObjectArray(char[] array)
    {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[i];
        return result;
    }

    public static int[] toPrimitiveArray(Integer[] array)
    {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? 0 : array[i]);
        return result;
    }

    public static double[] toPrimitiveArray(Double[] array)
    {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? 0 : array[i]);
        return result;
    }

    public static float[] toPrimitiveArray(Float[] array)
    {
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? 0 : array[i]);
        return result;
    }

    public static byte[] toPrimitiveArray(Byte[] array)
    {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? 0 : array[i]);
        return result;
    }

    public static short[] toPrimitiveArray(Short[] array)
    {
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? 0 : array[i]);
        return result;
    }

    public static long[] toPrimitiveArray(Long[] array)
    {
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? 0 : array[i]);
        return result;
    }

    public static boolean[] toPrimitiveArray(Boolean[] array)
    {
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? false : array[i]);
        return result;
    }

    public static char[] toPrimitiveArray(Character[] array)
    {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = (array[i] == null ? ' ' : array[i]);
        return result;
    }

    public static int[] subArray(int[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static double[] subArray(double[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static float[] subArray(float[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static byte[] subArray(byte[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static short[] subArray(short[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static long[] subArray(long[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static boolean[] subArray(boolean[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static char[] subArray(char[] array, int start, int end)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), start, end));
    }

    public static int[] subArrayOfSize(int[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static double[] subArrayOfSize(double[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static float[] subArrayOfSize(float[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static byte[] subArrayOfSize(byte[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static short[] subArrayOfSize(short[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static long[] subArrayOfSize(long[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static boolean[] subArrayOfSize(boolean[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static char[] subArrayOfSize(char[] array, int start, int size)
    {
        return toPrimitiveArray(subArrayOfSize(toObjectArray(array), start, size));
    }

    public static int[] subArrayFrom(int[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static double[] subArrayFrom(double[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static float[] subArrayFrom(float[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static byte[] subArrayFrom(byte[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static short[] subArrayFrom(short[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static long[] subArrayFrom(long[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static boolean[] subArrayFrom(boolean[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static char[] subArrayFrom(char[] array, int start)
    {
        return toPrimitiveArray(subArrayFrom(toObjectArray(array), start));
    }

    public static int[] subArray(int[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static double[] subArray(double[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static float[] subArray(float[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static byte[] subArray(byte[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static short[] subArray(short[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static long[] subArray(long[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static boolean[] subArray(boolean[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }

    public static char[] subArray(char[] array, int size)
    {
        return toPrimitiveArray(subArray(toObjectArray(array), size));
    }
}
