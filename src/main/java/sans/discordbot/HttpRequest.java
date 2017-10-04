package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest {
    public static InputStream sendGet(String url, String key) {
        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
    
            c.setRequestMethod("GET");
            c.setRequestProperty("X-Mashape-Key", key);
            c.setRequestProperty("Accept", "application/json");
            c.setUseCaches(false);
    
            // int status = c.getResponseCode();
            return c.getInputStream();
        } catch (IOException e) {
            return null;
        }

    }
}
    

