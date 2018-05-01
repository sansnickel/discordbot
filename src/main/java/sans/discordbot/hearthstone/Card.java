package sans.discordbot.hearthstone;

public class Card {

    private final String name;
    private final String cardset;
    private final String flavor;
    private final String imgurl;
    private final String artist;
    
    public Card (String name, String cardset, String flavor, String imgurl, String artist) {
        this.name = name;
        this.cardset = cardset;
        this.flavor = flavor;
        this.imgurl = imgurl;
        this.artist = artist;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getCardSet() {
        return this.cardset;
    }
    
    public String getFlavor() {
        return this.flavor;
    }
    
    public String getImgUrl() {
        return this.imgurl;
    }
    
    public String getArtist() {
        return this.artist;
    }
    
    
}
