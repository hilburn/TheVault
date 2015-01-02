package hilburnlib.utils;

import java.lang.reflect.Method;

public class RegisterDonation
{
    public static void setDonationURL(String ID, String donationUrl)
    {
        try
        {
            Class donationManager = Class.forName("openblocks.common.DonationURLManager");
            Method instance = donationManager.getMethod("instance");
            Method url = donationManager.getMethod("addUrl");
            url.invoke(instance.invoke(null),ID,donationUrl);
        } catch (ClassNotFoundException e)
        {
        } catch (Exception e)
        {
            LogHelper.warn("Donation Station Integration Frustration");
        }
    }
}
