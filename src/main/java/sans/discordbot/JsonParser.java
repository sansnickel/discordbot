package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;


import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {

    
    public static String parseJson(InputStream is, String from) throws IOException {
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        //System.out.println("jsontext:" + jsonTxt);
        
        StringBuilder response = new StringBuilder();
        if (from.equals("card")) {
            JSONArray arr = new JSONArray(jsonTxt);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String cardSet = obj.getString("cardSet");
                String img = obj.getString("img");
                response.append(img + "\nSet: " + cardSet + "&&");
            }
        }
        buffer.close();
        return response.toString();  
    }
    
    public static String parseJson(InputStream is, String from, String str) throws IOException {
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        //System.out.println("jsontext:" + jsonTxt);
        StringBuilder response = new StringBuilder();

        if (from.equals("cds")) {
            JSONArray arr = new JSONObject(jsonTxt).getJSONObject("data").getJSONObject(str).getJSONArray("spells");
            for(int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String cd = obj.getString("cooldownBurn");   
                switch (i) {
                    case 0: response.append("Q: "); break;
                    case 1: response.append("W: "); break;
                    case 2: response.append("E: "); break;
                    case 3: response.append("R: "); break;
                }
                response.append(cd + "\n");
            }       
        }

        buffer.close();
        return response.toString();  
    }
}
