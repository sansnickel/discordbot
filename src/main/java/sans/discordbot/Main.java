package sans.discordbot;

import sx.blah.discord.api.IDiscordClient;


public class Main 
{
       
    @SuppressWarnings("unused")
    public static void main( String[] args ) {
        
        System.out.println( "Hello World!" );
        IDiscordClient client = Client.createClient(args[0], true); // Gets the client object (from the first example)
        InterfaceListener il = new InterfaceListener(client, args[1], args[2], args[3], args[4], args[5]);
        ReactionListener rl = new ReactionListener(client, args[1], args[2], args[3], args[4], args[5]);

    }
}
