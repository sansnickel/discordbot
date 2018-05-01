package sans.discordbot.oxford;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;

import sans.discordbot.HttpRequest;
import sans.discordbot.JsonParser;

public class Oxford {

    private static final String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/";
    
    
    public static String getDef(String msg, String appid, String key) {
        
        try {
            
            
            InputStream is = HttpRequest.sendGetOxf(url + msg, appid, key);
            String response = JsonParser.parseJsonOxf(is);
            return response;
            
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot find definition.";
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error parsing response.";
        }
    }
    
}
