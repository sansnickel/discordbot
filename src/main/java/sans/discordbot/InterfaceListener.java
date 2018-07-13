package sans.discordbot;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONException;

import sans.discordbot.hearthstone.Card;
import sans.discordbot.hearthstone.Hearthstone;
import sans.discordbot.league.Game;
import sans.discordbot.league.League;
import sans.discordbot.league.SummonerInGame;
import sans.discordbot.numbers.Numbers;
import sans.discordbot.oxford.Definition;
import sans.discordbot.oxford.Oxford;
import sans.discordbot.todo.Todo;
import sans.discordbot.wolfram.ShortAnswer;
import sans.discordbot.wolfram.Wolfram;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
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
            
            
            String type = msg.substring(1);
            String arg = ""; 
            if (msg.indexOf(" ") != -1) { 
                type = msg.substring(1, msg.indexOf(" "));
                arg = msg.substring(msg.indexOf(" ")+1, msg.length());   
            }
            
            
            switch (type) {
                case "card":
                    sendCardInfo(arg, channel); break;
                    
                case "cds":
                    sendCdInfo(arg, channel); break;
                 
                case "def":
                    sendDefInfo(arg, channel); break;
                
                case "todo":
                    sendTodoInfo(arg, channel); break;
                    
                case "year":
                case "date":
                case "math":
                case "trivia":
                    sendNumInfo(type, arg, channel); break;
                    
                case "wolf":
                    sendWolfText(arg, channel); break;
                    
                case "livegame":
                    sendGameInfo(arg, channel); break;
                    
                case "test":
                    sendMessage("1\n2\n3\n4", channel); break;
                default: break;
                    
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

    IMessage sendMessage(String msg, IChannel channel) {
        IMessage sentmsg = null;
        if (msg != "") {
            sentmsg = RequestBuffer.request(() -> {
                return new MessageBuilder(client).withChannel(channel).withContent(msg).build();
            }).get();
        }
        return sentmsg;
        
    }
    
    IMessage sendMessage(EmbedObject e, IChannel channel) {
        IMessage sentmsg = null;
        if (e != null) { 
            sentmsg = RequestBuffer.request(() -> {
                return new MessageBuilder(client).withChannel(channel).withEmbed(e).build();
            }).get();
        }
        
        return sentmsg;
    }
    
    void sendCardInfo(String msg, IChannel channel) {
        
        try {
            Card card = Hearthstone.getCardInfo(msg, this.key1);
            EmbedBuilder b = new EmbedBuilder();
            
            b.withColor(115, 135, 220);
            b.appendField("Set", card.getCardSet(), true);
            b.appendField("Artist", card.getArtist(), true); 
            b.withImage(card.getImgUrl());
            b.withFooterText(card.getFlavor());
          
            sendMessage(b.build(), channel);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            sendMessage("Card not found.", channel);
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
        
        try {
            Definition def = Oxford.getDef(msg, this.id1, this.key3);
            
            EmbedBuilder b = new EmbedBuilder();
            b.withTitle(def.getWord());
            b.withColor(115, 135, 220);            
            
            for (int i = 0; i < def.getEntries().size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < def.getEntries().get(i).getDefs().size(); j++) {
                    sb.append(j+1 + ". ");
                    sb.append(def.getEntries().get(i).getDefs().get(j));
                    sb.append("\n");
                }
                           
                b.appendField(def.getEntries().get(i).getLexCat(), sb.toString(), false);
            }       
            
            sendMessage(b.build(), channel);
        } catch (IOException e) {
            e.printStackTrace();
            sendMessage("Cannot find definition for " + msg, channel);
        }
    }
    
    void sendWolfText(String msg, IChannel channel) {
 
       try {
           ShortAnswer output = Wolfram.getText(msg, this.key2).get();
           EmbedBuilder b = new EmbedBuilder();
           
           b.appendField("Query", output.getQuery(), false);
           b.appendField("Response", output.getResponse(), false);       
            
           IMessage m = sendMessage(b.build(), channel);
           ReactionEmoji reaction = ReactionEmoji.of("🤔");
           m.addReaction(reaction);
       } catch (NoSuchElementException e) {
           sendMessage("Error encoding query.", channel);
       }
       
       
       
    }
    
    void sendGameInfo(String msg, IChannel channel) {
        try {

            Game g = League.getLiveGameInfo(msg, this.key4).get();
                
            int[] blue = {0, 0, 255};
            int[] red = {255, 0, 0};
            
            sendTeamInfo(g.getBlueTeam(), channel, blue);
            sendTeamInfo(g.getRedTeam(), channel, red);
            
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            sendMessage("Summoner " + msg + " is not in game.", channel);
        } catch (IOException e) {
            e.printStackTrace();
            sendMessage("Summoner " + msg + " not found.", channel);
        }

    }
    
    private void sendTeamInfo(List<SummonerInGame> team, IChannel channel, int[] color) {
        for (int i = 0; i < team.size(); i++) {
            
            SummonerInGame s = team.get(i);
            String champname = League.getChampionName(s.getChampion());
              
            EmbedBuilder b = new EmbedBuilder();
            b.withAuthorName(s.getName());
            b.withAuthorIcon(League.URL + League.PATCH_NO + "/img/champion/" + champname + ".png");

            b.withTitle(s.getRank() + " --- " + s.getWinRate() + "%");
            if (s.getWinRate() == -1) { 
                b.withTitle(s.getRank());
            }

            b.withColor(color[0], color[1], color[2]);
            
            StringBuilder runes = new StringBuilder();
            
            for (int j = 0; j < s.getRunes().size(); j++) {
                long rune = s.getRunes().get(j);
                runes.append(League.getRuneName(rune));
                if (j != s.getRunes().size() - 1) {
                    runes.append(" | ");
                }
            }
            b.withFooterText(runes.toString());
            
            sendMessage(b.build(), channel);
            
        }
    }
    
    
    
    
}
