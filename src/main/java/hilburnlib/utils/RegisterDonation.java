package hilburnlib.utils;

import cpw.mods.fml.common.Optional;
import hilburnlib.reference.Mods;

import java.lang.reflect.Method;

public class RegisterDonation
{
    @Optional.Method(modid = Mods.OPENBLOCKS)
    public static void setDonationURL(String ID, String donationUrl)
    {
        try
        {
            Class donationManager = Class.forName("openblocks.common.DonationURLManager");
            Method instance = donationManager.getMethod("instance");
            Method url = donationManager.getMethod("addUrl");
            url.invoke(instance.invoke(null),ID,donationUrl);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
