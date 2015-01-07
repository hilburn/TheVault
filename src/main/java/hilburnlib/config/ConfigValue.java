package hilburnlib.config;

import hilburnlib.utils.Converter;
import hilburnlib.utils.LogHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ConfigValue
{
    public final String name;
    public final String category;
    public final String comment;
    public final Property.Type type;
    public final boolean needsRestart;
    protected final Field field;
    private final Object defaultValue;
    private final String[] defaultText;

    protected final Converter<?> converter;
    protected final Property property;
    private static final Map<Class<?>, Property.Type> CONFIG_TYPES = new LinkedHashMap<>();

    static
    {
        CONFIG_TYPES.put(int.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(Boolean.class, Property.Type.BOOLEAN);
        CONFIG_TYPES.put(boolean.class, Property.Type.BOOLEAN);
        CONFIG_TYPES.put(Byte.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(byte.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(Double.class, Property.Type.DOUBLE);
        CONFIG_TYPES.put(double.class, Property.Type.DOUBLE);
        CONFIG_TYPES.put(Float.class, Property.Type.DOUBLE);
        CONFIG_TYPES.put(float.class, Property.Type.DOUBLE);
        CONFIG_TYPES.put(Long.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(long.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(Short.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(short.class, Property.Type.INTEGER);
        CONFIG_TYPES.put(String.class, Property.Type.STRING);
    }

    protected ConfigValue(String modId, Configuration config, Field field, Config annotation)
    {
        this.comment = annotation.comment();
        this.category = annotation.category();
        this.needsRestart = annotation.needsRestart();
        String name = annotation.name();

        if (name.equals("")) name = field.getName();

        this.name = name;
        this.field = field;


        this.defaultValue = getFieldValue();
        if (this.defaultValue == null) throw new NullPointerException("Config field " + " has no default value");
        this.defaultText = convertToStringArray(this.defaultValue);

        final Class<?> fieldType = getFieldType();
        this.type = ConfigValue.CONFIG_TYPES.get(fieldType);
        if (this.type == null) throw new NullPointerException("Config field " + name + " has no property type mapping");

        this.converter = Converter.getConverterFor(fieldType);
        if (this.converter == null)
            throw new NullPointerException("Config field " + name + " has no known conversion from string");

        this.property = getProperty(config, type, defaultValue);
        if (this.needsRestart) this.property.requiresMcRestart();
        this.property.setLanguageKey(modId + "." + this.name);
    }

    protected void updateValueFromConfig(boolean force)
    {
        // return on newly created value. Due to forge bug list properties
        if (!force && !property.wasRead() && !property.isList()) return;

        final Property.Type actualType = property.getType();

        if (type != actualType)
            throw new IllegalStateException("Invalid config value type '" + actualType + "', expected '" + type + "'");

        String[] currentValue = getPropertyValue();
        try
        {
            Object converted = convertValue(currentValue);
            setFieldValue(converted);
        } catch (IllegalArgumentException | IllegalAccessException e)
        {
            LogHelper.warn("Invalid config property value " + Arrays.toString(currentValue) + ", using default value");
        }
    }

    protected void setFieldValue(Object value) throws IllegalArgumentException, IllegalAccessException
    {
        field.set(null, value);
    }

    protected Object getFieldValue()
    {
        try
        {
            return field.get(null);
        } catch (IllegalArgumentException | IllegalAccessException e)
        {
            return null;
        }
    }

    protected abstract Class<?> getFieldType();

    protected abstract Property getProperty(Configuration configFile, Property.Type expectedType, Object defaultValue);

    public abstract String[] getPropertyValue();

    protected abstract void setPropertyValue(String... values);

    protected abstract Object convertValue(String... values);

    public abstract boolean acceptsMultipleValues();

    public abstract String valueDescription();

    protected abstract String[] convertToStringArray(Object value);

    public String[] getDefaultValues()
    {
        return defaultText.clone();
    }

    private static class SingleValue extends ConfigValue
    {

        protected SingleValue(String modId, Configuration config, Field field, Config annotation)
        {
            super(modId, config, field, annotation);
        }

        @Override
        protected Class<?> getFieldType()
        {
            return field.getType();
        }

        @Override
        protected Property getProperty(Configuration configFile, Property.Type expectedType, Object defaultValue)
        {
            final String defaultString = defaultValue.toString();
            return configFile.get(category, name, defaultString, comment, expectedType);
        }

        @Override
        protected Object convertValue(String... values)
        {
            if (values.length != 1) throw new IllegalArgumentException("This parameter has only one value");
            final String value = values[0];
            return converter.readFromString(value);
        }

        @Override
        public String[] getPropertyValue()
        {
            return new String[]{property.getString()};
        }

        @Override
        protected void setPropertyValue(String... values)
        {
            if (values.length != 1) throw new IllegalArgumentException("This parameter has only one value");
            property.set(values[0]);
        }

        @Override
        public boolean acceptsMultipleValues()
        {
            return false;
        }

        @Override
        public String valueDescription()
        {
            return property.getString();
        }

        @Override
        protected String[] convertToStringArray(Object value)
        {
            return new String[]{value.toString()};
        }
    }

    private static class MultipleValues extends ConfigValue
    {

        protected MultipleValues(String modId, Configuration config, Field field, Config annotation)
        {
            super(modId, config, field, annotation);
        }

        @Override
        protected Class<?> getFieldType()
        {
            return field.getType().getComponentType();
        }

        @Override
        protected Property getProperty(Configuration configFile, Property.Type expectedType, Object defaultValue)
        {
            final String[] defaultStrings = convertToStringArray(defaultValue);
            return configFile.get(category, name, defaultStrings, comment, expectedType);
        }

        @Override
        protected Object convertValue(String... values)
        {
            final Object result = Array.newInstance(field.getType().getComponentType(), values.length);
            for (int i = 0; i < values.length; i++)
            {
                final String value = values[i].replaceAll("(\\s)+|\"", "");
                final Object converted = converter.readFromString(value);
                Array.set(result, i, converted);
            }
            return result;
        }

        @Override
        public String[] getPropertyValue()
        {
            return property.getStringList();
        }

        @Override
        protected void setPropertyValue(String... values)
        {
            property.set(values);
        }

        @Override
        public boolean acceptsMultipleValues()
        {
            return true;
        }

        @Override
        public String valueDescription()
        {
            return Arrays.toString(property.getStringList());
        }

        @Override
        protected String[] convertToStringArray(Object value)
        {
            if (!value.getClass().isArray())
                throw new IllegalArgumentException("Type " + value.getClass() + " is not an array");
            int length = Array.getLength(value);
            String[] result = new String[length];
            for (int i = 0; i < length; i++)
                result[i] = String.format("\"%s\"", Array.get(value, i).toString());

            return result;
        }
    }

    public static ConfigValue createConfigValueForField(String modId, Configuration config, Field field)
    {
        Config annotation = field.getAnnotation(Config.class);
        if (annotation == null) return null;
        Class<?> fieldType = field.getType();
        return fieldType.isArray() ? new MultipleValues(modId, config, field, annotation) : new SingleValue(modId, config, field, annotation);
    }
}
