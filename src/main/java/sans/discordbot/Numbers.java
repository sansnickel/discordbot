package sans.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Numbers {
    
    private final static String URL = "http://numbersapi.com/";
    
    public static String getInfo(String type, String msg) {
           
        try {

            InputStream is = HttpRequest.sendGet(URL + msg + "/" + type);
            String response = inputStreamToString(is);
            return JsonParser.toOutputBlock(response, "ml");

        } catch (IOException e) {
           
            e.printStackTrace();
            return "Invalid input.";
            
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
