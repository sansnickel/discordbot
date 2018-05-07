package sans.discordbot.wolfram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

import sans.discordbot.HttpRequest;

/**
 * A collection of methods for getting information from Wolfram's API. 
 * 
 *
 */

public class Wolfram {

    private final static String LINK = "http://wolframalpha.com/input/?=";
    private final static String URLSHORT = "http://api.wolframalpha.com/v1/result?appid=";
    private final static String URLSIMPLE = "http://api.wolframalpha.com/v1/simple?appid=";
     
    public static InputStream getImage(String msg, String key) throws IOException { 
        
        String url = URLSIMPLE + key + "&i=" + URLEncoder.encode(msg, "UTF-8");
        
        InputStream is = HttpRequest.sendGet(url);
        return is;
        
        
    }
    
    public static Optional<ShortAnswer> getText(String query, String key) {
       
        String encodedquery = "";
        try {
            
            encodedquery = URLEncoder.encode(query, "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            return Optional.empty();
        }
        
        String url = URLSHORT + key + "&i=" + encodedquery;
        String link = LINK + encodedquery;
           
        try (InputStream is = HttpRequest.sendGet(url);
             BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));) {
            
            StringBuilder response = new StringBuilder();
            
            while (buffer.ready()) {
                response.append(buffer.readLine());
            }
            
            return Optional.of(new ShortAnswer(query, encodedquery, response.toString(), link));    
      
        } catch (IOException e) {
            
            e.printStackTrace();
            return Optional.of(new ShortAnswer(query, null, "Could not interpret query.", null));
            
        }
    }
    
}
