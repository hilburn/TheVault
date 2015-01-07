package hilburnlib.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Converter<T>
{
    private static final Map<Class<?>, Converter<?>> map = new LinkedHashMap<>();
    
    public static Converter<?> getConverterFor(Class<?> clazz)
    {
        return map.get(clazz);
    }

    public abstract T readFromString(String s);

    public static final Converter<Integer> INT_CONVERTER = new Converter<Integer>()
    {
        @Override
        public Integer readFromString(String s)
        {
            return Integer.parseInt(s);
        }
    };

    public static final Converter<Boolean> BOOL_CONVERTER = new Converter<Boolean>()
    {
        @Override
        public Boolean readFromString(String s)
        {
            return Boolean.parseBoolean(s);
        }
    };

    public static final Converter<Byte> BYTE_CONVERTER = new Converter<Byte>()
    {
        @Override
        public Byte readFromString(String s)
        {
            return Byte.parseByte(s);
        }
    };

    public static final Converter<Double> DOUBLE_CONVERTER = new Converter<Double>()
    {
        @Override
        public Double readFromString(String s)
        {
            return Double.parseDouble(s);
        }
    };

    public static final Converter<Float> FLOAT_CONVERTER = new Converter<Float>()
    {
        @Override
        public Float readFromString(String s)
        {
            return Float.parseFloat(s);
        }
    };

    public static final Converter<Long> LONG_CONVERTER = new Converter<Long>()
    {
        @Override
        public Long readFromString(String s)
        {
            return Long.parseLong(s);
        }
    };

    public static final Converter<Short> SHORT_CONVERTER = new Converter<Short>()
    {
        @Override
        public Short readFromString(String s)
        {
            return Short.parseShort(s);
        }
    };

    public static final Converter<String> STRING_CONVERTER = new Converter<String>()
    {
        @Override
        public String readFromString(String s)
        {
            return s;
        }
    };

    static
    {
        map.put(Integer.class, INT_CONVERTER);
        map.put(int.class, INT_CONVERTER);
        map.put(Boolean.class, BOOL_CONVERTER);
        map.put(boolean.class, BOOL_CONVERTER);
        map.put(Byte.class, BYTE_CONVERTER);
        map.put(byte.class, BYTE_CONVERTER);
        map.put(Double.class, DOUBLE_CONVERTER);
        map.put(double.class, DOUBLE_CONVERTER);
        map.put(Float.class, FLOAT_CONVERTER);
        map.put(float.class, FLOAT_CONVERTER);
        map.put(Long.class, LONG_CONVERTER);
        map.put(long.class, LONG_CONVERTER);
        map.put(Short.class, SHORT_CONVERTER);
        map.put(short.class, SHORT_CONVERTER);
        map.put(String.class, STRING_CONVERTER);
    }
}
