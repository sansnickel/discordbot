package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sans.discordbot.hearthstone.Card;
import sans.discordbot.hearthstone.Hearthstone;
import sans.discordbot.league.League;

/** A collection of methods to extract desired information from InputStreams carrying JSON Objects. */
public class JsonParser {

    
    public static String parseJsonOxf(InputStream is) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);
        JSONArray arr = obj.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries");
        
        StringBuilder response = new StringBuilder();
        response.append(obj.getJSONArray("results").getJSONObject(0).getString("id")+"\n");
        
        for (int i = 0; i < arr.length(); i++) {
            
            JSONArray senses = arr.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getJSONArray("senses");
            response.append(arr.getJSONObject(i).getString("lexicalCategory") + "\n");
            for (int j = 0; j < senses.length(); j++) {
                response.append((j+1) + ". " + senses.getJSONObject(j).getJSONArray("definitions").getString(0) + "\n");
                
            }
        }
        
        return toOutputBlock(response.toString(), "ml");
    }
    
    public static Card parseJsonCard(InputStream is) throws IOException, JSONException {
       
        // assumption that no collectible card has the same name
        JSONObject card = getJsonArr(is).getJSONObject(0);
        
        String name = card.getString("name");
        String cardset = card.getString("cardSet");
        String flavor = card.getString("flavor");
        String img = card.getString("img");
        String artist = card.getString("artist");

        return new Card(name, cardset, flavor, img, artist);
    }
        
    public static String parseJsonCds(InputStream is, String str) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);
        JSONArray arr = obj.getJSONObject("data").getJSONObject(str).getJSONArray("spells");
        
        StringBuilder response = new StringBuilder();
        response.append(str + " Cooldowns\n");
        
        for(int i = 0; i < arr.length(); i++) {
           
            String cd = arr.getJSONObject(i).getString("cooldownBurn");   
            
            switch (i) {
                case 0: response.append("Q: "); break;
                case 1: response.append("W: "); break;
                case 2: response.append("E: "); break;
                case 3: response.append("R: "); break;
                default: break;
            }
            
            response.append(cd + "\n");
        }
        return toOutputBlock(response.toString(), "python"); 
    }
    
    public static String parseJsonLiveGame(InputStream is, String key) throws IOException, JSONException {
        
        StringBuilder response = new StringBuilder();
        JSONObject obj = getJsonObj(is);
        JSONArray participants = obj.getJSONArray("participants");
        for (int i = 0; i < participants.length(); i++) {
          
            JSONObject participant = participants.getJSONObject(i);
            String name = participant.getString("summonerName");    
            response.append(name + " ");
            response.append(League.getRankAndWr(name, key) + " | ");
            
            JSONArray perks = participant.getJSONObject("perks").getJSONArray("perkIds");
         
            for (int j = 0; j < perks.length(); j++) {
                response.append(perks.getLong(j) + " ");
            }
            response.append("\n");
        }
        
        return response.toString();
    }
    
    public static long getSummonerId(InputStream is) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);
        long id = obj.getLong("id");
        
        return id;
    }
    
    public static String getRankAndWr(InputStream is) throws IOException, JSONException {
        
        StringBuilder response = new StringBuilder();
        
        JSONArray arr = getJsonArr(is);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject queue =  arr.getJSONObject(i);
            
            if (queue.getString("queueType").equals("RANKED_SOLO_5x5")) {
                response.append(queue.getString("tier") + " ");
                response.append(queue.getString("rank") + " ");
                
                int wins = queue.getInt("wins");
                int losses = queue.getInt("losses");
  
                int winrate = (int) Math.round(((double) wins/(wins+losses)*100));
                
                response.append(winrate);
                break;
            }
        }

        return response.toString();

    }
    
    
    private static JSONArray getJsonArr (InputStream is) throws IOException {
        
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        
        return new JSONArray(jsonTxt);
    }
    
    private static JSONObject getJsonObj (InputStream is) throws IOException {
        
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        return new JSONObject(jsonTxt);
        
    }
    
    public static String toOutputBlock(String msg, String lang) {
        
        return "```" + lang + "\n" + msg + "```";
        
    }
    
 }
