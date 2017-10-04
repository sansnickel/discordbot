package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;


public class Hearthstone {

    public static String getCard(String card, String key) throws IOException {
        //System.out.println(card.substring(6));
        String url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/" + card.substring(6).replace(" ", "%20");
        InputStream is = HttpRequest.sendGet(url, key);
        if (is != null) {       
            String response = JsonParser.parseJson(is, "card");
            is.close();
            return response;
        }
        else
            return "cannot find card";
        
    }
    
    
    
}
