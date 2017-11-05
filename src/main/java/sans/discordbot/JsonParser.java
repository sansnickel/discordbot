package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;


import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {

    public static String parseJsonWolf(InputStream is) throws IOException {
        JSONObject obj = getJsonObjFromIS(is);
        //System.out.println(obj);
        JSONArray pods = obj.getJSONObject("queryresult").getJSONArray("pods");
        
        StringBuilder response = new StringBuilder();
        
        for (int i = 0; i < pods.length(); i++) {
            JSONArray subpods = pods.getJSONObject(i).getJSONArray("subpods");
            for (int j = 0; j < subpods.length(); j++) {
                String src = subpods.getJSONObject(j).getJSONObject("img").getString("src");
                response.append(src + "&&");
            }
        }
        return response.toString();  
    }
    
    public static String parseJsonHS(InputStream is) throws IOException {
        JSONArray arr = getJsonArrFromIS(is);
        StringBuilder response = new StringBuilder();
        
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String name = obj.isNull("name") ? "" : obj.getString("name");
            String cardSet = obj.isNull("cardSet") ? "" : obj.getString("cardSet");
            String flavor = obj.isNull("flavor") ? "" : obj.getString("flavor");
            String img = obj.isNull("img") ? "" : obj.getString("img");
  
            response.append(img + "\n" + "```\n" + name + "\n" + cardSet + "\n" + flavor + "```&&");
        }
        
        return response.toString();  
    }
        
    public static String parseJsonLoL(InputStream is, String str) throws IOException {
        JSONObject obj = getJsonObjFromIS(is);
        JSONArray arr = obj.getJSONObject("data").getJSONObject(str).getJSONArray("spells");
        StringBuilder response = new StringBuilder();
        response.append("```"+ str + " Cooldowns\n");
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
        response.append("```");
        return response.toString();  
    }
    
    static JSONArray getJsonArrFromIS (InputStream is) throws IOException {
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        return new JSONArray(jsonTxt);
    }
    
    static JSONObject getJsonObjFromIS (InputStream is) throws IOException {
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        return new JSONObject(jsonTxt);
    }
}
