package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;


public class Hearthstone {

    public static String getCardInfoAsString(String card, String key)  {
        String url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/" + card.substring(6).replace(" ", "%20");
        try {        
            InputStream is = HttpRequest.sendGetHS(url, key);
            String response = JsonParser.parseJsonHS(is);
            is.close();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "URL is bad.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot find card.";
        }
    }
}
