package sans.discordbot.todo;
import java.util.regex.Pattern;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.RequestBuffer;

/**
 * Represents a to-do as implemented for a to-do list that can be edited in Discord.
 * 
 *
 */
public class Todo {

    private final String course;
    private final String item;
    private final String location;
    private final Date date;
    
    
    public Todo (String course, String item, String location, Date date){
        this.course = course.toUpperCase().substring(0, course.length() < 8 ? course.length() : 8);
        this.item = item;
        this.location = location;
        this.date = date;        
    }
    
    public Todo (String msg) throws IndexOutOfBoundsException { // CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00
        /*
        this.course =          msg.substring(0                     , msg.indexOf    ("|")).trim().toUpperCase();
        this.item   =          msg.substring(msg.indexOf    ("|")+1, msg.lastIndexOf("|")).trim();
        this.date   = new Date(msg.substring(msg.lastIndexOf("|")+1, msg.length()        ).trim());
        */
        String[] fields = msg.split(Pattern.quote("|")); 
        //System.out.println(fields.length);
        //System.out.println(fields[3].trim());
        
        this.course = fields[0].trim().toUpperCase().substring(0, fields[0].trim().length() < 8 ? fields[0].trim().length() : 8);
        this.item = fields[1].trim();
        this.location = fields[2].trim();
        this.date = new Date(fields[3].trim());
        
        
    }
    
    
    public static String makeTodo(String msg, IChannel channel) {
        
        String request = msg; 
        String response;
        
        if (request.startsWith("setup")) {
            return "Pin this message.";
        }
        try {

            IMessage pin = getFirstPinnedMessage(channel);
            
            if (request.startsWith("del")) {
               
                pin.edit(removeTodo(Integer.parseInt(request.substring(3).trim()), pin));
                IMessage newpin = getFirstPinnedMessage(channel);
                response = displayList(newpin);
          
            } else if (request.isEmpty()) {
                
                response = displayList(pin);
            
            } else {
                
                try {
                    
                    Todo td = new Todo(request);
                    pin.edit(td.insertTodo(pin));
                    IMessage newpin = getFirstPinnedMessage(channel);
                    return displayList(newpin);
                    
                } catch (IndexOutOfBoundsException e) {
                    
                    e.printStackTrace();
                    return "Todo not in the right format.";
                    
                }
            }
            
            return response;
            
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "Use !todo setup and pin the message first.";
        } 
    }
    
    @Override
    public String toString() {
        return this.getCourse() + " | " + this.getItem() + " | " + this.getLocation() + " | " + this.getDate().toString();
    }
    
    public String toDisplayString() {
        
        return "[" + this.getCourse() + "] " + numSpaces(8-this.getCourse().length()) + this.getItem() + numSpaces(42 - this.getItem().length() - this.getLocation().length()) + this.getLocation() + "  " + this.getDate().toDisplayString();
    }
    
    public static String numSpaces(int n) {
        StringBuilder s = new StringBuilder();
        int i = 0;
        while (i<n) {
            s.append(' ');
            i++;
        }
        return s.toString();
    }
    
    public String getCourse() {
        return this.course;
    }
    public String getItem() {
        return this.item;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public Date getDate() {
        return new Date(this.date.toString());
    }
    
    private static String displayList(IMessage pin) {
        String tds[] = pin.getContent().split("\n");
        
        if (tds.length == 1) return "";        
        
        StringBuilder newmsg = new StringBuilder();
        newmsg.append("```");
        for (int i = 1; i < tds.length; i++) {
            if (i < 10)
                newmsg.append(i + ". " + new Todo(tds[i]).toDisplayString() + "\n");
            else 
                newmsg.append(i + "." + new Todo(tds[i]).toDisplayString() + "\n");
        }
        newmsg.append("```");
        return newmsg.toString();
    }
    
    private String insertTodo(IMessage pin) {
        String tds[] = pin.getContent().split("\n");
        if (tds.length <= 1) {
            return pin.getContent() + "\n" + this.toString();
        }
        else {
            StringBuilder newpin = new StringBuilder();
            boolean added = false;
            newpin.append(tds[0]);
            for (int i = 1; i < tds.length; i++) {
                if (this.getDate().isBefore(new Todo(tds[i]).getDate()) && added == false) {
                    newpin.append("\n" + this.toString());
                    added = true;
                    newpin.append("\n" + tds[i]);
                } else {
                    newpin.append("\n" + tds[i]);
                } 
            }
            if (added == false) {
                newpin.append("\n" + this.toString());
            }

            return newpin.toString();
        }
    }
    
    public static String removeTodo(int index, IMessage pin) {
        

        String tds[] = pin.getContent().split("\n");
        if (index >= tds.length) {
            return pin.getContent();
        }

        StringBuilder newpin = new StringBuilder();
        for (int i = 0; i < tds.length; i++) {
            if (i != index) {
                newpin.append(tds[i] + "\n");
            }
        }
        return newpin.toString();
    }
    
    static IMessage getFirstPinnedMessage(IChannel channel) {
        return RequestBuffer.request(() -> {
            return channel.getPinnedMessages().get(0);
        }).get();

    }

}
