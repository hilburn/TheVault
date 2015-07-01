package thevault.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;

import java.util.List;

/**
 * Functions used to serialize and deserialize 3D {@link MetaDataBlock}s
 */
public class StructureIO
{
    /**
     * Serialize a 3D {@link MetaDataBlock} structure to a string
     * @param dim3 the structure
     * @return the {@link String} version of the structure
     */
    public static String serialize(MetaDataBlock[][][] dim3)
    {
        StringBuilder content = new StringBuilder();
        for (MetaDataBlock[][] dim2 : dim3)
        {
            for (MetaDataBlock[] dim1 : dim2)
            {
                for (MetaDataBlock block : dim1)
                {
                    if (block == null)
                        block = new MetaDataBlock(Blocks.air);
                    content.append("<" + block.toString() + "> ");
                }
                content.append("\n");
            }
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Deserialize a {@link List<String>}  representation of a structure
     * @param raw a {@link List<String>} representation of a 3D {@link MetaDataBlock} structure
     * @return the 3D {@link MetaDataBlock} structure
     */
    public static MetaDataBlock[][][] deserialize(List<String> raw)
    {
        int dim1s = raw.get(0).split(" ").length;
        int dim2s = raw.indexOf("");
        int dim3s = 0;
        for (String line : raw)
            if (line.equals(""))
                dim3s++;
        MetaDataBlock[][][] dim3 = new MetaDataBlock[dim3s][dim2s][dim1s];
        int id1, id2, id3;
        id1 = id2 = id3 = 0;
        for (String line : raw)
        {
            String[] blocks = line.split(" ");
            for (String block : blocks)
            {
                if (block.equals("")) continue;
                block = block.substring(1, block.length() - 1);
                String[] parts = block.split(":");
                dim3[id3][id2][id1] = new MetaDataBlock(GameRegistry.findBlock(parts[0], parts[1]), Integer.getInteger(parts[2], 0));
                id1++;
            }
            id1 = 0;
            id2++;
            if (line.equals(""))
            {
                id3++;
                id2 = 0;
            }
        }
        return dim3;
    }
}