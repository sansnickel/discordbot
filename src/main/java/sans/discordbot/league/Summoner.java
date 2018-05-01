package sans.discordbot.league;

public class Summoner {

    private final String name;
    private final long id;
    private final long level;
    private String rank;
    private int winrate;
    
    public Summoner (String name, long id, long level) {
        this.name = name;
        this.id = id;
        this.level = level;
    }
    
    public Summoner (String name, long id, long level, String rank, int winrate) {
        this.name = name;
        this.id = id;
        this.rank = rank;
        this.level = level;
        this.winrate = winrate;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getId() {
        return this.id;
    }
    
    public long getLevel() {
        return this.level;
    }
    
    public String getRank() {
        return this.rank;
    }
    
    public int getWinRate() {
        return this.winrate;
    }
      
    public void setRank(String rank) {
        this.rank = rank;
    }
    
    public void setWinRate(int winrate) {
        this.winrate = winrate;
    }
    
    
}
