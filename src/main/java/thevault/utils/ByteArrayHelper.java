package thevault.utils;

import java.io.*;

public class ByteArrayHelper
{
    public static byte[] toByteArray(Object o)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            return baos.toByteArray();
        } catch (IOException e)
        {
            LogHelper.warn("IOException during toByteArray");
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromByteArray(byte[] bytes)
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream oos = new ObjectInputStream(bais);
            return (T)oos.readObject();
        } catch (IOException e)
        {
            LogHelper.warn("IOException during fromBytesArray");
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            LogHelper.warn("ClassNotFoundException fromBytesArray");
            e.printStackTrace();
        }
        return null;
    }
}
