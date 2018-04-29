package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

public class Wolfram {

    private final static String LINK = "http://wolframalpha.com/input/?=";
    private final static String URLSHORT = "http://api.wolframalpha.com/v1/result?appid=";
    private final static String URLSIMPLE = "http://api.wolframalpha.com/v1/simple?appid=";
    
    private final String query;
    private final String encodedquery;
    private final String response;
    private final String link;
    
    /** Holds a user query, Wolfram Alpha's Short Answer response, and other related information. */ 
    
    public Wolfram (String query, String encodedquery, String response, String link) {
        this.query = query;
        this.encodedquery = encodedquery;
        this.response = response;
        this.link = link;
    }
 
    public String getQuery () {
        return this.query;
    }
    
    public String getEncodedQuery() {
        return this.encodedquery;
    }
        
    public String getResponse () {
        return this.response;
    }
    
    public String getLink () {
        return this.link;
    }
   
    
    /*
    
    public static String getWolfInfo(String msg, String key) {
        try {
            String url = URL + key + "&input=" + URLEncoder.encode(msg, "UTF-8") + "&format=image&output=JSON";
            //System.out.println(url);
            InputStream is = HttpRequest.sendGet(url);
            String response = JsonParser.parseJsonWolf(is);
      
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot interpret query.";
        }
    }*/
    
    public static InputStream getImage(String msg, String key) throws IOException { 
        
        String url = URLSIMPLE + key + "&i=" + URLEncoder.encode(msg, "UTF-8");
        InputStream is = HttpRequest.sendGet(url);
        return is;
        
    }
    
    
    
    public static Wolfram getText(String query, String key) {
       
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
            
            return new Wolfram (query, encodedquery, response.toString(), link);    
      
        } catch (IOException e) {
            e.printStackTrace();
            return new Wolfram (query, null, "Could not interpret query.", null );
        }
    }
    
}
