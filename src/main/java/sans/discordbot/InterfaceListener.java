package sans.discordbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
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
    private final String id1;  // oxford
    private final String key3; // oxford
    
    public InterfaceListener(IDiscordClient discordClient, String key1, String key2, String id1, String key3) {
        this.client = discordClient;
        this.key1 = key1;
        this.key2 = key2;
        this.id1 = id1;
        this.key3 = key3;
        EventDispatcher dispatcher = discordClient.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(this); // Registers the IListener example class from above
    }
        
    //@Override
    public void handle(MessageReceivedEvent event) { // This is called when the MessageReceivedEvent is dispatched
        
        IMessage message = event.getMessage();
        IChannel channel = message.getChannel();
        boolean delete = false;
        try {
            // Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
            //new MessageBuilder(this.client).withChannel(channel).withContent(message.getContent()).build();
            String msg = message.getContent();
            if (msg.startsWith("!card")) {
                sendCardInfo(msg, channel);
            }
            else if (msg.startsWith("!cds")) {
                sendCDInfo(msg, channel);
            }
            else if (msg.startsWith("!def")) {
                sendDefInfo(msg, channel);
            }
            
            else if (msg.startsWith("!todo")) {
                sendTodoInfo(msg, channel);
            }
            else if (msg.startsWith("!year") || msg.startsWith("!date") || msg.startsWith("!mathfact") || msg.startsWith("!trivia")) {
                sendNumInfo(msg, channel);
                delete = false;
            }
            else if (msg.startsWith("!wolf")) {
                sendWolfInfo(msg, channel);
            }
            
            else if (msg.startsWith("!test")) {
                sendMessage("1\n2\n3\n4", channel);
            }
            else {
                delete = false;
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
    
    void sendMessage(EmbedObject e, IChannel channel) {
        if (e != null) {
            RequestBuffer.request(() -> {
                new MessageBuilder(client).withChannel(channel).withContent("").withEmbed(e).build();
            });
        }
    }
    
    void sendCardInfo(String msg, IChannel channel) {
        String response = Hearthstone.getCardInfoAsString(msg, this.key1);
        String[] responses = response.split("&&");
        for (String s : responses) {
            sendMessage(s, channel);
        }
    }
    void sendCDInfo(String msg, IChannel channel) {
        String response = League.getCDs(msg);
        sendMessage(response, channel);
    }
    
    void sendTodoInfo(String msg, IChannel channel) {
        String response = Todo.makeTodo(msg, channel);
        sendMessage(response, channel);
    }
    void sendNumInfo(String msg, IChannel channel) {
        String response = Numbers.getInfo(msg);
        sendMessage(response, channel);
    }
    void sendDefInfo(String msg, IChannel channel) {
        String response = Oxford.getDef(msg, this.id1, this.key3);
        sendMessage(response, channel);
    }
    void sendWolfInfo(String msg, IChannel channel) {
       /*String response = Wolfram.getWolfInfo(msg, this.key2);
       String urls[] = response.split("&&");
       for (String url : urls) {
           EmbedObject eo = new EmbedBuilder().withImage(url).build();
           sendMessage(eo, channel);
       }*/
       String response = Wolfram.getSimpleInfo(msg, this.key2);
       if (response.startsWith("=wolf")) {
           sendMessage(response, channel);
       }
       else {
           sendMessage(JsonParser.formatStringToBlock(response, "ml"), channel);    
       }
       
    }
}
