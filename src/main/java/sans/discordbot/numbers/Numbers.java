package sans.discordbot.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sans.discordbot.HttpRequest;
import sans.discordbot.JsonParser;

/**
 * A collection of methods related to getting information from the Numbers API.
 * 
 *
 */
public class Numbers {
    
    private final static String URL = "http://numbersapi.com/";
    
    public static String getInfo(String type, String msg) {
           
        String url = URL + msg + "/" + type;
        
        try (InputStream is = HttpRequest.sendGet(url);) {
            
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
