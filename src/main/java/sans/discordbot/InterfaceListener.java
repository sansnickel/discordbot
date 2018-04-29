package sans.discordbot;

import java.io.IOException;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;


public class InterfaceListener implements IListener<MessageReceivedEvent> { // The event type in IListener<> can be any class which extends Event
    
    public IDiscordClient client;
    private final String key1; // x-mashape    
    private final String key2; // wolfram
    private final String key3; // oxford
    private final String key4; // league
    private final String id1;  // oxford
    
    public InterfaceListener(IDiscordClient discordClient, String key1, String key2, String id1, String key3, String key4) {
        
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
    public void handle(MessageReceivedEvent event) { // This is called when the MessageReceivedEvent is dispatched
       
        IMessage message = event.getMessage();       // get the sent message
        IChannel channel = message.getChannel();     // and the channel from which it is sent
        boolean delete = false;                      // if set to true, the query is deleted from the channel to save space
        
        try {
            
            String msg = message.getContent();      // get the content of the sent message
            if (!msg.startsWith("!")) return;        // if it does not start with !, message was not intended for the bot
            
            // past here then the message was intended for the bot
            // queries are in the form ![type] [arg]
            
            String type = msg.substring(1, msg.indexOf(" "));
            
            String arg = " "; 
            if (msg.indexOf(" ") != -1) { 
                arg = msg.substring(msg.indexOf(" ")+1, msg.length());   
            }
            
            
            switch (type) {
                case "card":
                    sendCardInfo(arg, channel);
                    break;
                case "cds":
                    sendCdInfo(arg, channel);
                    break;
                case "def":
                    sendDefInfo(arg, channel);
                    break;
                case "todo":
                    sendTodoInfo(arg, channel);
                    break;
                case "year":
                case "date":
                case "math":
                case "trivia":
                    sendNumInfo(type, arg, channel);
                    break;
                case "wolf":
                    sendWolfText(arg, channel);
                    break;
                case "livegame":
                    sendGameInfo(arg, channel);
                    break;
                case "test":
                    sendMessage("1\n2\n3\n4", channel);
                    break;
            }     
            
            if (delete) {
                message.delete();
            }
                     
        } catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
            System.err.print("Sending messages too quickly!");
            e.printStackTrace();
        } catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
            System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
            e.printStackTrace();
        } catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
            System.err.print("Missing permissions for channel!");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            sendMessage("Wrong message format.", channel);
        }
        
    }

    void sendMessage(String msg, IChannel channel) {
        
        if (msg != "") {
            RequestBuffer.request(() -> {
                IMessage sentmsg = new MessageBuilder(client).withChannel(channel).withContent(msg).build();
                if (sentmsg.getContent().startsWith("=wolf")) {
                    sentmsg.delete();
                }
            });
        }
        
    }
    
    IMessage sendMessage(EmbedObject e, IChannel channel) {

        IMessage sentmsg = RequestBuffer.request(() -> {
            return new MessageBuilder(client).withChannel(channel).withEmbed(e).build();
        }).get();

        return sentmsg;
    }
    
    void sendCardInfo(String msg, IChannel channel) {
        
        String response = Hearthstone.getCardInfo(msg, this.key1);
        String[] responses = response.split("&&");
        for (String s : responses) {
            sendMessage(s, channel);
        }
    }
    
    void sendCdInfo(String msg, IChannel channel) {
        
        String response = League.getCDs(msg);
        sendMessage(response, channel);
    }
    
    void sendTodoInfo(String msg, IChannel channel) {
        
        String response = Todo.makeTodo(msg, channel);
        sendMessage(response, channel);
    }
    
    void sendNumInfo(String type, String msg, IChannel channel) {
        
        String response = Numbers.getInfo(type, msg);
        sendMessage(response, channel);
    }
    
    void sendDefInfo(String msg, IChannel channel) {
        
        String response = Oxford.getDef(msg, this.id1, this.key3);
        sendMessage(response, channel);
    }
    
    void sendWolfText(String msg, IChannel channel) {
 
       Wolfram output = Wolfram.getText(msg, this.key2);
       EmbedBuilder b = new EmbedBuilder();
       
       b.appendField("Query", output.getQuery(), false);
       b.appendField("Response", output.getResponse(), false);       
        
       IMessage m = sendMessage(b.build(), channel);
       ReactionEmoji reaction = ReactionEmoji.of("🤔");
       m.addReaction(reaction);
       
    }
    
    void sendGameInfo(String msg, IChannel channel) {
        String response = League.getLiveGameInfo(msg, this.key4);
        sendMessage(response, channel);
    }
    
    
    
    
    
}
