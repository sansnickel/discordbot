package sans.discordbot.hearthstone;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.json.JSONException;

import sans.discordbot.HttpRequest;
import sans.discordbot.JsonParser;


public class Hearthstone {

    private static final String URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/";    
    
    public static Card getCardInfo(String card, String key) throws IOException, JSONException {

        String url = URL + URLEncoder.encode(card, "UTF-8").replace("+", "%20") + "?collectible=1";
        InputStream is = HttpRequest.sendGetHS(url, key);
        Card hscard = JsonParser.getCard(is);
        is.close();
        return hscard;
 
    }
}
