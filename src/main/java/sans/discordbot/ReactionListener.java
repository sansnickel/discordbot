package sans.discordbot;

import java.io.IOException;
import java.io.InputStream;

import sans.discordbot.wolfram.Wolfram;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RequestBuffer;

public class ReactionListener implements IListener<ReactionAddEvent> {
    
    public IDiscordClient client;
    private final String key1; // x-mashape    
    private final String key2; // wolfram
    private final String key3; // oxford
    private final String key4; // league
    private final String id1;  // oxford
    
    public ReactionListener(IDiscordClient discordClient, String key1, String key2, String id1, String key3, String key4) {
        
        this.client = discordClient;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.id1 = id1;
        
        EventDispatcher dispatcher = discordClient.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(this);                          // Registers the IListener example class from above
    
    }

    @Override
    public void handle(ReactionAddEvent event) {
        
        
        IChannel channel = event.getChannel();         
        IMessage m = event.getMessage();
        
        String query = m.getEmbeds().get(0).getEmbedFields().get(0).getValue();
        
        if (m.getReactionByUnicode("🤔").getUsers().size() == 2) {  // there seems to be an off by one error with the given getCount() function
                                                           // getUsers().size() 
            sendWolfImage(query, channel);
        }
        
        
        
    }
    
    void sendWolfImage(String msg, IChannel channel) {
        
       try {
           
            sendMessage(Wolfram.getImage(msg, this.key2), "response.jpg", channel);
            
        } catch (IOException e) {
            
            e.printStackTrace();
            sendMessage("No response found.", channel);
        }

    }

    IMessage sendMessage(InputStream is, String name, IChannel channel) {

        IMessage m = RequestBuffer.request(() -> {
            return new MessageBuilder(client).withChannel(channel).withFile(is, name).build();
        }).get();
        
        try {
            is.close();
            return m;
        } catch (IOException e) {
            e.printStackTrace();
            return m;
        }        
    } 
    
    IMessage sendMessage(String msg, IChannel channel) {
        
       
        IMessage sentmsg = RequestBuffer.request(() -> {
            return new MessageBuilder(client).withChannel(channel).withContent(msg).build();
          
        }).get();
        return sentmsg;
        
    }
    

}
