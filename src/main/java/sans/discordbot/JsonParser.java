package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;


import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {

    
    public static String parseJson(InputStream is) throws IOException {
        BufferedReader buffer = new BufferedReader (new InputStreamReader(is));
        String jsonTxt = buffer.lines().collect(Collectors.joining("\n"));
        //System.out.println("jsontext:" + jsonTxt);
        JSONArray arr = new JSONArray(jsonTxt);
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String cardSet = obj.getString("cardSet");
            String img = obj.getString("img");
            response.append(img + "\nSet: " + cardSet + "&&");
        }
        buffer.close();
        return response.toString();
     
    }

}
