package sans.discordbot;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public class Todo {

    private final String course;
    private final String item;
    private final Date date;
    
    
    public Todo (String course, String item, Date date){
        this.course = course.toUpperCase();
        this.item = item;
        this.date = date;
    }
    
    public Todo (String msg) throws IndexOutOfBoundsException { // CPEN400A | Midterm 2 | 11/23/2017 23:00
        this.course =          msg.substring(0                     , msg.indexOf    ("|")).trim().toUpperCase();
        this.item   =          msg.substring(msg.indexOf    ("|")+1, msg.lastIndexOf("|")).trim();
        this.date   = new Date(msg.substring(msg.lastIndexOf("|")+1, msg.length()        ).trim());
    }
    
    public static String makeTodo(String msg, IChannel channel) {
        String request = msg.substring(5); // gets rid of the !todo
        String response;

        if (request.startsWith(" setup")) {
            return "Pin this message.";
        }
        try {
            IMessage pin = channel.getPinnedMessages().get(0);
            if (request.startsWith("ne")) {
                pin.edit(removeTodo(Integer.parseInt(request.substring(2).trim()), pin));
                IMessage newpin = channel.getPinnedMessages().get(0);
                response = displayList(newpin);
            } else if (request.isEmpty()) {
                response = displayList(pin);
            } else {
                try {
                    Todo td = new Todo(request);
                    pin.edit(td.insertTodo(pin));
                    IMessage newpin = channel.getPinnedMessages().get(0);
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
        return this.getCourse() + " | " + this.getItem() + " | " + this.getDate().toString();
    }
    
    public String toDisplayString() {
        
        return "[" + this.getCourse() + "] " + this.getItem() + " - " + this.getDate().toDisplayString();
    }
    
    public String getCourse() {
        return this.course;
    }
    public String getItem() {
        return this.item;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    private static String displayList(IMessage pin) {
        String tds[] = pin.getContent().split("\n");
        StringBuilder newmsg = new StringBuilder();
        newmsg.append("```");
        for (int i = 1; i < tds.length; i++) {
            newmsg.append(i + ". " + new Todo(tds[i]).toDisplayString() + "\n");
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
            //System.out.println(newpin.toString());
            return newpin.toString();
        }
    }
    
    public static String removeTodo(int index, IMessage pin) {
        String tds[] = pin.getContent().split("\n");
        StringBuilder newpin = new StringBuilder();
        for (int i = 0; i < tds.length; i++) {
            if (i != index) {
                newpin.append(tds[i] + "\n");
            }
        }
        return newpin.toString();
    }

}
