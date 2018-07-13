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

    /** Gets the definition from an InputStream carrying a JSON object from the Oxford API.
     * @param is The InputStream
     * @return A Definition Object
     * @throws IOException if there was a problem getting the info from Oxford API
     * @throws JSONException if there was a problem parsing the JSON
     */
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
    
    /** Gets the card information from an InputStream carrying a JSON Object from the Mashape API.
     * @param is The InputStream
     * @return a Card object
     * @throws IOException if there was a problem getting the info from Mashape (card does not exist, etc.)
     * @throws JSONException if there was a problem parsing the JSON
     */
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
        
    /** Gets the cooldowns of a champion's spells in League of Legends.
     * @param is The InputStream
     * @param champ The Champion Name
     * @return a String for the bot to output (shown to user)
     * @throws IOException if there was a problem getting the information from ddragon
     * @throws JSONException if there was a problem parsing the JSON
     */
    public static String getSpells(InputStream is, String champ) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);
        JSONArray arr = obj.getJSONObject("data").getJSONObject(champ).getJSONArray("spells");
        
        StringBuilder response = new StringBuilder();
        response.append(champ + " Cooldowns\n");
        
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
    
    /** Gets the live game information of a player in League of Legends from an InputStream carrying a JSON Object from the League API.
     * @param is
     * @param key
     * @return a Game object with the participants, their ranks, win rates, and runes
     * @throws IOException if there was a problem getting the information from the League API
     * @throws JSONException if there was a problem parsing the JSON
     */
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
    
    /** Gets the name, id, and level of a summoner in League of Legends given the InputStream carrying a JSON object from the League API.
     * @param is the InputStream
     * @return a Summoner object with the information
     * @throws IOException if there was a problem getting information from the League API
     * @throws JSONException if there was a problem parsing the JSON
     */
    public static Summoner getSummonerInfo(InputStream is) throws IOException, JSONException {
        
        JSONObject obj = getJsonObj(is);
        
        String name = obj.getString("name");
        long id = obj.getLong("id");
        long level = obj.getLong("summonerLevel");
              
        return new Summoner(name, id, level);
    }
    
    /** Adds the rank and win rate information of a summoner in League of Legends given the InputStream carrying a JSON object from the League API, as well as an existing Summoner object.
     * @param s the Summoner
     * @param is the InputStream
     * @return an updated Summoner object
     * @throws IOException if there was a problem getting information from the League API
     * @throws JSONException if there was a problem parsing the JSON
     */
    public static Summoner getSummonerSoloStats(Summoner s, InputStream is) throws IOException, JSONException {
        
        JSONArray arr = getJsonArr(is);
        
        String name = s.getName();
        long id = s.getId();
        String rank = "UNRANKED";
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
    
    /** Gets the rune name given the id of a rune (as stated by the League API). 
     * @param id the id of the rune
     * @return the name of the rune
     */
    public static String getRuneName (long id) {
        JSONArray runes = new JSONArray(League.RUNES);
        for (int i = 0; i < runes.length(); i++) {
            
            if (runes.getJSONObject(i).getInt("id") == (int) id) {
                return runes.getJSONObject(i).getString("name");
            }
        }
        return "";
    }
    
    /** Gets the name of a League champion given its id (as stated by the League API).
     * @param id the id of the champion
     * @return the name of the champion
     */
    public static String getChampionName (long id) throws IOException {
        
        
        String name = "";
        JSONObject champions = new JSONObject(League.getAllChampNames()).getJSONObject("data");
        for (int i = 0; i < champions.names().length(); i++) {
            JSONObject champ = champions.getJSONObject(champions.names().getString(i));
            
            if (champ.getString("key").equals(String.valueOf(id))) {
                name = champions.names().getString(i);
                break;
            }
        }

        return name;
    }
    
    public static String getRealChampName (String champ) throws IOException {
        String name = "";
        JSONObject champions = new JSONObject(League.getAllChampNames()).getJSONObject("data");
        for (int i = 0; i < champions.names().length(); i++) {
            JSONObject champion = champions.getJSONObject(champions.names().getString(i));
            if (champion.getString("name").equals(champ)) {
                name = champion.getString("id");
                break;
            }
        }
        
        return name;
    }
    
    public static String getAllChampNames (InputStream is) throws IOException {
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        return jsonTxt;
    }
    
    /** Gets the current League of Legends patch as stated by ddragon.
     * @param is the InputStream from ddragon carrying a JSON object of the versions
     * @return the current patch number
     * @throws IOException if there was a problem getting information from ddragon 
     */
    public static String getPatchNo (InputStream is) throws IOException {
        JSONArray patch = getJsonArr(is);
        return patch.getString(0);
        
    }
    
    /** Gets a JSONArray object from an InputStream
     * @param is the InputStream
     * @return a JSONArray representation of the data in the stream
     * @throws IOException if there was a problem reading from the stream
     */
    private static JSONArray getJsonArr (InputStream is) throws IOException {
        
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        
        return new JSONArray(jsonTxt);
    }
    
    /** Gets a JSONObject from an InputStream
     * @param is the InputStream
     * @return a JSONObject representation of the data in the stream
     * @throws IOException if there was a problem reading from the stream
     */
    private static JSONObject getJsonObj (InputStream is) throws IOException {
        
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is, "UTF-8"));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        return new JSONObject(jsonTxt);
        
    }
    
    /** Formats a String to be displayed as a code block in Discord.
     * @param msg the message to be displayed
     * @param lang the syntax highlighting language
     * @return a String ready to be displayed in Discord
     */
    public static String toOutputBlock(String msg, String lang) {
        
        return "```" + lang + "\n" + msg + "```";
        
    }
    
 }
