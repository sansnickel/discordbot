package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;

public class League {
    
    public static String getCDs(String champ) throws IOException {
        String[] words = champ.substring(5).split(" ");
        StringBuilder str = new StringBuilder();
        for (String word : words) {
            str.append(word.substring(0,1).toUpperCase() + word.substring(1));
        }
        
        String url = "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion/" + str.toString() + ".json";
        InputStream is = HttpRequest.sendGet(url);
        if (is != null) {       
            String response = JsonParser.parseJson(is, "cds", str.toString());
            is.close();
            return response;
        }
        else
            return "cannot find champ";
    }

    
    
}
