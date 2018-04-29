package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.google.common.util.concurrent.RateLimiter;

public class HttpRequest {
    
    private static final RateLimiter throttle = RateLimiter.create(5);
    
    /** Sends a GET Request to the given URL and returns the InputStream received */
    public static InputStream sendGet(String url) throws IOException {
       
        HttpURLConnection c = getHttpURLConnection(url);
        setGetProperties(c);
        return c.getInputStream();
        
    }
    
    // Sends a GET Request to the given URL with the HS X-Mashape-Key and returns the InputStream received
    public static InputStream sendGetHS(String url, String key) throws IOException {
       
        HttpURLConnection c = getHttpURLConnection(url);
        setGetProperties(c);
        setHSKey(c, key);
        return c.getInputStream();
        
    }
    
    public static InputStream sendGetOxf(String url, String appid, String key) throws IOException {
        
        HttpURLConnection c = getHttpURLConnection(url);
        setGetProperties(c);
        setOxfKey(c, appid, key);
        return c.getInputStream();
        
    }
    
    public static InputStream sendGetLoL(String url, String key) throws IOException {
        
        throttle.acquire(6);
        System.out.println("sending");
        
        HttpURLConnection c = getHttpURLConnection(url);
        setGetProperties(c);
        setLoLKey(c, key);
   
        return c.getInputStream();
        
    }
    
    
    /* Helper Functions */
    
    // Returns the HttpURLConnection from a given URL
    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        //try {       
        URL u = new URL(url);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        return c;
    }
    
    // Just a wrapper to set the X-Mashape-Key Header of a HttpURLConnection c to key
    private static void setHSKey(HttpURLConnection c, String key) throws ProtocolException {
        c.setRequestProperty("X-Mashape-Key", key);
    }
    
    private static void setOxfKey(HttpURLConnection c, String id, String key) {
        c.setRequestProperty("app_id", id);
        c.setRequestProperty("app_key", key);
    }
    
    private static void setLoLKey(HttpURLConnection c, String key) throws ProtocolException {
        c.setRequestProperty("X-Riot-Token", key);
    }
    
    // Set standard GET properties/values for an HttpURLConnection
    private static void setGetProperties(HttpURLConnection c) throws ProtocolException {
        c.setRequestMethod("GET");
        c.setRequestProperty("Accept", "application/json");
        c.setUseCaches(false);
    }
    
    
}
    

