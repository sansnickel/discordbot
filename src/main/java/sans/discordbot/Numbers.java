package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Numbers {

    public static String getInfo(String msg) {
        String url = "http://numbersapi.com/";
        String response = null;
        try {
            InputStream is = null;
            if (msg.startsWith("!year")) {
                is = HttpRequest.sendGet(url + msg.substring(6) + "/year");
            } else if (msg.startsWith("!date")) {
                is = HttpRequest.sendGet(url + msg.substring(6) + "/date");
            } else if (msg.startsWith("!mathfact")) {
                is = HttpRequest.sendGet(url + msg.substring(10) + "/math");
            } else if (msg.startsWith("!trivia")) {
                is = HttpRequest.sendGet(url + msg.substring(8) + "/trivia");  
            }
            response = inputStreamToString(is);
            return JsonParser.formatStringToBlock(response, "ml");
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot connect or invalid input.";
        }
        
    }
    
      
    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while (buffer.ready()) {
            response.append(buffer.readLine() + "\n");
        }
        buffer.close();
        return response.toString();
    }
    
}
