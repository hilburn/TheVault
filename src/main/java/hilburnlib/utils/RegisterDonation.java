package hilburnlib.utils;

import java.lang.reflect.Method;

public class RegisterDonation
{
    public static void setDonationURL(String modID, String donationUrl)
    {
        try
        {
            Class donationManager = Class.forName("openblocks.common.DonationURLManager");
            Method instance = donationManager.getMethod("instance");
            Method url = donationManager.getMethod("addUrl");
            url.invoke(instance.invoke(null),modID,donationUrl);
            LogHelper.info("Donation Station Integration Completion");
        } catch (ClassNotFoundException e)
        {
            //OpenBlocks isn't loaded - not actually an error
            LogHelper.info("Donation Station Perambulation, Location Calibration Frustration");
        } catch (Exception e)
        {
            LogHelper.warn("Donation Station Integration Frustration");
        }
    }
}
