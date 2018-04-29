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

    private final static String URL = "http://api.wolframalpha.com/v2/query?appid=";
    private final static String URL2 = "http://api.wolframalpha.com/v1/result?appid=";
    private final static int TIMEOUT = 60;
    
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
    }
    
    public static String getSimpleInfo(String msg, String key) {
        try {
            String url = URL2 + key + "&i=" + URLEncoder.encode(msg, "UTF-8");
            InputStream is = HttpRequest.sendGet(url);
            StringBuilder s = new StringBuilder();
            BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
            while (buffer.ready()) {
                s.append(buffer.readLine());
            }
            buffer.close();
            return "Query: " + msg.substring(6) + "\nResult: " + s.toString();
        } catch (IOException e) {
            return "=wolf " + msg.substring(6);
        }
    }
    
}
