package sans.discordbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;


public class InterfaceListener implements IListener<MessageReceivedEvent> { // The event type in IListener<> can be any class which extends Event
    
    public IDiscordClient client;
    private final String key;
    
    public InterfaceListener(IDiscordClient discordClient, String key) {
        this.client = discordClient;
        this.key = key;
        EventDispatcher dispatcher = discordClient.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(this); // Registers the IListener example class from above
    }
        
    //@Override
    public void handle(MessageReceivedEvent event) { // This is called when the MessageReceivedEvent is dispatched
        
        IMessage message = event.getMessage();
        IChannel channel = message.getChannel();
        boolean isCommand = true;
        try {
            // Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
            //new MessageBuilder(this.client).withChannel(channel).withContent(message.getContent()).build();
            String msg = message.getContent();
            
            if (msg.startsWith("!card")) {
                sendCardInfo(msg, channel, key);
            }
            else if (msg.startsWith("!cds")) {
                sendCDInfo(msg, channel);
            }
            
            else if (msg.startsWith("!def")) {
                //def();
            }
            
            else if (msg.startsWith("!todo")) {
                sendTodoInfo(msg, channel);
            }
            
            else if (msg.startsWith("!test")) {
                new MessageBuilder(this.client).withChannel(channel).withContent("1\n2\n3\n4").build();
            }
            else {
                isCommand = false;
            }
            if (isCommand) {
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
        RequestBuffer.request(() -> {
            new MessageBuilder(client).withChannel(channel).withContent(msg).build();
        });
    }
    
    void sendCardInfo(String msg, IChannel channel, String key) {
        String response = Hearthstone.getCardInfoAsString(msg, this.key);
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
    
    
}
