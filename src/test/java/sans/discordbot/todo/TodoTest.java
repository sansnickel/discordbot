package sans.discordbot.todo;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sans.discordbot.Client;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

public class TodoTest {

    
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testTodoStringStringStringDate() {
        Todo td = new Todo("cpen411", "Quiz 2", "CEME112", new Date("5/5/2018 12:00"));
        assertEquals(td.getCourse(), "CPEN411");
        assertEquals(td.getDate().getHour(), 12);
    }

    @Test
    public void testTodoString() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");
        assertEquals("CPEN400A", td.getCourse());
        assertEquals("Midterm 2", td.getItem());
        assertEquals("OSBO A", td.getLocation());
        assertEquals(new Date("11/23/2017 23:00").getDay(), td.getDate().getDay());
        
        exception.expect(IndexOutOfBoundsException.class);
        td = new Todo("CPEN400A  Midterm 2 | OSBO A | 11/23/2017 23:00");
    }

    @Test
    public void testToString() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");
        assertEquals("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00", td.toString());
    }

    @Test
    public void testToDisplayString() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");       
                                                // 42-6-9 = 27 spaces
        assertEquals("[CPEN400A] Midterm 2" + "                           " + "OSBO A  " + "11/23 23:00", td.toDisplayString());
 
        
    }

    @Test
    public void testNumSpaces() {
        String s = Todo.numSpaces(5);
        assertEquals("     ", s);
        s = Todo.numSpaces(-2);
        assertEquals("", s);
    }

    @Test
    public void testGetCourse() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");
        assertEquals("CPEN400A", td.getCourse());
    }

    @Test
    public void testGetItem() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");
        assertEquals("Midterm 2", td.getItem());
    }

    @Test
    public void testGetLocation() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");
        assertEquals("OSBO A", td.getLocation());
    }

    @Test
    public void testGetDate() {
        Todo td = new Todo("CPEN400A | Midterm 2 | OSBO A | 11/23/2017 23:00");
        assertEquals(new Date("11/23/2017 23:00").toString(), "11/23/2017 23:00");
    }

}
