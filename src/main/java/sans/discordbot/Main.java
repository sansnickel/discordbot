package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;

import sans.discordbot.league.League;
import sx.blah.discord.api.IDiscordClient;


public class Main 
{
       
    @SuppressWarnings("unused")
    public static void main( String[] args ) {
        
        System.out.println( "Hello World!" );
        IDiscordClient client = Client.createClient(args[0], true); // Gets the client object (from the first example)
        InterfaceListener il = new InterfaceListener(client, args[1], args[2], args[3], args[4], args[5]);
        ReactionListener rl = new ReactionListener(client, args[1], args[2], args[3], args[4], args[5]);

        try {
            InputStream is = HttpRequest.sendGet("https://ddragon.leagueoflegends.com/api/versions.json");
            String response = JsonParser.getPatchNo(is);
            is.close();
            League.PATCH_NO = response;
            
        } catch (IOException e) {
            League.PATCH_NO = "8.9.1";
        }
        
    }
}
