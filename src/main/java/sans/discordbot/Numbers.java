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
            if (msg.startsWith("!year")) {
                InputStream is = HttpRequest.sendGet(url + msg.substring(6) + "/year");
                response = inputStreamToString(is);
            } else if (msg.startsWith("!date")) {
                InputStream is = HttpRequest.sendGet(url + msg.substring(6) + "/date");
                response = inputStreamToString(is);
            } else if (msg.startsWith("!mathfact")) {
                InputStream is = HttpRequest.sendGet(url + msg.substring(10) + "/math");
                response = inputStreamToString(is);
            } else if (msg.startsWith("!trivia")) {
                InputStream is = HttpRequest.sendGet(url + msg.substring(8) + "/trivia");
                response = inputStreamToString(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot connect or invalid input.";
        }
        return "```" + response + "```";
    }
    
      
    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        while (buffer.ready()) {
            response.append(buffer.readLine() + "\n");
        }
        buffer.close();
        return response.toString();
    }
    
}
