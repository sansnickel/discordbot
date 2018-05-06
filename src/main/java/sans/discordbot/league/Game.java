package sans.discordbot.league;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a League of Legends game (in terms of its participants). 
 * 
 *
 */
public class Game {

    private final List<SummonerInGame> blueteam;
    private final List<SummonerInGame> redteam;
    
    
    public Game (List<SummonerInGame> blueteam, List<SummonerInGame> redteam) {
        this.blueteam = new ArrayList<>(blueteam);
        this.redteam = new ArrayList<>(redteam);
        

    }
    
    public List<SummonerInGame> getBlueTeam() {
        return new ArrayList<>(blueteam);
    }
    public List<SummonerInGame> getRedTeam() {
        return new ArrayList<>(redteam);
    }
    
    
    
    
}
