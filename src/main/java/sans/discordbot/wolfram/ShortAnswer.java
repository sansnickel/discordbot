package sans.discordbot.wolfram;

public class ShortAnswer {

    private final String query;
    private final String encodedquery;
    private final String response;
    private final String link;
    
    /** Holds a user query, Wolfram Alpha's Short Answer response, and other related information. */ 
    
    public ShortAnswer (String query, String encodedquery, String response, String link) {
        this.query = query;
        this.encodedquery = encodedquery;
        this.response = response;
        this.link = link;
    }
 
    public String getQuery () {
        return this.query;
    }
    
    public String getEncodedQuery() {
        return this.encodedquery;
    }
        
    public String getResponse () {
        return this.response;
    }
    
    public String getLink () {
        return this.link;
    }
   
    
    
}
