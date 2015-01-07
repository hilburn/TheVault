package hilburnlib.client.font;

import hilburnlib.reference.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;

public class Font
{
    private FontRendererWithZLevel fontRenderer;

    public Font(boolean unicode, int zLevel)
    {
        Minecraft mc = Minecraft.getMinecraft();
        fontRenderer = new FontRendererWithZLevel(mc.gameSettings, Resources.Vanilla.FONT, mc.getTextureManager(), unicode).setZLevel(zLevel);
        ((IReloadableResourceManager) mc.getResourceManager()).registerReloadListener(fontRenderer);
    }
    
    public Font(boolean unicode)
    {
        this(unicode, 0);
    }
    
    public Font(int zLevel)
    {
        this(false, zLevel);
    }
    
    public float getCurrentZLevel()
    {
        return this.fontRenderer.getZLevel();
    }

    public Font setZLevel(float zLevel)
    {
        this.fontRenderer.setZLevel(zLevel);
        return this;
    }

    public Font incZLevel(float inc)
    {
        this.fontRenderer.incZLevel(inc);
        return this;
    }

    public Font decZLevel(float dec)
    {
        this.fontRenderer.decZLevel(dec);
        return this;
    }

    public Font incZLevel()
    {
        this.fontRenderer.incZLevel();
        return this;
    }

    public Font decZLevel()
    {
        this.fontRenderer.decZLevel();
        return this;
    }

    public void print(Object o, int x, int y)
    {
        fontRenderer.drawString(String.valueOf(o), x, y, 8, false);
    }

    public void print(Object o, int x, int y, int color)
    {
        fontRenderer.drawString(String.valueOf(o), x, y, color, false);
    }

    public void print(Object o, int x, int y, int color, boolean shadow)
    {
        fontRenderer.drawString(String.valueOf(o), x, y, color, shadow);
    }

    public void printWithZ(Object o, int x, int y, int z)
    {
        float prevZ = fontRenderer.getZLevel();
        fontRenderer.setZLevel(z);
        fontRenderer.drawString(String.valueOf(o), x, y, 8, false);
        fontRenderer.setZLevel(prevZ);
    }

    public void printWithZ(Object o, int x, int y, int z, int color)
    {
        float prevZ = fontRenderer.getZLevel();
        fontRenderer.setZLevel(z);
        fontRenderer.drawString(String.valueOf(o), x, y, color, false);
        fontRenderer.setZLevel(prevZ);
    }

    public void printWithZ(Object o, int x, int y, int z, int color, boolean shadow)
    {
        float prevZ = fontRenderer.getZLevel();
        fontRenderer.setZLevel(z);
        fontRenderer.drawString(String.valueOf(o), x, y, color, shadow);
        fontRenderer.setZLevel(prevZ);
    }
    
    public FontRendererWithZLevel getFontRenderer()
    {
        return this.fontRenderer;
    }
}
