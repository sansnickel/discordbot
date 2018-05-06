package sans.discordbot.numbers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import sans.discordbot.HttpRequest;

public class NumbersTest {

    @Test
    public void testGetInfo() {
        String s = Numbers.getInfo("trivia", "7");
        assertNotNull(s);
        s = Numbers.getInfo("7", "invalid");
        assertEquals("Invalid input.", s);
        
    }

    @Test
    public void testInputStreamToString() throws IOException {

        InputStream is = HttpRequest.sendGet("http://numbersapi.com/7/trivia");
        String s = Numbers.inputStreamToString(is);
        assertNotNull(s);
            

        
    }

}
