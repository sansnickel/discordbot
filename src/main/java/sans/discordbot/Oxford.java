package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;

public class Oxford {

    private final static String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/";
    
    
    public static String getDef(String msg, String appid, String key) {
        try {
            InputStream is = HttpRequest.sendGetOxf(url + msg.substring(5), appid, key);
            String response = JsonParser.parseJsonOxf(is);
            return response;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Cannot find definition.";
        }
    }
    
}
