package sans.discordbot.oxford;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;

import sans.discordbot.HttpRequest;
import sans.discordbot.JsonParser;

public class Oxford {

    private static final String URL = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/";
    
    
    public static Definition getDef(String msg, String appid, String key) throws IOException {
        
        String query = URLEncoder.encode(msg, "UTF-8").replace("+", "%20");
        String url = URL + query;
        
        try (InputStream is = HttpRequest.sendGetOxf(url, appid, key);) {
                               
            Definition def = JsonParser.getDef(is);
            return def;
            
        } 
    }
    
}
