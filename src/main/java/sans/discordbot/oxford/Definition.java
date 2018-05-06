package sans.discordbot.oxford;

import java.util.ArrayList;
import java.util.List;

public class Definition {
    
    private final String word;
    private final List<Entry> entries;
    

    public Definition(String word, List<Entry> entries) {
        this.word = word.trim();
        this.entries = new ArrayList<>(entries);        
        
    }
    
    public List<Entry> getEntries() {
        return new ArrayList<>(this.entries);
    }
    
    public String getWord() {
        return this.word;
    }

}
