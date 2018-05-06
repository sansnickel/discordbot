package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sans.discordbot.hearthstone.Card;
import sans.discordbot.league.Game;
import sans.discordbot.league.League;
import sans.discordbot.league.Summoner;
import sans.discordbot.league.SummonerInGame;
import sans.discordbot.oxford.Definition;
import sans.discordbot.oxford.Entry;

/** A collection of methods to extract desired information from InputStreams carrying JSON Objects. */
public class JsonParser {

    
    public static Definition getDef(InputStream is) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);       
        JSONArray arr = obj.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries");
        
        String word = obj.getJSONArray("results").getJSONObject(0).getString("id");
        List<Entry> entries = new ArrayList<Entry>();
        
        for (int i = 0; i < arr.length(); i++) {
 
            JSONArray senses = arr.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getJSONArray("senses");
            
            String lexcat = arr.getJSONObject(i).getString("lexicalCategory");
            List<String> defs = new ArrayList<String>();
            
            
            for (int j = 0; j < senses.length(); j++) {
                
                if(senses.getJSONObject(j).has("definitions")) {
                    defs.add(senses.getJSONObject(j).getJSONArray("definitions").getString(0));
                }
            }
            
            Entry e = new Entry(lexcat, defs);
            entries.add(e);            
            
        }
        
        return new Definition(word, entries);
    }
    
    public static Card getCard(InputStream is) throws IOException, JSONException {
       
        // assumption that no collectible card has the same name
        JSONObject card = getJsonArr(is).getJSONObject(0);
        
        String name = card.getString("name");
        String cardset = card.getString("cardSet");
        String flavor = card.getString("flavor");
        String img = card.getString("img");
        String artist = card.getString("artist");

        return new Card(name, cardset, flavor, img, artist);
    }
        
    public static String getSpells(InputStream is, String str) throws IOException, JSONException {
        
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
    
    public static Game getLiveGame(InputStream is, String key) throws IOException, JSONException {
        
        List<SummonerInGame> blueteam = new ArrayList<SummonerInGame>();
        List<SummonerInGame> redteam = new ArrayList<SummonerInGame>();
        
              
        JSONObject obj = getJsonObj(is);
        JSONArray participants = obj.getJSONArray("participants");
        
        for (int i = 0; i < participants.length(); i++) {
          
            JSONObject participant = participants.getJSONObject(i);
                     
            String name = participant.getString("summonerName");
            long id = participant.getLong("summonerId");
            long team = participant.getLong("teamId");
            long champion = participant.getLong("championId");
            
            List<Long> runes = new ArrayList<Long>();
            
            Summoner s = League.getSummonerInfo(name, key);
                        
            JSONArray perks = participant.getJSONObject("perks").getJSONArray("perkIds");
            for (int j = 0; j < perks.length(); j++) {
                runes.add(perks.getLong(j)); 
            }
                 
                     
            SummonerInGame summoner = new SummonerInGame(name, id, s.getLevel(), s.getRank(), s.getWinRate(), team, champion, runes);
            
            if (participant.getLong("teamId") == 100) {
                blueteam.add(summoner);
            }
            else {
                redteam.add(summoner);
            }    
 
        }
        
        
        
        return new Game(blueteam, redteam);
    }
    
    public static Summoner getSummonerInfo(InputStream is) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);
        
        String name = obj.getString("name");
        long id = obj.getLong("id");
        long level = obj.getLong("summonerLevel");
              
        return new Summoner(name, id, level);
    }
    
    public static Summoner getSummonerSoloStats(Summoner s, InputStream is) throws IOException, JSONException {
        
        JSONArray arr = getJsonArr(is);
        
        String name = s.getName();
        long id = s.getId();
        String rank = "";
        long level = s.getLevel();
        int winrate = -1;
                
        for (int i = 0; i < arr.length(); i++) {
            
            JSONObject queue =  arr.getJSONObject(i);
            
            if (queue.getString("queueType").equals("RANKED_SOLO_5x5")) {
                
                rank = queue.getString("tier") + " " + queue.getString("rank");
                
                int wins = queue.getInt("wins");
                int losses = queue.getInt("losses");
  
                winrate = (int) Math.round(((double) wins/(wins+losses)*100));
                
                break;
            }
        }

        return new Summoner(name, id, level, rank, winrate);

    }
    
    public static String getRuneName (long id) {
        JSONArray runes = new JSONArray(League.RUNES);
        for (int i = 0; i < runes.length(); i++) {
            
            if (runes.getJSONObject(i).getInt("id") == (int) id) {
                return runes.getJSONObject(i).getString("name");
            }
        }
        return "";
    }
    
    public static String getChampionName (long id) {
        JSONObject champions = new JSONObject(League.CHAMPIONS).getJSONObject("data");
        String name = champions.getJSONObject(String.valueOf(id)).getString("key");
        return name;
    }
    
    public static String getPatchNo (InputStream is) throws IOException {
        JSONArray patch = getJsonArr(is);
        return patch.getString(0);
        
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
