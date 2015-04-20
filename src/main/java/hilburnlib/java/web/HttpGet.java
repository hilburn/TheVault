package hilburnlib.java.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class HttpGet implements Callable<String>
{
    protected String url;
    protected static final String ENCODING = "UTF-8";
    
    public HttpGet(String url)
    {
        this.url = url;
    }
    
    public String getContents()
    {
        try
        {
            URL url = new URL(this.url);

            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (builder.length() > 0)
                    builder.append('\n');
                builder.append(line);
            }
            reader.close();
            return new String(builder);
        } catch (MalformedURLException e)
        {
            throw new IllegalArgumentException("Malformed link: " + e);
        } catch (IOException e)
        {
            throw new RuntimeException("Failed to fetch contents from link: " + e);
        }
    }

    @Override
    public String call() throws Exception
    {
        return getContents();
    }
}
