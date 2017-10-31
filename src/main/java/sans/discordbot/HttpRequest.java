package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class HttpRequest {
    
    // Sends a GET Request to the given URL and returns the InputStream received
    public static InputStream sendGet(String url) throws MalformedURLException, IOException {
        HttpURLConnection c = getHttpURLConnection(url);
        setGetProperties(c);
        return c.getInputStream();
    }
    
    // Sends a GET Request to the given URL with the HS X-Mashape-Key and returns the InputStream received
    public static InputStream sendGetHS(String url, String key) throws MalformedURLException, IOException {
        HttpURLConnection c = getHttpURLConnection(url);
        setGetProperties(c);
        setHSKey(c, key);
        return c.getInputStream();
    }
    
    /* Helper Functions */
    
    // Returns the HttpURLConnection from a given URL
    static HttpURLConnection getHttpURLConnection(String url) throws MalformedURLException, IOException {
        //try {       
        URL u = new URL(url);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        return c;
    }
    
    // Just a wrapper to set the X-Mashape-Key Header of a HttpURLConnection c to key
    static void setHSKey(HttpURLConnection c, String key) throws ProtocolException {
        c.setRequestProperty("X-Mashape-Key", key);
    }
    
    // Set standard GET properties/values for an HttpURLConnection
    static void setGetProperties(HttpURLConnection c) throws ProtocolException {
        c.setRequestMethod("GET");
        c.setRequestProperty("Accept", "application/json");
        c.setUseCaches(false);
    }
    
    
}
    

