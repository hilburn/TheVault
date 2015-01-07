package hilburnlib.config;

import hilburnlib.nbt.NBTHelper;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Sync
{
    public static byte[] getSyncMessage(Class clazz)
    {
        Field[] fields = clazz.getFields();
        NBTTagCompound nbtConfig = new NBTTagCompound();
        nbtConfig.setString("C", clazz.getName());
        nbtConfig.setShort("L", (short) fields.length);
        for (short i=0;i<fields.length;i++)
        {
            try
            {
                Field field = fields[i];
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) && !Modifier.isFinal(mod))
                {
                    Object obj = field.get(null);
                    if (obj == null) continue;
                    NBTTagCompound tagCompound = NBTHelper.writeToNBT(obj);
                    if (!tagCompound.hasNoTags()) nbtConfig.setTag(String.valueOf(i), tagCompound);
                }
            } catch (IllegalAccessException e) {}
        }
        byte[] bytes = new byte[0];
        try
        {
            bytes = CompressedStreamTools.compress(nbtConfig);
        } catch (IOException e) {}
        return bytes;
    }

    public static void loadSyncMessage(byte[] bytes)
    {
        try
        {
            NBTTagCompound config = CompressedStreamTools.func_152457_a(bytes,new NBTSizeTracker(bytes.length*8));
            Field[] fields = Class.forName(config.getString("C")).getFields();
            short length = config.getShort("L");
            for (short i = 0; i<length; i++)
            {
                Object obj = NBTHelper.rawFromNBT(config.getCompoundTag(String.valueOf(i)));
                if (obj!=null)
                    fields[i].set(null,obj);
            }
        } catch (Exception e){}
        return;
    }
}