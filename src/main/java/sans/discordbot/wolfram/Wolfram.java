package sans.discordbot.wolfram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import sans.discordbot.HttpRequest;


public class Wolfram {

    private final static String LINK = "http://wolframalpha.com/input/?=";
    private final static String URLSHORT = "http://api.wolframalpha.com/v1/result?appid=";
    private final static String URLSIMPLE = "http://api.wolframalpha.com/v1/simple?appid=";
     
    public static InputStream getImage(String msg, String key) throws IOException { 
        
        String url = URLSIMPLE + key + "&i=" + URLEncoder.encode(msg, "UTF-8");
        InputStream is = HttpRequest.sendGet(url);
        return is;
        
    }
    
    public static ShortAnswer getText(String query, String key) {
       
        try {
            
            String encodedquery = URLEncoder.encode(query, "UTF-8");
            String url = URLSHORT + key + "&i=" + encodedquery;
            String link = LINK + encodedquery;
           
            InputStream is = HttpRequest.sendGet(url);
            StringBuilder response = new StringBuilder();
            BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
            
            while (buffer.ready()) {
                response.append(buffer.readLine());
            }
            buffer.close();
            
            return new ShortAnswer(query, encodedquery, response.toString(), link);    
      
        } catch (IOException e) {
            e.printStackTrace();
            return new ShortAnswer(query, null, "Could not interpret query.", null );
        }
    }
    
}
