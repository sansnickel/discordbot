package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class League {
    
    final static String PATCH_NO = "7.21";
    
    public static String getCDs(String champ) {
        String[] words = champ.substring(5).split(" ");
        StringBuilder str = new StringBuilder();
        for (String word : words) {
            str.append(word.substring(0,1).toUpperCase() + word.substring(1));
        }
        String url = "http://ddragon.leagueoflegends.com/cdn/" + PATCH_NO + ".1/data/en_US/champion/" + str.toString() + ".json";
        
        try {        
            InputStream is = HttpRequest.sendGet(url);
            String response = JsonParser.parseJsonLoL(is, str.toString());
            is.close();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "URL is bad.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot find champ.";
        }

    }

    
    
}
