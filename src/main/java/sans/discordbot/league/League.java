package sans.discordbot.league;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import sans.discordbot.HttpRequest;
import sans.discordbot.JsonParser;



public class League {
    
    private final static String PATCH_NO = "8.8";
    private final static String URL = "https://ddragon.leagueoflegends.com/cdn/";
    private final static String URL2 = "https://na1.api.riotgames.com/lol/";
    
    public static String getCDs(String champ) {
        
        String[] words = champ.split(" ");
        StringBuilder str = new StringBuilder();
        
        for (String word : words) {
            str.append(word.substring(0,1).toUpperCase() + word.substring(1));
        }
        
        String url = URL + PATCH_NO + ".1/data/en_US/champion/" + str.toString() + ".json";
        
        try {        
            
            InputStream is = HttpRequest.sendGet(url);
            String response = JsonParser.parseJsonCds(is, str.toString());
            is.close();
            return response;
            
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot find champ.";
        }

    }
    
    public static String getLiveGameInfo(String summoner, String key) {
             
        long id = getSummonerId(summoner, key);
        if (id == -1) return "Summoner not found.";
        
        String url = URL2 + "spectator/v3/active-games/by-summoner/" + id;
       
        try {        
            
            InputStream is = HttpRequest.sendGetLoL(url, key);
            String response = JsonParser.parseJsonLiveGame(is, key);
            is.close();
            return response;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return summoner + " is not currently in game.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error getting game info. ";
        }

    }
    
    public static long getSummonerId(String summoner, String key) {
           
        try {
            
            String url = URL2 + "summoner/v3/summoners/by-name/" + URLEncoder.encode(summoner, "UTF-8").replace("+", "%20");
            InputStream is = HttpRequest.sendGetLoL(url, key);
            long id = JsonParser.getSummonerId(is);
            is.close();
            return id;
            
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }
    
    public static String getRankAndWr(String summoner, String key) {
       
        long summonerid = getSummonerId(summoner, key);
        String url = URL2 + "league/v3/positions/by-summoner/" + summonerid;
        
        try {
            
            InputStream is = HttpRequest.sendGetLoL(url, key);
            String response = JsonParser.getRankAndWr(is);
            is.close();
            return response;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    
    

    
    
}
