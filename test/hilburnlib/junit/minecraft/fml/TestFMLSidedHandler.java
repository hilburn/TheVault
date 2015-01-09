package hilburnlib.junit.minecraft.fml;

import cpw.mods.fml.common.IFMLSidedHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.StartupQuery;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.util.List;
import java.util.Set;

public class TestFMLSidedHandler implements IFMLSidedHandler
{
    @Override
    public List<String> getAdditionalBrandingInformation()
    {
        return null;
    }

    @Override
    public Side getSide()
    {
        return Side.CLIENT;
    }

    @Override
    public void haltGame(String message, Throwable exception)
    {

    }

    @Override
    public void showGuiScreen(Object clientGuiElement)
    {

    }

    @Override
    public void queryUser(StartupQuery query) throws InterruptedException
    {

    }

    @Override
    public void beginServerLoading(MinecraftServer server)
    {

    }

    @Override
    public void finishServerLoading()
    {

    }

    @Override
    public File getSavesDirectory()
    {
        return null;
    }

    @Override
    public MinecraftServer getServer()
    {
        return null;
    }

    @Override
    public boolean shouldServerShouldBeKilledQuietly()
    {
        return false;
    }

    @Override
    public void addModAsResource(ModContainer container)
    {

    }

    @Override
    public String getCurrentLanguage()
    {
        return null;
    }

    @Override
    public void serverStopped()
    {

    }

    @Override
    public NetworkManager getClientToServerNetworkManager()
    {
        return null;
    }

    @Override
    public INetHandler getClientPlayHandler()
    {
        return null;
    }

    @Override
    public void waitForPlayClient()
    {

    }

    @Override
    public void fireNetRegistrationEvent(EventBus bus, NetworkManager manager, Set<String> channelSet, String channel, Side side)
    {

    }

    @Override
    public boolean shouldAllowPlayerLogins()
    {
        return false;
    }

    @Override
    public void allowLogins()
    {

    }
}
