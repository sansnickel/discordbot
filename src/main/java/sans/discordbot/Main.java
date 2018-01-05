package sans.discordbot;

import sx.blah.discord.api.IDiscordClient;


public class Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        IDiscordClient client = Client.createClient(args[0], true); // Gets the client object (from the first example)
        @SuppressWarnings("unused")
        InterfaceListener il = new InterfaceListener(client, args[1], args[2], args[3], args[4]);

    }
}
