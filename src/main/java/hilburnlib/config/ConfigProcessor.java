package hilburnlib.config;

import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class ConfigProcessor
{
    public static class ModConfiguration
    {
        private final Configuration config;
        public final Class<?> configClass;
        public final String modId;

        private Map<String, Map<String, ConfigValue>> configValues = new TreeMap<String, Map<String, ConfigValue>>();

        private ModConfiguration(String modId, Configuration config, Class<?> configClass)
        {
            this.modId = modId;
            this.config = config;
            this.configClass = configClass;
        }

        private void processField(Field field)
        {
            ConfigValue configValue = ConfigValue.createConfigValueForField(modId, config, field);
            if (configValue != null)
            {
                configValue.updateValueFromConfig(false);
                Map<String, ConfigValue> category = configValues.get(configValue.category);
                if (category == null) category = new LinkedHashMap<String, ConfigValue>();
                category.put(configValue.name, configValue);
            }
        }

        @SubscribeEvent
        public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.modID.equalsIgnoreCase(modId))
            {
                for (Map<String, ConfigValue> category : configValues.values())
                    for (ConfigValue configValue : category.values())
                        configValue.updateValueFromConfig(true);
            }
        }

        public void save()
        {
            if (config.hasChanged()) config.save();
        }

        public Set<String> getCategories()
        {
            return configValues.keySet();
        }

        public Map<String, ConfigValue> getValues(String category)
        {
            Map<String, ConfigValue> map = configValues.get(category);
            return map == null ? new LinkedHashMap<String, ConfigValue>() : map;
        }

        public ConfigValue getValue(String category, String name)
        {
            return getValues(category).get("name");
        }
        
        public List<IConfigElement> getConfigElements()
        {
            List<IConfigElement> list = new LinkedList<IConfigElement>();
            for (String cat : getCategories())
                list.addAll(new ConfigElement(config.getCategory(cat)).getChildElements());
            return list;
        }
        
        public String getConfigPath()
        {
             return config.toString();
        }
    }

    private static final Map<String, ModConfiguration> configs = new LinkedHashMap<String, ModConfiguration>();

    public static Collection<String> getConfigsIds()
    {
        return Collections.unmodifiableCollection(configs.keySet());
    }

    public static ModConfiguration getConfig(String modId)
    {
        return configs.get(modId.toLowerCase());
    }

    public static void processAnnotations(String modId, Configuration config, Class<?> configClass)
    {
        if (configs.containsKey(modId))
            throw new IllegalArgumentException("Trying to configure mod " + modId + " twice");
        ModConfiguration modConfig = new ModConfiguration(modId, config, configClass);
        configs.put(modId.toLowerCase(), modConfig);

        for (Field f : configClass.getFields())
            if (Modifier.isStatic(f.getModifiers())) modConfig.processField(f);

        modConfig.save();

        MinecraftForge.EVENT_BUS.register(modConfig);
    }
}
