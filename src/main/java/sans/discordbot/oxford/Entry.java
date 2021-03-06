package sans.discordbot.oxford;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents entries for a particular part of speech for a definiton of a word. 
 * 
 *
 */
public class Entry {

    private final String lexcat;
    private final List<String> defs;
        
    public Entry (String lexcat, List<String> entries) {
        this.lexcat = lexcat;
        this.defs = new ArrayList<>(entries);
    }
        
    public String getLexCat() {
        return this.lexcat;
    }
    
    public List<String> getDefs() {
        return new ArrayList<>(this.defs);
    }

}
