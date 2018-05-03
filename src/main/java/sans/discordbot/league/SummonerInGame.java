package sans.discordbot.league;

import java.util.ArrayList;
import java.util.List;

public class SummonerInGame extends Summoner {
    
    private final long team;
    private final long champion;
    private final List<Long> runes;    

    public SummonerInGame(String name, long id,  long level, String rank, int winrate, long team, long champion, List<Long> runes) {
        super(name, id, level, rank, winrate);
        
        this.team = team;
        this.champion = champion;
        this.runes = new ArrayList<>(runes);
        
    }
    public long getTeam() {
        return this.team;
    }
    
    public long getChampion() {
        return this.champion;
    }

    public List<Long> getRunes() { 
        return new ArrayList<>(runes);
    }
    
}
