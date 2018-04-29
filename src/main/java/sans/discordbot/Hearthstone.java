package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.json.JSONException;


public class Hearthstone {

    private static final String URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/";
    
    public static String getCardInfo(String card, String key)  {

        try {        
            
            String url = URL + URLEncoder.encode(card, "UTF-8").replace("+", "%20") + "?collectible=1";
            InputStream is = HttpRequest.sendGetHS(url, key);
            String response = JsonParser.parseJsonHS(is);
            is.close();
            return response;
            
        } catch (IOException e) {                      
            e.printStackTrace();
            return "Cannot find card.";
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error parsing response.";
        }
    }
}
