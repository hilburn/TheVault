package thevault.config;

import thevault.java.math.MathHelper;
import net.minecraftforge.common.config.Property;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class PropertyParser<T>
{
    protected static Map<Class<?>, PropertyParser> parserMap = new HashMap<>();
    protected static PropertyParser parseBool = new PropertyParser<Boolean>()
    {
        @Override
        Boolean convert(Property property, String value)
        {
            return Boolean.getBoolean(value);
        }

        @Override
        Boolean validate(Property property, Boolean value)
        {
            return null;
        }
    };
    protected static PropertyParser parseInt = new PropertyParser<Integer>()
    {

        @Override
        Integer convert(Property property, String value)
        {
            return validate(property, Integer.valueOf(value));
        }

        @Override
        Integer validate(Property property, Integer value)
        {
            Double max = Double.valueOf(property.getMaxValue());
            Double min = Double.valueOf(property.getMinValue());
            return (int)MathHelper.clamp(value, min, max);
        }
    };
    protected static PropertyParser parseDouble = new PropertyParser<Double>()
    {

        @Override
        Double convert(Property property, String value)
        {
            return validate(property, Double.valueOf(value));
        }

        @Override
        Double validate(Property property, Double value)
        {
            Double max = Double.valueOf(property.getMaxValue());
            Double min = Double.valueOf(property.getMinValue());
            return MathHelper.clamp(value, min, max);
        }
    };
    protected static PropertyParser parseString = new PropertyParser<String>()
    {

        @Override
        String convert(Property property, String value)
        {
            return value;
        }

        @Override
        String validate(Property property, String value)
        {
            return null;
        }
    };

    public T getValue(Property property, String value)
    {
        if (property.getValidationPattern() == null || property.getValidationPattern().matcher(value).find())
        {
            if (property.getValidValues().length == 0 || Arrays.asList(property.getValidValues()).contains(value))
            {
                return convert(property, value);
            }
        }
        return null;
    }

    abstract T convert(Property property, String value);

    abstract T validate(Property property, T value);

    static
    {
        parserMap.put(Boolean.class, parseBool);
        parserMap.put(boolean.class, parseBool);
        parserMap.put(Byte.class, parseInt);
        parserMap.put(byte.class, parseInt);
        parserMap.put(Short.class, parseInt);
        parserMap.put(short.class, parseInt);
        parserMap.put(Integer.class, parseInt);
        parserMap.put(int.class, parseInt);
        parserMap.put(Long.class, parseInt);
        parserMap.put(long.class, parseInt);
        parserMap.put(Float.class, parseDouble);
        parserMap.put(float.class, parseDouble);
        parserMap.put(Double.class, parseDouble);
        parserMap.put(double.class, parseDouble);
        parserMap.put(String.class, parseString);
    }

    public static PropertyParser getParser(Class<?> clazz)
    {
        return parserMap.get(clazz);
    }
}
