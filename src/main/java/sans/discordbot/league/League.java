package sans.discordbot.league;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Optional;

import sans.discordbot.HttpRequest;
import sans.discordbot.JsonParser;


/**
 * A collection of methods for getting information related to League.
 * 
 *
 */
public class League {
      
    public static String PATCH_NO;
    public final static String URL = "https://ddragon.leagueoflegends.com/cdn/";
    private final static String URL2 = "https://na1.api.riotgames.com/lol/";
    
    /*public final static String CHAMPIONS = "{\r\n" + 
            "    \"type\": \"champion\",\r\n" + 
            "    \"version\": \"8.8.2\",\r\n" + 
            "    \"data\": {\r\n" + 
            "        \"1\": {\r\n" + 
            "            \"title\": \"the Dark Child\",\r\n" + 
            "            \"id\": 1,\r\n" + 
            "            \"key\": \"Annie\",\r\n" + 
            "            \"name\": \"Annie\"\r\n" + 
            "        },\r\n" + 
            "        \"2\": {\r\n" + 
            "            \"title\": \"the Berserker\",\r\n" + 
            "            \"id\": 2,\r\n" + 
            "            \"key\": \"Olaf\",\r\n" + 
            "            \"name\": \"Olaf\"\r\n" + 
            "        },\r\n" + 
            "        \"3\": {\r\n" + 
            "            \"title\": \"the Colossus\",\r\n" + 
            "            \"id\": 3,\r\n" + 
            "            \"key\": \"Galio\",\r\n" + 
            "            \"name\": \"Galio\"\r\n" + 
            "        },\r\n" + 
            "        \"4\": {\r\n" + 
            "            \"title\": \"the Card Master\",\r\n" + 
            "            \"id\": 4,\r\n" + 
            "            \"key\": \"TwistedFate\",\r\n" + 
            "            \"name\": \"Twisted Fate\"\r\n" + 
            "        },\r\n" + 
            "        \"5\": {\r\n" + 
            "            \"title\": \"the Seneschal of Demacia\",\r\n" + 
            "            \"id\": 5,\r\n" + 
            "            \"key\": \"XinZhao\",\r\n" + 
            "            \"name\": \"Xin Zhao\"\r\n" + 
            "        },\r\n" + 
            "        \"6\": {\r\n" + 
            "            \"title\": \"the Dreadnought\",\r\n" + 
            "            \"id\": 6,\r\n" + 
            "            \"key\": \"Urgot\",\r\n" + 
            "            \"name\": \"Urgot\"\r\n" + 
            "        },\r\n" + 
            "        \"7\": {\r\n" + 
            "            \"title\": \"the Deceiver\",\r\n" + 
            "            \"id\": 7,\r\n" + 
            "            \"key\": \"Leblanc\",\r\n" + 
            "            \"name\": \"LeBlanc\"\r\n" + 
            "        },\r\n" + 
            "        \"8\": {\r\n" + 
            "            \"title\": \"the Crimson Reaper\",\r\n" + 
            "            \"id\": 8,\r\n" + 
            "            \"key\": \"Vladimir\",\r\n" + 
            "            \"name\": \"Vladimir\"\r\n" + 
            "        },\r\n" + 
            "        \"9\": {\r\n" + 
            "            \"title\": \"the Harbinger of Doom\",\r\n" + 
            "            \"id\": 9,\r\n" + 
            "            \"key\": \"Fiddlesticks\",\r\n" + 
            "            \"name\": \"Fiddlesticks\"\r\n" + 
            "        },\r\n" + 
            "        \"10\": {\r\n" + 
            "            \"title\": \"The Judicator\",\r\n" + 
            "            \"id\": 10,\r\n" + 
            "            \"key\": \"Kayle\",\r\n" + 
            "            \"name\": \"Kayle\"\r\n" + 
            "        },\r\n" + 
            "        \"11\": {\r\n" + 
            "            \"title\": \"the Wuju Bladesman\",\r\n" + 
            "            \"id\": 11,\r\n" + 
            "            \"key\": \"MasterYi\",\r\n" + 
            "            \"name\": \"Master Yi\"\r\n" + 
            "        },\r\n" + 
            "        \"12\": {\r\n" + 
            "            \"title\": \"the Minotaur\",\r\n" + 
            "            \"id\": 12,\r\n" + 
            "            \"key\": \"Alistar\",\r\n" + 
            "            \"name\": \"Alistar\"\r\n" + 
            "        },\r\n" + 
            "        \"13\": {\r\n" + 
            "            \"title\": \"the Rune Mage\",\r\n" + 
            "            \"id\": 13,\r\n" + 
            "            \"key\": \"Ryze\",\r\n" + 
            "            \"name\": \"Ryze\"\r\n" + 
            "        },\r\n" + 
            "        \"14\": {\r\n" + 
            "            \"title\": \"The Undead Juggernaut\",\r\n" + 
            "            \"id\": 14,\r\n" + 
            "            \"key\": \"Sion\",\r\n" + 
            "            \"name\": \"Sion\"\r\n" + 
            "        },\r\n" + 
            "        \"15\": {\r\n" + 
            "            \"title\": \"the Battle Mistress\",\r\n" + 
            "            \"id\": 15,\r\n" + 
            "            \"key\": \"Sivir\",\r\n" + 
            "            \"name\": \"Sivir\"\r\n" + 
            "        },\r\n" + 
            "        \"16\": {\r\n" + 
            "            \"title\": \"the Starchild\",\r\n" + 
            "            \"id\": 16,\r\n" + 
            "            \"key\": \"Soraka\",\r\n" + 
            "            \"name\": \"Soraka\"\r\n" + 
            "        },\r\n" + 
            "        \"17\": {\r\n" + 
            "            \"title\": \"the Swift Scout\",\r\n" + 
            "            \"id\": 17,\r\n" + 
            "            \"key\": \"Teemo\",\r\n" + 
            "            \"name\": \"Teemo\"\r\n" + 
            "        },\r\n" + 
            "        \"18\": {\r\n" + 
            "            \"title\": \"the Yordle Gunner\",\r\n" + 
            "            \"id\": 18,\r\n" + 
            "            \"key\": \"Tristana\",\r\n" + 
            "            \"name\": \"Tristana\"\r\n" + 
            "        },\r\n" + 
            "        \"19\": {\r\n" + 
            "            \"title\": \"the Uncaged Wrath of Zaun\",\r\n" + 
            "            \"id\": 19,\r\n" + 
            "            \"key\": \"Warwick\",\r\n" + 
            "            \"name\": \"Warwick\"\r\n" + 
            "        },\r\n" + 
            "        \"20\": {\r\n" + 
            "            \"title\": \"the Yeti Rider\",\r\n" + 
            "            \"id\": 20,\r\n" + 
            "            \"key\": \"Nunu\",\r\n" + 
            "            \"name\": \"Nunu\"\r\n" + 
            "        },\r\n" + 
            "        \"21\": {\r\n" + 
            "            \"title\": \"the Bounty Hunter\",\r\n" + 
            "            \"id\": 21,\r\n" + 
            "            \"key\": \"MissFortune\",\r\n" + 
            "            \"name\": \"Miss Fortune\"\r\n" + 
            "        },\r\n" + 
            "        \"22\": {\r\n" + 
            "            \"title\": \"the Frost Archer\",\r\n" + 
            "            \"id\": 22,\r\n" + 
            "            \"key\": \"Ashe\",\r\n" + 
            "            \"name\": \"Ashe\"\r\n" + 
            "        },\r\n" + 
            "        \"23\": {\r\n" + 
            "            \"title\": \"the Barbarian King\",\r\n" + 
            "            \"id\": 23,\r\n" + 
            "            \"key\": \"Tryndamere\",\r\n" + 
            "            \"name\": \"Tryndamere\"\r\n" + 
            "        },\r\n" + 
            "        \"24\": {\r\n" + 
            "            \"title\": \"Grandmaster at Arms\",\r\n" + 
            "            \"id\": 24,\r\n" + 
            "            \"key\": \"Jax\",\r\n" + 
            "            \"name\": \"Jax\"\r\n" + 
            "        },\r\n" + 
            "        \"25\": {\r\n" + 
            "            \"title\": \"Fallen Angel\",\r\n" + 
            "            \"id\": 25,\r\n" + 
            "            \"key\": \"Morgana\",\r\n" + 
            "            \"name\": \"Morgana\"\r\n" + 
            "        },\r\n" + 
            "        \"26\": {\r\n" + 
            "            \"title\": \"the Chronokeeper\",\r\n" + 
            "            \"id\": 26,\r\n" + 
            "            \"key\": \"Zilean\",\r\n" + 
            "            \"name\": \"Zilean\"\r\n" + 
            "        },\r\n" + 
            "        \"27\": {\r\n" + 
            "            \"title\": \"the Mad Chemist\",\r\n" + 
            "            \"id\": 27,\r\n" + 
            "            \"key\": \"Singed\",\r\n" + 
            "            \"name\": \"Singed\"\r\n" + 
            "        },\r\n" + 
            "        \"28\": {\r\n" + 
            "            \"title\": \"Agony's Embrace\",\r\n" + 
            "            \"id\": 28,\r\n" + 
            "            \"key\": \"Evelynn\",\r\n" + 
            "            \"name\": \"Evelynn\"\r\n" + 
            "        },\r\n" + 
            "        \"29\": {\r\n" + 
            "            \"title\": \"the Plague Rat\",\r\n" + 
            "            \"id\": 29,\r\n" + 
            "            \"key\": \"Twitch\",\r\n" + 
            "            \"name\": \"Twitch\"\r\n" + 
            "        },\r\n" + 
            "        \"30\": {\r\n" + 
            "            \"title\": \"the Deathsinger\",\r\n" + 
            "            \"id\": 30,\r\n" + 
            "            \"key\": \"Karthus\",\r\n" + 
            "            \"name\": \"Karthus\"\r\n" + 
            "        },\r\n" + 
            "        \"31\": {\r\n" + 
            "            \"title\": \"the Terror of the Void\",\r\n" + 
            "            \"id\": 31,\r\n" + 
            "            \"key\": \"Chogath\",\r\n" + 
            "            \"name\": \"Cho'Gath\"\r\n" + 
            "        },\r\n" + 
            "        \"32\": {\r\n" + 
            "            \"title\": \"the Sad Mummy\",\r\n" + 
            "            \"id\": 32,\r\n" + 
            "            \"key\": \"Amumu\",\r\n" + 
            "            \"name\": \"Amumu\"\r\n" + 
            "        },\r\n" + 
            "        \"33\": {\r\n" + 
            "            \"title\": \"the Armordillo\",\r\n" + 
            "            \"id\": 33,\r\n" + 
            "            \"key\": \"Rammus\",\r\n" + 
            "            \"name\": \"Rammus\"\r\n" + 
            "        },\r\n" + 
            "        \"34\": {\r\n" + 
            "            \"title\": \"the Cryophoenix\",\r\n" + 
            "            \"id\": 34,\r\n" + 
            "            \"key\": \"Anivia\",\r\n" + 
            "            \"name\": \"Anivia\"\r\n" + 
            "        },\r\n" + 
            "        \"35\": {\r\n" + 
            "            \"title\": \"the Demon Jester\",\r\n" + 
            "            \"id\": 35,\r\n" + 
            "            \"key\": \"Shaco\",\r\n" + 
            "            \"name\": \"Shaco\"\r\n" + 
            "        },\r\n" + 
            "        \"36\": {\r\n" + 
            "            \"title\": \"the Madman of Zaun\",\r\n" + 
            "            \"id\": 36,\r\n" + 
            "            \"key\": \"DrMundo\",\r\n" + 
            "            \"name\": \"Dr. Mundo\"\r\n" + 
            "        },\r\n" + 
            "        \"37\": {\r\n" + 
            "            \"title\": \"Maven of the Strings\",\r\n" + 
            "            \"id\": 37,\r\n" + 
            "            \"key\": \"Sona\",\r\n" + 
            "            \"name\": \"Sona\"\r\n" + 
            "        },\r\n" + 
            "        \"38\": {\r\n" + 
            "            \"title\": \"the Void Walker\",\r\n" + 
            "            \"id\": 38,\r\n" + 
            "            \"key\": \"Kassadin\",\r\n" + 
            "            \"name\": \"Kassadin\"\r\n" + 
            "        },\r\n" + 
            "        \"39\": {\r\n" + 
            "            \"title\": \"the Blade Dancer\",\r\n" + 
            "            \"id\": 39,\r\n" + 
            "            \"key\": \"Irelia\",\r\n" + 
            "            \"name\": \"Irelia\"\r\n" + 
            "        },\r\n" + 
            "        \"40\": {\r\n" + 
            "            \"title\": \"the Storm's Fury\",\r\n" + 
            "            \"id\": 40,\r\n" + 
            "            \"key\": \"Janna\",\r\n" + 
            "            \"name\": \"Janna\"\r\n" + 
            "        },\r\n" + 
            "        \"41\": {\r\n" + 
            "            \"title\": \"the Saltwater Scourge\",\r\n" + 
            "            \"id\": 41,\r\n" + 
            "            \"key\": \"Gangplank\",\r\n" + 
            "            \"name\": \"Gangplank\"\r\n" + 
            "        },\r\n" + 
            "        \"42\": {\r\n" + 
            "            \"title\": \"the Daring Bombardier\",\r\n" + 
            "            \"id\": 42,\r\n" + 
            "            \"key\": \"Corki\",\r\n" + 
            "            \"name\": \"Corki\"\r\n" + 
            "        },\r\n" + 
            "        \"43\": {\r\n" + 
            "            \"title\": \"the Enlightened One\",\r\n" + 
            "            \"id\": 43,\r\n" + 
            "            \"key\": \"Karma\",\r\n" + 
            "            \"name\": \"Karma\"\r\n" + 
            "        },\r\n" + 
            "        \"44\": {\r\n" + 
            "            \"title\": \"the Shield of Valoran\",\r\n" + 
            "            \"id\": 44,\r\n" + 
            "            \"key\": \"Taric\",\r\n" + 
            "            \"name\": \"Taric\"\r\n" + 
            "        },\r\n" + 
            "        \"45\": {\r\n" + 
            "            \"title\": \"the Tiny Master of Evil\",\r\n" + 
            "            \"id\": 45,\r\n" + 
            "            \"key\": \"Veigar\",\r\n" + 
            "            \"name\": \"Veigar\"\r\n" + 
            "        },\r\n" + 
            "        \"48\": {\r\n" + 
            "            \"title\": \"the Troll King\",\r\n" + 
            "            \"id\": 48,\r\n" + 
            "            \"key\": \"Trundle\",\r\n" + 
            "            \"name\": \"Trundle\"\r\n" + 
            "        },\r\n" + 
            "        \"50\": {\r\n" + 
            "            \"title\": \"the Noxian Grand General\",\r\n" + 
            "            \"id\": 50,\r\n" + 
            "            \"key\": \"Swain\",\r\n" + 
            "            \"name\": \"Swain\"\r\n" + 
            "        },\r\n" + 
            "        \"51\": {\r\n" + 
            "            \"title\": \"the Sheriff of Piltover\",\r\n" + 
            "            \"id\": 51,\r\n" + 
            "            \"key\": \"Caitlyn\",\r\n" + 
            "            \"name\": \"Caitlyn\"\r\n" + 
            "        },\r\n" + 
            "        \"53\": {\r\n" + 
            "            \"title\": \"the Great Steam Golem\",\r\n" + 
            "            \"id\": 53,\r\n" + 
            "            \"key\": \"Blitzcrank\",\r\n" + 
            "            \"name\": \"Blitzcrank\"\r\n" + 
            "        },\r\n" + 
            "        \"54\": {\r\n" + 
            "            \"title\": \"Shard of the Monolith\",\r\n" + 
            "            \"id\": 54,\r\n" + 
            "            \"key\": \"Malphite\",\r\n" + 
            "            \"name\": \"Malphite\"\r\n" + 
            "        },\r\n" + 
            "        \"55\": {\r\n" + 
            "            \"title\": \"the Sinister Blade\",\r\n" + 
            "            \"id\": 55,\r\n" + 
            "            \"key\": \"Katarina\",\r\n" + 
            "            \"name\": \"Katarina\"\r\n" + 
            "        },\r\n" + 
            "        \"56\": {\r\n" + 
            "            \"title\": \"the Eternal Nightmare\",\r\n" + 
            "            \"id\": 56,\r\n" + 
            "            \"key\": \"Nocturne\",\r\n" + 
            "            \"name\": \"Nocturne\"\r\n" + 
            "        },\r\n" + 
            "        \"57\": {\r\n" + 
            "            \"title\": \"the Twisted Treant\",\r\n" + 
            "            \"id\": 57,\r\n" + 
            "            \"key\": \"Maokai\",\r\n" + 
            "            \"name\": \"Maokai\"\r\n" + 
            "        },\r\n" + 
            "        \"58\": {\r\n" + 
            "            \"title\": \"the Butcher of the Sands\",\r\n" + 
            "            \"id\": 58,\r\n" + 
            "            \"key\": \"Renekton\",\r\n" + 
            "            \"name\": \"Renekton\"\r\n" + 
            "        },\r\n" + 
            "        \"59\": {\r\n" + 
            "            \"title\": \"the Exemplar of Demacia\",\r\n" + 
            "            \"id\": 59,\r\n" + 
            "            \"key\": \"JarvanIV\",\r\n" + 
            "            \"name\": \"Jarvan IV\"\r\n" + 
            "        },\r\n" + 
            "        \"60\": {\r\n" + 
            "            \"title\": \"the Spider Queen\",\r\n" + 
            "            \"id\": 60,\r\n" + 
            "            \"key\": \"Elise\",\r\n" + 
            "            \"name\": \"Elise\"\r\n" + 
            "        },\r\n" + 
            "        \"61\": {\r\n" + 
            "            \"title\": \"the Lady of Clockwork\",\r\n" + 
            "            \"id\": 61,\r\n" + 
            "            \"key\": \"Orianna\",\r\n" + 
            "            \"name\": \"Orianna\"\r\n" + 
            "        },\r\n" + 
            "        \"62\": {\r\n" + 
            "            \"title\": \"the Monkey King\",\r\n" + 
            "            \"id\": 62,\r\n" + 
            "            \"key\": \"MonkeyKing\",\r\n" + 
            "            \"name\": \"Wukong\"\r\n" + 
            "        },\r\n" + 
            "        \"63\": {\r\n" + 
            "            \"title\": \"the Burning Vengeance\",\r\n" + 
            "            \"id\": 63,\r\n" + 
            "            \"key\": \"Brand\",\r\n" + 
            "            \"name\": \"Brand\"\r\n" + 
            "        },\r\n" + 
            "        \"64\": {\r\n" + 
            "            \"title\": \"the Blind Monk\",\r\n" + 
            "            \"id\": 64,\r\n" + 
            "            \"key\": \"LeeSin\",\r\n" + 
            "            \"name\": \"Lee Sin\"\r\n" + 
            "        },\r\n" + 
            "        \"67\": {\r\n" + 
            "            \"title\": \"the Night Hunter\",\r\n" + 
            "            \"id\": 67,\r\n" + 
            "            \"key\": \"Vayne\",\r\n" + 
            "            \"name\": \"Vayne\"\r\n" + 
            "        },\r\n" + 
            "        \"68\": {\r\n" + 
            "            \"title\": \"the Mechanized Menace\",\r\n" + 
            "            \"id\": 68,\r\n" + 
            "            \"key\": \"Rumble\",\r\n" + 
            "            \"name\": \"Rumble\"\r\n" + 
            "        },\r\n" + 
            "        \"69\": {\r\n" + 
            "            \"title\": \"the Serpent's Embrace\",\r\n" + 
            "            \"id\": 69,\r\n" + 
            "            \"key\": \"Cassiopeia\",\r\n" + 
            "            \"name\": \"Cassiopeia\"\r\n" + 
            "        },\r\n" + 
            "        \"72\": {\r\n" + 
            "            \"title\": \"the Crystal Vanguard\",\r\n" + 
            "            \"id\": 72,\r\n" + 
            "            \"key\": \"Skarner\",\r\n" + 
            "            \"name\": \"Skarner\"\r\n" + 
            "        },\r\n" + 
            "        \"74\": {\r\n" + 
            "            \"title\": \"the Revered Inventor\",\r\n" + 
            "            \"id\": 74,\r\n" + 
            "            \"key\": \"Heimerdinger\",\r\n" + 
            "            \"name\": \"Heimerdinger\"\r\n" + 
            "        },\r\n" + 
            "        \"75\": {\r\n" + 
            "            \"title\": \"the Curator of the Sands\",\r\n" + 
            "            \"id\": 75,\r\n" + 
            "            \"key\": \"Nasus\",\r\n" + 
            "            \"name\": \"Nasus\"\r\n" + 
            "        },\r\n" + 
            "        \"76\": {\r\n" + 
            "            \"title\": \"the Bestial Huntress\",\r\n" + 
            "            \"id\": 76,\r\n" + 
            "            \"key\": \"Nidalee\",\r\n" + 
            "            \"name\": \"Nidalee\"\r\n" + 
            "        },\r\n" + 
            "        \"77\": {\r\n" + 
            "            \"title\": \"the Spirit Walker\",\r\n" + 
            "            \"id\": 77,\r\n" + 
            "            \"key\": \"Udyr\",\r\n" + 
            "            \"name\": \"Udyr\"\r\n" + 
            "        },\r\n" + 
            "        \"78\": {\r\n" + 
            "            \"title\": \"Keeper of the Hammer\",\r\n" + 
            "            \"id\": 78,\r\n" + 
            "            \"key\": \"Poppy\",\r\n" + 
            "            \"name\": \"Poppy\"\r\n" + 
            "        },\r\n" + 
            "        \"79\": {\r\n" + 
            "            \"title\": \"the Rabble Rouser\",\r\n" + 
            "            \"id\": 79,\r\n" + 
            "            \"key\": \"Gragas\",\r\n" + 
            "            \"name\": \"Gragas\"\r\n" + 
            "        },\r\n" + 
            "        \"80\": {\r\n" + 
            "            \"title\": \"the Artisan of War\",\r\n" + 
            "            \"id\": 80,\r\n" + 
            "            \"key\": \"Pantheon\",\r\n" + 
            "            \"name\": \"Pantheon\"\r\n" + 
            "        },\r\n" + 
            "        \"81\": {\r\n" + 
            "            \"title\": \"the Prodigal Explorer\",\r\n" + 
            "            \"id\": 81,\r\n" + 
            "            \"key\": \"Ezreal\",\r\n" + 
            "            \"name\": \"Ezreal\"\r\n" + 
            "        },\r\n" + 
            "        \"82\": {\r\n" + 
            "            \"title\": \"the Iron Revenant\",\r\n" + 
            "            \"id\": 82,\r\n" + 
            "            \"key\": \"Mordekaiser\",\r\n" + 
            "            \"name\": \"Mordekaiser\"\r\n" + 
            "        },\r\n" + 
            "        \"83\": {\r\n" + 
            "            \"title\": \"Shepherd of Souls\",\r\n" + 
            "            \"id\": 83,\r\n" + 
            "            \"key\": \"Yorick\",\r\n" + 
            "            \"name\": \"Yorick\"\r\n" + 
            "        },\r\n" + 
            "        \"84\": {\r\n" + 
            "            \"title\": \"the Fist of Shadow\",\r\n" + 
            "            \"id\": 84,\r\n" + 
            "            \"key\": \"Akali\",\r\n" + 
            "            \"name\": \"Akali\"\r\n" + 
            "        },\r\n" + 
            "        \"85\": {\r\n" + 
            "            \"title\": \"the Heart of the Tempest\",\r\n" + 
            "            \"id\": 85,\r\n" + 
            "            \"key\": \"Kennen\",\r\n" + 
            "            \"name\": \"Kennen\"\r\n" + 
            "        },\r\n" + 
            "        \"86\": {\r\n" + 
            "            \"title\": \"The Might of Demacia\",\r\n" + 
            "            \"id\": 86,\r\n" + 
            "            \"key\": \"Garen\",\r\n" + 
            "            \"name\": \"Garen\"\r\n" + 
            "        },\r\n" + 
            "        \"89\": {\r\n" + 
            "            \"title\": \"the Radiant Dawn\",\r\n" + 
            "            \"id\": 89,\r\n" + 
            "            \"key\": \"Leona\",\r\n" + 
            "            \"name\": \"Leona\"\r\n" + 
            "        },\r\n" + 
            "        \"90\": {\r\n" + 
            "            \"title\": \"the Prophet of the Void\",\r\n" + 
            "            \"id\": 90,\r\n" + 
            "            \"key\": \"Malzahar\",\r\n" + 
            "            \"name\": \"Malzahar\"\r\n" + 
            "        },\r\n" + 
            "        \"91\": {\r\n" + 
            "            \"title\": \"the Blade's Shadow\",\r\n" + 
            "            \"id\": 91,\r\n" + 
            "            \"key\": \"Talon\",\r\n" + 
            "            \"name\": \"Talon\"\r\n" + 
            "        },\r\n" + 
            "        \"92\": {\r\n" + 
            "            \"title\": \"the Exile\",\r\n" + 
            "            \"id\": 92,\r\n" + 
            "            \"key\": \"Riven\",\r\n" + 
            "            \"name\": \"Riven\"\r\n" + 
            "        },\r\n" + 
            "        \"96\": {\r\n" + 
            "            \"title\": \"the Mouth of the Abyss\",\r\n" + 
            "            \"id\": 96,\r\n" + 
            "            \"key\": \"KogMaw\",\r\n" + 
            "            \"name\": \"Kog'Maw\"\r\n" + 
            "        },\r\n" + 
            "        \"98\": {\r\n" + 
            "            \"title\": \"the Eye of Twilight\",\r\n" + 
            "            \"id\": 98,\r\n" + 
            "            \"key\": \"Shen\",\r\n" + 
            "            \"name\": \"Shen\"\r\n" + 
            "        },\r\n" + 
            "        \"99\": {\r\n" + 
            "            \"title\": \"the Lady of Luminosity\",\r\n" + 
            "            \"id\": 99,\r\n" + 
            "            \"key\": \"Lux\",\r\n" + 
            "            \"name\": \"Lux\"\r\n" + 
            "        },\r\n" + 
            "        \"101\": {\r\n" + 
            "            \"title\": \"the Magus Ascendant\",\r\n" + 
            "            \"id\": 101,\r\n" + 
            "            \"key\": \"Xerath\",\r\n" + 
            "            \"name\": \"Xerath\"\r\n" + 
            "        },\r\n" + 
            "        \"102\": {\r\n" + 
            "            \"title\": \"the Half-Dragon\",\r\n" + 
            "            \"id\": 102,\r\n" + 
            "            \"key\": \"Shyvana\",\r\n" + 
            "            \"name\": \"Shyvana\"\r\n" + 
            "        },\r\n" + 
            "        \"103\": {\r\n" + 
            "            \"title\": \"the Nine-Tailed Fox\",\r\n" + 
            "            \"id\": 103,\r\n" + 
            "            \"key\": \"Ahri\",\r\n" + 
            "            \"name\": \"Ahri\"\r\n" + 
            "        },\r\n" + 
            "        \"104\": {\r\n" + 
            "            \"title\": \"the Outlaw\",\r\n" + 
            "            \"id\": 104,\r\n" + 
            "            \"key\": \"Graves\",\r\n" + 
            "            \"name\": \"Graves\"\r\n" + 
            "        },\r\n" + 
            "        \"105\": {\r\n" + 
            "            \"title\": \"the Tidal Trickster\",\r\n" + 
            "            \"id\": 105,\r\n" + 
            "            \"key\": \"Fizz\",\r\n" + 
            "            \"name\": \"Fizz\"\r\n" + 
            "        },\r\n" + 
            "        \"106\": {\r\n" + 
            "            \"title\": \"the Thunder's Roar\",\r\n" + 
            "            \"id\": 106,\r\n" + 
            "            \"key\": \"Volibear\",\r\n" + 
            "            \"name\": \"Volibear\"\r\n" + 
            "        },\r\n" + 
            "        \"107\": {\r\n" + 
            "            \"title\": \"the Pridestalker\",\r\n" + 
            "            \"id\": 107,\r\n" + 
            "            \"key\": \"Rengar\",\r\n" + 
            "            \"name\": \"Rengar\"\r\n" + 
            "        },\r\n" + 
            "        \"110\": {\r\n" + 
            "            \"title\": \"the Arrow of Retribution\",\r\n" + 
            "            \"id\": 110,\r\n" + 
            "            \"key\": \"Varus\",\r\n" + 
            "            \"name\": \"Varus\"\r\n" + 
            "        },\r\n" + 
            "        \"111\": {\r\n" + 
            "            \"title\": \"the Titan of the Depths\",\r\n" + 
            "            \"id\": 111,\r\n" + 
            "            \"key\": \"Nautilus\",\r\n" + 
            "            \"name\": \"Nautilus\"\r\n" + 
            "        },\r\n" + 
            "        \"112\": {\r\n" + 
            "            \"title\": \"the Machine Herald\",\r\n" + 
            "            \"id\": 112,\r\n" + 
            "            \"key\": \"Viktor\",\r\n" + 
            "            \"name\": \"Viktor\"\r\n" + 
            "        },\r\n" + 
            "        \"113\": {\r\n" + 
            "            \"title\": \"Fury of the North\",\r\n" + 
            "            \"id\": 113,\r\n" + 
            "            \"key\": \"Sejuani\",\r\n" + 
            "            \"name\": \"Sejuani\"\r\n" + 
            "        },\r\n" + 
            "        \"114\": {\r\n" + 
            "            \"title\": \"the Grand Duelist\",\r\n" + 
            "            \"id\": 114,\r\n" + 
            "            \"key\": \"Fiora\",\r\n" + 
            "            \"name\": \"Fiora\"\r\n" + 
            "        },\r\n" + 
            "        \"115\": {\r\n" + 
            "            \"title\": \"the Hexplosives Expert\",\r\n" + 
            "            \"id\": 115,\r\n" + 
            "            \"key\": \"Ziggs\",\r\n" + 
            "            \"name\": \"Ziggs\"\r\n" + 
            "        },\r\n" + 
            "        \"117\": {\r\n" + 
            "            \"title\": \"the Fae Sorceress\",\r\n" + 
            "            \"id\": 117,\r\n" + 
            "            \"key\": \"Lulu\",\r\n" + 
            "            \"name\": \"Lulu\"\r\n" + 
            "        },\r\n" + 
            "        \"119\": {\r\n" + 
            "            \"title\": \"the Glorious Executioner\",\r\n" + 
            "            \"id\": 119,\r\n" + 
            "            \"key\": \"Draven\",\r\n" + 
            "            \"name\": \"Draven\"\r\n" + 
            "        },\r\n" + 
            "        \"120\": {\r\n" + 
            "            \"title\": \"the Shadow of War\",\r\n" + 
            "            \"id\": 120,\r\n" + 
            "            \"key\": \"Hecarim\",\r\n" + 
            "            \"name\": \"Hecarim\"\r\n" + 
            "        },\r\n" + 
            "        \"121\": {\r\n" + 
            "            \"title\": \"the Voidreaver\",\r\n" + 
            "            \"id\": 121,\r\n" + 
            "            \"key\": \"Khazix\",\r\n" + 
            "            \"name\": \"Kha'Zix\"\r\n" + 
            "        },\r\n" + 
            "        \"122\": {\r\n" + 
            "            \"title\": \"the Hand of Noxus\",\r\n" + 
            "            \"id\": 122,\r\n" + 
            "            \"key\": \"Darius\",\r\n" + 
            "            \"name\": \"Darius\"\r\n" + 
            "        },\r\n" + 
            "        \"126\": {\r\n" + 
            "            \"title\": \"the Defender of Tomorrow\",\r\n" + 
            "            \"id\": 126,\r\n" + 
            "            \"key\": \"Jayce\",\r\n" + 
            "            \"name\": \"Jayce\"\r\n" + 
            "        },\r\n" + 
            "        \"127\": {\r\n" + 
            "            \"title\": \"the Ice Witch\",\r\n" + 
            "            \"id\": 127,\r\n" + 
            "            \"key\": \"Lissandra\",\r\n" + 
            "            \"name\": \"Lissandra\"\r\n" + 
            "        },\r\n" + 
            "        \"131\": {\r\n" + 
            "            \"title\": \"Scorn of the Moon\",\r\n" + 
            "            \"id\": 131,\r\n" + 
            "            \"key\": \"Diana\",\r\n" + 
            "            \"name\": \"Diana\"\r\n" + 
            "        },\r\n" + 
            "        \"133\": {\r\n" + 
            "            \"title\": \"Demacia's Wings\",\r\n" + 
            "            \"id\": 133,\r\n" + 
            "            \"key\": \"Quinn\",\r\n" + 
            "            \"name\": \"Quinn\"\r\n" + 
            "        },\r\n" + 
            "        \"134\": {\r\n" + 
            "            \"title\": \"the Dark Sovereign\",\r\n" + 
            "            \"id\": 134,\r\n" + 
            "            \"key\": \"Syndra\",\r\n" + 
            "            \"name\": \"Syndra\"\r\n" + 
            "        },\r\n" + 
            "        \"136\": {\r\n" + 
            "            \"title\": \"The Star Forger\",\r\n" + 
            "            \"id\": 136,\r\n" + 
            "            \"key\": \"AurelionSol\",\r\n" + 
            "            \"name\": \"Aurelion Sol\"\r\n" + 
            "        },\r\n" + 
            "        \"141\": {\r\n" + 
            "            \"title\": \"the Shadow Reaper\",\r\n" + 
            "            \"id\": 141,\r\n" + 
            "            \"key\": \"Kayn\",\r\n" + 
            "            \"name\": \"Kayn\"\r\n" + 
            "        },\r\n" + 
            "        \"142\": {\r\n" + 
            "            \"title\": \"the Aspect of Twilight\",\r\n" + 
            "            \"id\": 142,\r\n" + 
            "            \"key\": \"Zoe\",\r\n" + 
            "            \"name\": \"Zoe\"\r\n" + 
            "        },\r\n" + 
            "        \"143\": {\r\n" + 
            "            \"title\": \"Rise of the Thorns\",\r\n" + 
            "            \"id\": 143,\r\n" + 
            "            \"key\": \"Zyra\",\r\n" + 
            "            \"name\": \"Zyra\"\r\n" + 
            "        },\r\n" + 
            "        \"145\": {\r\n" + 
            "            \"title\": \"Daughter of the Void\",\r\n" + 
            "            \"id\": 145,\r\n" + 
            "            \"key\": \"Kaisa\",\r\n" + 
            "            \"name\": \"Kai'Sa\"\r\n" + 
            "        },\r\n" + 
            "        \"150\": {\r\n" + 
            "            \"title\": \"the Missing Link\",\r\n" + 
            "            \"id\": 150,\r\n" + 
            "            \"key\": \"Gnar\",\r\n" + 
            "            \"name\": \"Gnar\"\r\n" + 
            "        },\r\n" + 
            "        \"154\": {\r\n" + 
            "            \"title\": \"the Secret Weapon\",\r\n" + 
            "            \"id\": 154,\r\n" + 
            "            \"key\": \"Zac\",\r\n" + 
            "            \"name\": \"Zac\"\r\n" + 
            "        },\r\n" + 
            "        \"157\": {\r\n" + 
            "            \"title\": \"the Unforgiven\",\r\n" + 
            "            \"id\": 157,\r\n" + 
            "            \"key\": \"Yasuo\",\r\n" + 
            "            \"name\": \"Yasuo\"\r\n" + 
            "        },\r\n" + 
            "        \"161\": {\r\n" + 
            "            \"title\": \"the Eye of the Void\",\r\n" + 
            "            \"id\": 161,\r\n" + 
            "            \"key\": \"Velkoz\",\r\n" + 
            "            \"name\": \"Vel'Koz\"\r\n" + 
            "        },\r\n" + 
            "        \"163\": {\r\n" + 
            "            \"title\": \"the Stoneweaver\",\r\n" + 
            "            \"id\": 163,\r\n" + 
            "            \"key\": \"Taliyah\",\r\n" + 
            "            \"name\": \"Taliyah\"\r\n" + 
            "        },\r\n" + 
            "        \"164\": {\r\n" + 
            "            \"title\": \"the Steel Shadow\",\r\n" + 
            "            \"id\": 164,\r\n" + 
            "            \"key\": \"Camille\",\r\n" + 
            "            \"name\": \"Camille\"\r\n" + 
            "        },\r\n" + 
            "        \"201\": {\r\n" + 
            "            \"title\": \"the Heart of the Freljord\",\r\n" + 
            "            \"id\": 201,\r\n" + 
            "            \"key\": \"Braum\",\r\n" + 
            "            \"name\": \"Braum\"\r\n" + 
            "        },\r\n" + 
            "        \"202\": {\r\n" + 
            "            \"title\": \"the Virtuoso\",\r\n" + 
            "            \"id\": 202,\r\n" + 
            "            \"key\": \"Jhin\",\r\n" + 
            "            \"name\": \"Jhin\"\r\n" + 
            "        },\r\n" + 
            "        \"203\": {\r\n" + 
            "            \"title\": \"The Eternal Hunters\",\r\n" + 
            "            \"id\": 203,\r\n" + 
            "            \"key\": \"Kindred\",\r\n" + 
            "            \"name\": \"Kindred\"\r\n" + 
            "        },\r\n" + 
            "        \"222\": {\r\n" + 
            "            \"title\": \"the Loose Cannon\",\r\n" + 
            "            \"id\": 222,\r\n" + 
            "            \"key\": \"Jinx\",\r\n" + 
            "            \"name\": \"Jinx\"\r\n" + 
            "        },\r\n" + 
            "        \"223\": {\r\n" + 
            "            \"title\": \"the River King\",\r\n" + 
            "            \"id\": 223,\r\n" + 
            "            \"key\": \"TahmKench\",\r\n" + 
            "            \"name\": \"Tahm Kench\"\r\n" + 
            "        },\r\n" + 
            "        \"236\": {\r\n" + 
            "            \"title\": \"the Purifier\",\r\n" + 
            "            \"id\": 236,\r\n" + 
            "            \"key\": \"Lucian\",\r\n" + 
            "            \"name\": \"Lucian\"\r\n" + 
            "        },\r\n" + 
            "        \"238\": {\r\n" + 
            "            \"title\": \"the Master of Shadows\",\r\n" + 
            "            \"id\": 238,\r\n" + 
            "            \"key\": \"Zed\",\r\n" + 
            "            \"name\": \"Zed\"\r\n" + 
            "        },\r\n" + 
            "        \"240\": {\r\n" + 
            "            \"title\": \"the Cantankerous Cavalier\",\r\n" + 
            "            \"id\": 240,\r\n" + 
            "            \"key\": \"Kled\",\r\n" + 
            "            \"name\": \"Kled\"\r\n" + 
            "        },\r\n" + 
            "        \"245\": {\r\n" + 
            "            \"title\": \"the Boy Who Shattered Time\",\r\n" + 
            "            \"id\": 245,\r\n" + 
            "            \"key\": \"Ekko\",\r\n" + 
            "            \"name\": \"Ekko\"\r\n" + 
            "        },\r\n" + 
            "        \"254\": {\r\n" + 
            "            \"title\": \"the Piltover Enforcer\",\r\n" + 
            "            \"id\": 254,\r\n" + 
            "            \"key\": \"Vi\",\r\n" + 
            "            \"name\": \"Vi\"\r\n" + 
            "        },\r\n" + 
            "        \"266\": {\r\n" + 
            "            \"title\": \"the Darkin Blade\",\r\n" + 
            "            \"id\": 266,\r\n" + 
            "            \"key\": \"Aatrox\",\r\n" + 
            "            \"name\": \"Aatrox\"\r\n" + 
            "        },\r\n" + 
            "        \"267\": {\r\n" + 
            "            \"title\": \"the Tidecaller\",\r\n" + 
            "            \"id\": 267,\r\n" + 
            "            \"key\": \"Nami\",\r\n" + 
            "            \"name\": \"Nami\"\r\n" + 
            "        },\r\n" + 
            "        \"268\": {\r\n" + 
            "            \"title\": \"the Emperor of the Sands\",\r\n" + 
            "            \"id\": 268,\r\n" + 
            "            \"key\": \"Azir\",\r\n" + 
            "            \"name\": \"Azir\"\r\n" + 
            "        },\r\n" + 
            "        \"412\": {\r\n" + 
            "            \"title\": \"the Chain Warden\",\r\n" + 
            "            \"id\": 412,\r\n" + 
            "            \"key\": \"Thresh\",\r\n" + 
            "            \"name\": \"Thresh\"\r\n" + 
            "        },\r\n" + 
            "        \"420\": {\r\n" + 
            "            \"title\": \"the Kraken Priestess\",\r\n" + 
            "            \"id\": 420,\r\n" + 
            "            \"key\": \"Illaoi\",\r\n" + 
            "            \"name\": \"Illaoi\"\r\n" + 
            "        },\r\n" + 
            "        \"421\": {\r\n" + 
            "            \"title\": \"the Void Burrower\",\r\n" + 
            "            \"id\": 421,\r\n" + 
            "            \"key\": \"RekSai\",\r\n" + 
            "            \"name\": \"Rek'Sai\"\r\n" + 
            "        },\r\n" + 
            "        \"427\": {\r\n" + 
            "            \"title\": \"the Green Father\",\r\n" + 
            "            \"id\": 427,\r\n" + 
            "            \"key\": \"Ivern\",\r\n" + 
            "            \"name\": \"Ivern\"\r\n" + 
            "        },\r\n" + 
            "        \"429\": {\r\n" + 
            "            \"title\": \"the Spear of Vengeance\",\r\n" + 
            "            \"id\": 429,\r\n" + 
            "            \"key\": \"Kalista\",\r\n" + 
            "            \"name\": \"Kalista\"\r\n" + 
            "        },\r\n" + 
            "        \"432\": {\r\n" + 
            "            \"title\": \"the Wandering Caretaker\",\r\n" + 
            "            \"id\": 432,\r\n" + 
            "            \"key\": \"Bard\",\r\n" + 
            "            \"name\": \"Bard\"\r\n" + 
            "        },\r\n" + 
            "        \"497\": {\r\n" + 
            "            \"title\": \"The Charmer\",\r\n" + 
            "            \"id\": 497,\r\n" + 
            "            \"key\": \"Rakan\",\r\n" + 
            "            \"name\": \"Rakan\"\r\n" + 
            "        },\r\n" + 
            "        \"498\": {\r\n" + 
            "            \"title\": \"the Rebel\",\r\n" + 
            "            \"id\": 498,\r\n" + 
            "            \"key\": \"Xayah\",\r\n" + 
            "            \"name\": \"Xayah\"\r\n" + 
            "        },\r\n" + 
            "        \"516\": {\r\n" + 
            "            \"title\": \"The Fire below the Mountain\",\r\n" + 
            "            \"id\": 516,\r\n" + 
            "            \"key\": \"Ornn\",\r\n" + 
            "            \"name\": \"Ornn\"\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "}"; */
    public final static String RUNES = "[\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Ingenious Hunter\",\r\n" + 
            "        \"id\": 8134,\r\n" + 
            "        \"key\": \"IngeniousHunter\",\r\n" + 
            "        \"shortDesc\": \"<b>Unique</b> <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>takedowns</lol-uikit-tooltipped-keyword> grant permanent Active Item <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>CDR</lol-uikit-tooltipped-keyword> (includes Trinkets).\",\r\n" + 
            "        \"longDesc\": \"Gain 10% <b>Active Item CDR</b> plus an additional 6% per <i>Bounty Hunter</i> stack (includes Trinkets).<br><br>Earn a <i>Bounty Hunter</i> stack the first time you get a takedown on each enemy champion.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/IngeniousHunter/IngeniousHunter.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Last Stand\",\r\n" + 
            "        \"id\": 8299,\r\n" + 
            "        \"key\": \"LastStand\",\r\n" + 
            "        \"shortDesc\": \"Deal more damage to champions while you are low on health.\",\r\n" + 
            "        \"longDesc\": \"Deal 5% - 11% increased damage to champions while you are below 60% health. Max damage gained at 30% health.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/LastStand/LastStand.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Revitalize\",\r\n" + 
            "        \"id\": 8453,\r\n" + 
            "        \"key\": \"Revitalize\",\r\n" + 
            "        \"shortDesc\": \"Heals and shields you cast or receive are 5% stronger and increased by an additional 10% on low health targets.\",\r\n" + 
            "        \"longDesc\": \"Heals and shields you cast or receive are 5% stronger and increased by an additional 10% on targets below 40% health.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/Revitalize/Revitalize.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Ravenous Hunter\",\r\n" + 
            "        \"id\": 8135,\r\n" + 
            "        \"key\": \"RavenousHunter\",\r\n" + 
            "        \"shortDesc\": \"<b>Unique</b> <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>takedowns</lol-uikit-tooltipped-keyword> grant permanent healing from ability damage. \",\r\n" + 
            "        \"longDesc\": \"Heal for a percentage of the damage dealt by your abilities.<br>Healing: 2.5% + 2.5% per <i>Bounty Hunter</i> stack. <br><br>Earn a <i>Bounty Hunter</i> stack the first time you get a takedown on each enemy champion.<br><rules><br>Healing reduced to one third for Area of Effect abilities.</rules><br>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/RavenousHunter/RavenousHunter.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Approach Velocity\",\r\n" + 
            "        \"id\": 8410,\r\n" + 
            "        \"key\": \"ApproachVelocity\",\r\n" + 
            "        \"shortDesc\": \"Bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword> towards nearby ally champions that are <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_ImpairMov'>movement impaired</lol-uikit-tooltipped-keyword> or enemy champions that you impair.\",\r\n" + 
            "        \"longDesc\": \"Gain 15% Movement Speed towards nearby ally champions that are movement impaired or enemy champions that you impair. <br><br>Range: 1000\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/ApproachVelocity/ApproachVelocity.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Legend: Bloodline\",\r\n" + 
            "        \"id\": 9103,\r\n" + 
            "        \"key\": \"LegendBloodline\",\r\n" + 
            "        \"shortDesc\": \"<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>Takedowns</lol-uikit-tooltipped-keyword> on enemies grant permanent<b> Lifesteal</b>. \",\r\n" + 
            "        \"longDesc\": \"Gain 0.8% life steal for every <i>Legend</i> stack (max 10 stacks).<br><br>Earn progress toward <i>Legend</i> stacks for every champion takedown, epic monster takedown, large monster kill, and minion kill.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/Legend_Infamy.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Coup de Grace\",\r\n" + 
            "        \"id\": 8014,\r\n" + 
            "        \"key\": \"CoupDeGrace\",\r\n" + 
            "        \"shortDesc\": \"Deal more damage to low health enemy champions.\",\r\n" + 
            "        \"longDesc\": \"Deal 7% more damage to champions who have less than 40% health.<br><br>Additionally, takedowns on champions grant an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of 9 Attack Damage or 15 Ability Power for 10s.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/CoupDeGrace/CoupDeGrace.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Overgrowth\",\r\n" + 
            "        \"id\": 8451,\r\n" + 
            "        \"key\": \"Overgrowth\",\r\n" + 
            "        \"shortDesc\": \"Gain additional permanent max health when minions or monsters die near you.\",\r\n" + 
            "        \"longDesc\": \"Permanently gain 0.2% maximum health for every 8 monsters or enemy minions that die near you.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/Overgrowth/Overgrowth.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Overheal\",\r\n" + 
            "        \"id\": 9101,\r\n" + 
            "        \"key\": \"Overheal\",\r\n" + 
            "        \"shortDesc\": \"Excess healing on you becomes a shield.\",\r\n" + 
            "        \"longDesc\": \"Excess healing on you becomes a shield, for up to 10% of your total health + 10.<br><br>Shield is built up from 40% of excess self-healing, or 300% of excess healing from allies.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/Overheal.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Transcendence\",\r\n" + 
            "        \"id\": 8210,\r\n" + 
            "        \"key\": \"Transcendence\",\r\n" + 
            "        \"shortDesc\": \"Gain 10% <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>CDR</lol-uikit-tooltipped-keyword> when you reach level 10. Excess CDR becomes AP or AD, <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>adaptive</lol-uikit-tooltipped-keyword>.\",\r\n" + 
            "        \"longDesc\": \"Gain 10% CDR when you reach level 10.<br><br>Each percent of CDR exceeding the CDR limit is converted to an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of 1.2 Attack Damage or 2 Ability Power.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/Transcendence/Transcendence.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Eyeball Collection\",\r\n" + 
            "        \"id\": 8138,\r\n" + 
            "        \"key\": \"EyeballCollection\",\r\n" + 
            "        \"shortDesc\": \"Collect eyeballs for champion and ward <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>takedowns</lol-uikit-tooltipped-keyword>. Gain permanent AD or AP, <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>adaptive</lol-uikit-tooltipped-keyword> for each eyeball plus bonus upon collection completion.\",\r\n" + 
            "        \"longDesc\": \"Collect eyeballs for champion and ward takedowns. Gain an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of 0.6 Attack Damage or 1 Ability Power, per eyeball collected. <br><br>Upon completing your collection at 20 eyeballs, additionally gain an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of 6 Attack Damage, or 10 Ability Power.<br><br>Collect 2 eyeballs per champion kill, 1 per assist, and 1 per ward takedown.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/EyeballCollection/EyeballCollection.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Cut Down\",\r\n" + 
            "        \"id\": 8017,\r\n" + 
            "        \"key\": \"CutDown\",\r\n" + 
            "        \"shortDesc\": \"Deal more damage to champions with more max health than you.\",\r\n" + 
            "        \"longDesc\": \"Deal 4% more damage to champions with 150 more max health than you, increasing to 12% at 2000 more max health.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/CutDown/CutDown.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Taste of Blood\",\r\n" + 
            "        \"id\": 8139,\r\n" + 
            "        \"key\": \"TasteOfBlood\",\r\n" + 
            "        \"shortDesc\": \"Heal when you damage an enemy champion.\",\r\n" + 
            "        \"longDesc\": \"Heal when you damage an enemy champion.<br><br>Healing: 18-35 (+0.2 bonus AD, +0.1 AP) health (based on level)<br><br>Cooldown: 20s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/TasteOfBlood/GreenTerror_TasteOfBlood.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Zombie Ward\",\r\n" + 
            "        \"id\": 8136,\r\n" + 
            "        \"key\": \"ZombieWard\",\r\n" + 
            "        \"shortDesc\": \"After killing a ward, a friendly Zombie Ward is raised in its place. When your wards expire, they also reanimate as Zombie Wards.\",\r\n" + 
            "        \"longDesc\": \"After killing a ward, a friendly Zombie Ward is raised in its place. Additionally, when your wards expire, they reanimate as Zombie Wards.<br><br>Zombie Wards are visible, last for 30 - 120s and don't count towards your ward limit.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/ZombieWard/ZombieWard.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Legend: Alacrity\",\r\n" + 
            "        \"id\": 9104,\r\n" + 
            "        \"key\": \"LegendAlacrity\",\r\n" + 
            "        \"shortDesc\": \"<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>Takedowns</lol-uikit-tooltipped-keyword> on enemies grant permanent <b>Attack Speed</b>. \",\r\n" + 
            "        \"longDesc\": \"Gain 3% attack speed plus an additional 1.5% for every <i>Legend</i> stack (max 10 stacks).<br><br>Earn progress toward <i>Legend</i> stacks for every champion takedown, epic monster takedown, large monster kill, and minion kill.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/Legend_Heroism.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Legend: Tenacity\",\r\n" + 
            "        \"id\": 9105,\r\n" + 
            "        \"key\": \"LegendTenacity\",\r\n" + 
            "        \"shortDesc\": \"<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>Takedowns</lol-uikit-tooltipped-keyword> on enemies grant permanent <b>Tenacity</b>. \",\r\n" + 
            "        \"longDesc\": \"Gain 5% tenacity plus an additional 2.5% for every <i>Legend</i> stack (max 10 stacks).<br><br>Earn progress toward <i>Legend</i> stacks for every champion takedown, epic monster takedown, large monster kill, and minion kill.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/Legend_Tenacity.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Summon Aery\",\r\n" + 
            "        \"id\": 8214,\r\n" + 
            "        \"key\": \"SummonAery\",\r\n" + 
            "        \"shortDesc\": \"Your attacks and abilities send Aery to a target, damaging enemies or shielding allies.\",\r\n" + 
            "        \"longDesc\": \"Your attacks and abilities send Aery to a target, damaging enemy champions or shielding allies.<br><br>Damage: 15 - 40 based on level (+<scaleAP>0.1 AP</scaleAP> and +<scaleAD>0.15 bonus AD</scaleAD>)<br>Shield: 30 - 80 based on level (+<scaleAP>0.25 AP</scaleAP> and +<scaleAD>0.4 bonus AD</scaleAD>) <br><br>Aery cannot be sent out again until she returns to you.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/SummonAery/SummonAery.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Conqueror\",\r\n" + 
            "        \"id\": 8010,\r\n" + 
            "        \"key\": \"Conqueror\",\r\n" + 
            "        \"shortDesc\": \"After 4 seconds in combat, your first attack against an enemy champion grants you AD and converts some of your damage to true damage.\",\r\n" + 
            "        \"longDesc\": \"After 4 seconds in combat, your first attack against an enemy champion grants you 10 - 35 AD, based on level, for 3 seconds and converts 20% of your damage to champions to true damage.<br><br><rules>Melee Only: Damaging enemy champions refreshes this buff.</rules>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/Conqueror/Conqueror.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Lethal Tempo\",\r\n" + 
            "        \"id\": 8008,\r\n" + 
            "        \"key\": \"LethalTempo\",\r\n" + 
            "        \"shortDesc\": \"1.5s after damaging a champion gain a large amount of Attack Speed. Lethal Tempo allows you to temporarily exceed the attack speed limit.\",\r\n" + 
            "        \"longDesc\": \"1.5s after damaging a champion gain 40 - 110% Attack Speed (based on level) for 3s. Attacking a champion extends the effect to 6s.<br><br>Cooldown: 6s<br><br>Lethal Tempo allows you to temporarily exceed the attack speed limit.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/FlowofBattle/FlowofBattleTemp.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Presence of Mind\",\r\n" + 
            "        \"id\": 8009,\r\n" + 
            "        \"key\": \"PresenceOfMind\",\r\n" + 
            "        \"shortDesc\": \"Takedowns restore 20% of your maximum mana and refund 10% of your ultimate's cooldown.\",\r\n" + 
            "        \"longDesc\": \"Takedowns restore 20% of your maximum mana and refund 10% of your ultimate's cooldown.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/LastResort/LastResortIcon.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Guardian\",\r\n" + 
            "        \"id\": 8465,\r\n" + 
            "        \"key\": \"Guardian\",\r\n" + 
            "        \"shortDesc\": \"Guard allies you cast spells on and those that are very nearby. If you or a guarded ally would take damage, you're both hasted and granted a shield.\",\r\n" + 
            "        \"longDesc\": \"<i>Guard</i> allies within 175 units of you, and allies you target with spells for 2.5s. While <i>Guarding</i>, if you or the ally take damage, both of you gain a shield and are hasted for 1.5s.<br><br>Cooldown: <scaleLevel>70 - 40</scaleLevel> seconds<br>Shield: <scaleLevel>70 - 150</scaleLevel> + <scaleAP>0.25%</scaleAP> of your ability power + <scalehealth>12%</scalehealth> of your bonus health.<br>Haste: +20% Movement Speed.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/Guardian/Guardian.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Sudden Impact\",\r\n" + 
            "        \"id\": 8143,\r\n" + 
            "        \"key\": \"SuddenImpact\",\r\n" + 
            "        \"shortDesc\": \"Gain a burst of Lethality and Magic Penetration after using a dash, leap, blink, teleport, or when leaving stealth.\",\r\n" + 
            "        \"longDesc\": \"After exiting stealth or using a dash, leap, blink, or teleport, dealing any damage to a champion grants you 10 Lethality and 8 Magic Penetration for 5s.<br><br>Cooldown: 4s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/SuddenImpact/SuddenImpact.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Triumph\",\r\n" + 
            "        \"id\": 9111,\r\n" + 
            "        \"key\": \"Triumph\",\r\n" + 
            "        \"shortDesc\": \"<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>Takedowns</lol-uikit-tooltipped-keyword> restore 12% of your missing health and grant an additional 20 gold. \",\r\n" + 
            "        \"longDesc\": \"Takedowns restore 12% of your missing health and grant an additional 20 gold. <br><br><hr></hr><br><i>'The most dangerous game brings the greatest glory.' <br>—Noxian Reckoner</i>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/DangerousGame.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Font of Life\",\r\n" + 
            "        \"id\": 8463,\r\n" + 
            "        \"key\": \"FontOfLife\",\r\n" + 
            "        \"shortDesc\": \"<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_ImpairMov'>Impairing</lol-uikit-tooltipped-keyword> the movement of an enemy champion marks them. Your allies heal when attacking champions you've marked. \",\r\n" + 
            "        \"longDesc\": \"Impairing the movement of an enemy champion marks them for 4s.<br><br>Ally champions who attack marked enemies heal for 5 + 1% of your max health over 2s. \",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/FontOfLife/FontOfLife.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Relentless Hunter\",\r\n" + 
            "        \"id\": 8105,\r\n" + 
            "        \"key\": \"RelentlessHunter\",\r\n" + 
            "        \"shortDesc\": \"<b>Unique</b> <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>takedowns</lol-uikit-tooltipped-keyword> grant permanent <b>out of combat <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword></b>. \",\r\n" + 
            "        \"longDesc\": \"Gain 8 <b>out of combat Movement Speed</b> plus 8 per <i>Bounty Hunter</i> stack.<br><br>Earn a <i>Bounty Hunter</i> stack the first time you get a takedown on each enemy champion.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/RelentlessHunter/RelentlessHunter.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Cosmic Insight\",\r\n" + 
            "        \"id\": 8347,\r\n" + 
            "        \"key\": \"CosmicInsight\",\r\n" + 
            "        \"shortDesc\": \"+5% <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>CDR</lol-uikit-tooltipped-keyword><br>+5% Max CDR<br>+5% Summoner Spell CDR<br>+5% Item CDR\",\r\n" + 
            "        \"longDesc\": \"+5% CDR<br>+5% Max CDR<br>+5% Summoner Spell CDR<br>+5% Item CDR\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/CosmicInsight/CosmicInsight.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Manaflow Band\",\r\n" + 
            "        \"id\": 8226,\r\n" + 
            "        \"key\": \"ManaflowBand\",\r\n" + 
            "        \"shortDesc\": \"Hitting an enemy champion with an ability permanently increases your maximum mana by 25, up to 250 mana.<br><br>After reaching 250 bonus mana, restore 1% of your missing mana every 5 seconds.\",\r\n" + 
            "        \"longDesc\": \"Hitting an enemy champion with an ability permanently increases your maximum mana by 25, up to 250 mana.<br><br>After reaching 250 bonus mana, restore 1% of your missing mana every 5 seconds.<br><br>Cooldown: 15 seconds\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/ManaflowBand/ManaflowBand.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Magical Footwear\",\r\n" + 
            "        \"id\": 8304,\r\n" + 
            "        \"key\": \"MagicalFootwear\",\r\n" + 
            "        \"shortDesc\": \"You get free boots at 10 min but you cannot buy boots before then. Each <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>takedown</lol-uikit-tooltipped-keyword> you get makes your boots come 30s sooner.\",\r\n" + 
            "        \"longDesc\": \"You get free Slightly Magical Boots at 10 min, but you cannot buy boots before then. For each takedown you acquire the boots 30s sooner.<br><br>Slightly Magical Boots give you an additional +10 Movement Speed.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/MagicalFootwear/MagicalFootwear.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Biscuit Delivery\",\r\n" + 
            "        \"id\": 8345,\r\n" + 
            "        \"key\": \"BiscuitDelivery\",\r\n" + 
            "        \"shortDesc\": \"Gain a free Biscuit every 3 min, until 12 min. Consuming a Biscuit permanently increases your max mana and restores health and mana.\",\r\n" + 
            "        \"longDesc\": \"Biscuit Delivery: Gain a Total Biscuit of Everlasting Will every 3 mins, until 12 min.<br><br>Biscuits restore 15% of your missing health and mana. Consuming any Biscuit increases your mana cap by 40 mana permanently. <br><br><i>Manaless:</i> Champions without mana restore 20% missing health instead.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/BiscuitDelivery/BiscuitDelivery.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Nullifying Orb\",\r\n" + 
            "        \"id\": 8224,\r\n" + 
            "        \"key\": \"NullifyingOrb\",\r\n" + 
            "        \"shortDesc\": \"Gain a magic damage shield when taken to low health by magic damage.\",\r\n" + 
            "        \"longDesc\": \"When you take magic damage that would reduce your Health below 30%, gain a shield that absorbs 40 - 120 magic damage based on level (<scaleAP>+0.1 AP</scaleAP> and <scaleAD>+0.15 bonus AD</scaleAD>) for 4s.<br><br>Cooldown: 60s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/NullifyingOrb/Pokeshield.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Fleet Footwork\",\r\n" + 
            "        \"id\": 8021,\r\n" + 
            "        \"key\": \"FleetFootwork\",\r\n" + 
            "        \"shortDesc\": \"Attacking and moving builds Energy stacks. At 100 stacks, your next attack heals you and grants increased <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword>.\",\r\n" + 
            "        \"longDesc\": \"Attacking and moving builds Energy stacks. At 100 stacks, your next attack is Energized.<br><br>Energized attacks heal you for 3 - 60 (+0.3 Bonus AD, +0.4 AP) and grant +30% movement speed for 1s.<br><rules>Healing is 60% as effective when used on a minion (30% effective for ranged).<br>Healing is increased by 40% of your critical damage modifier when triggered by a critical hit.</rules>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/FleetFootwork/FleetFootwork.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Electrocute\",\r\n" + 
            "        \"id\": 8112,\r\n" + 
            "        \"key\": \"Electrocute\",\r\n" + 
            "        \"shortDesc\": \"Hitting a champion with 3 <b>separate</b> attacks or abilities in 3s deals bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>adaptive damage</lol-uikit-tooltipped-keyword>.\",\r\n" + 
            "        \"longDesc\": \"Hitting a champion with 3 <b>separate</b> attacks or abilities within 3s deals bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>adaptive damage</font></lol-uikit-tooltipped-keyword>.<br><br>Damage: 50 - 220 (+0.5 bonus AD, +0.3 AP) damage.<br><br>Cooldown: 50 - 25s<br><br><hr></hr><i>'We called them the Thunderlords, for to speak of their lightning was to invite disaster.'</i>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/Electrocute/Electrocute.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Absolute Focus\",\r\n" + 
            "        \"id\": 8233,\r\n" + 
            "        \"key\": \"AbsoluteFocus\",\r\n" + 
            "        \"shortDesc\": \"While above 70% health, gain extra <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>adaptive damage</lol-uikit-tooltipped-keyword>.\",\r\n" + 
            "        \"longDesc\": \"While above 70% health, gain an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of up to 24 Attack Damage or 40 Ability Power (based on level). <br><br>Grants 3 Attack Damage or 5 Ability Power at level 1. \",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/AbsoluteFocus/AbsoluteFocus.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Celerity\",\r\n" + 
            "        \"id\": 8234,\r\n" + 
            "        \"key\": \"Celerity\",\r\n" + 
            "        \"shortDesc\": \"Gain 3% extra <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword>. Gain extra AP or AD, <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>adaptive</lol-uikit-tooltipped-keyword> based on your bonus MS. \",\r\n" + 
            "        \"longDesc\": \"Gain 3% increased Movement Speed.<br>Your Bonus Movement Speed is converted to Attack Damage or Ability Power, <font color='#48C4B7'>adaptive</font> at a rate of 4.8% Attack Damage or 8% Ability Power.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/Celerity/CelerityTemp.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Time Warp Tonic\",\r\n" + 
            "        \"id\": 8352,\r\n" + 
            "        \"key\": \"TimeWarpTonic\",\r\n" + 
            "        \"shortDesc\": \"Your potions, biscuits and elixirs last 20% longer, and you gain 5% Movement Speed while under their effects.\",\r\n" + 
            "        \"longDesc\": \"Your potions, biscuits and elixirs last 20% longer, and you gain 5% Movement Speed while under their effects.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/TimeWarpTonic/TimeWarpTonic.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Bone Plating\",\r\n" + 
            "        \"id\": 8473,\r\n" + 
            "        \"key\": \"BonePlating\",\r\n" + 
            "        \"shortDesc\": \"After taking damage from an enemy champion, the next 3 spells or attacks you receive from them deal 20-50 less damage.<br><br>Duration: 3s<br>Cooldown: 45s\",\r\n" + 
            "        \"longDesc\": \"After taking damage from an enemy champion, the next 3 spells or attacks you receive from them deal 20-50 less damage.<br><br>Duration: 3s<br>Cooldown: 45s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/BonePlating/BonePlating.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Waterwalking\",\r\n" + 
            "        \"id\": 8232,\r\n" + 
            "        \"key\": \"Waterwalking\",\r\n" + 
            "        \"shortDesc\": \"Gain <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword> and AP or AD, <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>adaptive</lol-uikit-tooltipped-keyword> in the river.\",\r\n" + 
            "        \"longDesc\": \"Gain 25 Movement Speed and an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of up to 18 Attack Damage or 30 Ability Power (based on level) when in the river.<br><br><hr></hr><br><i>May you be as swift as the rushing river and agile as a startled Rift Scuttler.</i><br>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/Waterwalking/Waterwalking.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Scorch\",\r\n" + 
            "        \"id\": 8237,\r\n" + 
            "        \"key\": \"Scorch\",\r\n" + 
            "        \"shortDesc\": \"Your first ability hit every 20s burns champions.\",\r\n" + 
            "        \"longDesc\": \"Your next ability hit sets champions on fire dealing 20 - 60 bonus magic damage based on level after 1s.<br><br>Cooldown: 20s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/Scorch/Scorch.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Kleptomancy\",\r\n" + 
            "        \"id\": 8359,\r\n" + 
            "        \"key\": \"Kleptomancy\",\r\n" + 
            "        \"shortDesc\": \"After using an ability, your next attack will grant bonus gold if used on a champion. There's a chance you'll also gain a consumable.\",\r\n" + 
            "        \"longDesc\": \"After using an ability, your next attack will grant bonus gold if used on a champion. There's a chance you'll also gain a consumable.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/Kleptomancy/Kleptomancy.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Perfect Timing\",\r\n" + 
            "        \"id\": 8313,\r\n" + 
            "        \"key\": \"PerfectTiming\",\r\n" + 
            "        \"shortDesc\": \"Gain a free Stopwatch. Stopwatch has a one time use <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Stasis'>Stasis</lol-uikit-tooltipped-keyword> effect.\",\r\n" + 
            "        \"longDesc\": \"Start the game with a Commencing Stopwatch that transforms into a Stopwatch after 10 min. Stopwatch has a one time use Stasis effect.<br><br>Reduces the cooldown of Zhonya's Hourglass, Guardian Angel, and Gargoyle Stoneplate by 15%.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/PerfectTiming/PerfectTiming.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Gathering Storm\",\r\n" + 
            "        \"id\": 8236,\r\n" + 
            "        \"key\": \"GatheringStorm\",\r\n" + 
            "        \"shortDesc\": \"Gain increasing amounts of AD or AP, <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>adaptive</lol-uikit-tooltipped-keyword> over the course of the game.\",\r\n" + 
            "        \"longDesc\": \"Every 10 min gain AP or AD, <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword>.<br><br><i>10 min</i>: + 8 AP or 5 AD <br><i>20 min</i>: + 24 AP or 14 AD<br><i>30 min</i>: + 48 AP or 29 AD<br><i>40 min</i>: + 80 AP or 48 AD<br><i>50 min</i>: + 120 AP or 72 AD<br><i>60 min</i>: + 168 AP or 101 AD<br>etc...\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/GatheringStorm/GatheringStorm.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Glacial Augment\",\r\n" + 
            "        \"id\": 8351,\r\n" + 
            "        \"key\": \"GlacialAugment\",\r\n" + 
            "        \"shortDesc\": \"Your first attack against an enemy champion slows them (per unit cooldown). Slowing champions with active items shoots a freeze ray at them, creating a lingering slow zone.\",\r\n" + 
            "        \"longDesc\": \"Basic attacking a champion slows them for 2s. The slow increases in strength over its duration.<li><i>Ranged</i>: Ranged attacks slow by up to 30% - 40%</li> <li><i>Melee</i>: Melee attacks slow by up to 45% - 55%</li><br>Slowing a champion with active items shoots a freeze ray through them, freezing the nearby ground for 5s, slowing all units inside by 60%.<br><br>Cooldown: 7-4s per unit\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/GlacialAugment/GlacialAugment.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Chrysalis\",\r\n" + 
            "        \"id\": 8472,\r\n" + 
            "        \"key\": \"Chrysalis\",\r\n" + 
            "        \"shortDesc\": \"Start the game with an extra 60 health. At 4 takedowns, consume that health to gain an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of 9 Attack Damage or 15 Ability Power.\",\r\n" + 
            "        \"longDesc\": \"Start the game with an extra 60 health. At 4 takedowns, consume that health to gain an <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>adaptive</font></lol-uikit-tooltipped-keyword> bonus of 9 Attack Damage or 15 Ability Power.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/Chrysalis/Chrysalis.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Phase Rush\",\r\n" + 
            "        \"id\": 8230,\r\n" + 
            "        \"key\": \"PhaseRush\",\r\n" + 
            "        \"shortDesc\": \"Hitting an enemy champion with 3 <b>separate</b> attacks or abilities grants a burst of <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword>. \",\r\n" + 
            "        \"longDesc\": \"Hitting an enemy champion with 3 attacks or <b>separate</b> abilities within 3s grants 15 - 40% Movement Speed based on level and 75% Slow Resistance.<br><br>Duration: 3s<br>Cooldown: 15s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/PhaseRush/PhaseRush.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Conditioning\",\r\n" + 
            "        \"id\": 8429,\r\n" + 
            "        \"key\": \"Conditioning\",\r\n" + 
            "        \"shortDesc\": \"After 10 min gain +8 Armor and +8 Magic Resist and increase your Armor and Magic Resist by 5%.\",\r\n" + 
            "        \"longDesc\": \"After 10 min gain +8 Armor and +8 Magic Resist and increase your Armor and Magic Resist by 5%.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/Conditioning/Conditioning.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Hextech Flashtraption\",\r\n" + 
            "        \"id\": 8306,\r\n" + 
            "        \"key\": \"HextechFlashtraption\",\r\n" + 
            "        \"shortDesc\": \"While Flash is on cooldown it is replaced by <i>Hexflash</i>.<br><br><i>Hexflash</i>: Channel, then blink to a new location.\",\r\n" + 
            "        \"longDesc\": \"While Flash is on cooldown it is replaced by <i>Hexflash</i>.<br><br><i>Hexflash</i>: Channel for 2s to blink to a new location.<br><br>Cooldown: 20s. Goes on a 10s cooldown when you enter champion combat.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/HextechFlashtraption/HextechFlashtraption.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"Arcane Comet\",\r\n" + 
            "        \"id\": 8229,\r\n" + 
            "        \"key\": \"ArcaneComet\",\r\n" + 
            "        \"shortDesc\": \"Damaging a champion with an ability hurls a damaging comet at their location.\",\r\n" + 
            "        \"longDesc\": \"Damaging a champion with an ability hurls a comet at their location, or, if Arcane Comet is on cooldown, reduces its remaining cooldown.<br><br><lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>Adaptive Damage</font></lol-uikit-tooltipped-keyword>: 30 - 100 based on level (<scaleAP>+0.2 AP</scaleAP> and <scaleAD>+0.35 bonus AD</scaleAD>)<br>Cooldown: 20 - 8s<br><rules><br>Cooldown Reduction:<br>Single Target: 20%.<br>Area of Effect: 10%.<br>Damage over Time: 5%.<br></rules>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/ArcaneComet/ArcaneComet.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Future's Market\",\r\n" + 
            "        \"id\": 8321,\r\n" + 
            "        \"key\": \"FuturesMarket\",\r\n" + 
            "        \"shortDesc\": \"You can enter debt to buy items.\",\r\n" + 
            "        \"longDesc\": \"You can enter debt to buy items. The amount you can borrow increases over time.<br><br>Lending Fee: 50 gold\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/FuturesMarket/FuturesMarket.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Predator\",\r\n" + 
            "        \"id\": 8124,\r\n" + 
            "        \"key\": \"Predator\",\r\n" + 
            "        \"shortDesc\": \"Add an active effect to your boots that grants a large boost of <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>MS</lol-uikit-tooltipped-keyword> and causes your next attack or ability to deal bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>adaptive damage</lol-uikit-tooltipped-keyword>.\",\r\n" + 
            "        \"longDesc\": \"Enchants your boots with the active effect '<font color='#c60300'>Predator</font>.'<br><br>Channel for 1.5s out of combat to gain 45% movement speed for 15s. Damaging attacks or abilities end this effect, dealing 60 - 180 (+<scaleAD>0.4</scaleAD> bonus AD)(+<scaleAP>0.25</scaleAP> AP) bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>adaptive damage</font></lol-uikit-tooltipped-keyword>.<br><br>Cooldown: 150s - 100s. Starts the game on cooldown. 50% cooldown if interrupted while channeling.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/Predator/Predator.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Unflinching\",\r\n" + 
            "        \"id\": 8242,\r\n" + 
            "        \"key\": \"Unflinching\",\r\n" + 
            "        \"shortDesc\": \"After casting a Summoner Spell, gain Tenacity and Slow Resistance for a short duration. Additionally, gain Tenacity and Slow Resistance for each Summoner Spell on cooldown. \",\r\n" + 
            "        \"longDesc\": \"After casting a Summoner Spell, gain 15% Tenacity and Slow Resistance for 10s. Additionally, gain 10% Tenacity and Slow Resistance for each Summoner Spell on cooldown. \",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/Unflinching/Unflinching.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Sorcery\",\r\n" + 
            "        \"runePathId\": 8200,\r\n" + 
            "        \"name\": \"The Ultimate Hat\",\r\n" + 
            "        \"id\": 8243,\r\n" + 
            "        \"key\": \"TheUltimateHat\",\r\n" + 
            "        \"shortDesc\": \"Your ultimate's cooldown is reduced. Each time you cast your ultimate, its cooldown is further reduced.\",\r\n" + 
            "        \"longDesc\": \"Your ultimate's cooldown is reduced by 5%. Each time you cast your ultimate, its cooldown is further reduced by 2%. Stacks up to 5 times.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Sorcery/TheUltimateHat/TheUltimateHat.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Demolish\",\r\n" + 
            "        \"id\": 8446,\r\n" + 
            "        \"key\": \"Demolish\",\r\n" + 
            "        \"shortDesc\": \"Charge up a powerful attack against a tower while near it.\",\r\n" + 
            "        \"longDesc\": \"Charge up a powerful attack against a tower over 4s, while within 600 range of it. The charged attack deals 125 (+30% of your max health) bonus physical damage.<br><br>Cooldown: 45s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/Demolish/Demolish.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Dark Harvest\",\r\n" + 
            "        \"id\": 8128,\r\n" + 
            "        \"key\": \"DarkHarvest\",\r\n" + 
            "        \"shortDesc\": \"Champions, large minions, and large monsters drop soul essence on death. Touch souls to absorb them and deal bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>adaptive damage</lol-uikit-tooltipped-keyword> on your next attack based on total soul essence collected.\",\r\n" + 
            "        \"longDesc\": \"Champions, large minions, and large monsters drop soul essence on death. Collect souls to become <font color='#c60300'>Soul Charged</font>. Your next attack on a champion or structure consumes <font color='#c60300'>Soul Charged</font> to deal bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>adaptive damage</font></lol-uikit-tooltipped-keyword>.<br><br><font color='#c60300'>Soul Charged</font> lasts 20s, increased to 300s after collecting 150 soul essence.<br><br>Bonus damage: 40 - 80 (+<scaleAD>0.25 bonus AD</scaleAD>) (+<scaleAP>0.2 AP</scaleAP>) + soul essence collected.<br><rules><br>Champions - 6 soul essence.<br>Monsters - 2 soul essence.<br>Minions - 4 soul essence.</rules>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/DarkHarvest/DarkHarvest.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Unsealed Spellbook\",\r\n" + 
            "        \"id\": 8326,\r\n" + 
            "        \"key\": \"UnsealedSpellbook\",\r\n" + 
            "        \"shortDesc\": \"Exchange Summoner Shards at the shop to change your Summoner Spells during game. Your Summoner Spells have reduced cooldowns. <br>\",\r\n" + 
            "        \"longDesc\": \"Gain a Summoner Shard at 2 min and another every 6 min after (Max 2 shards).<br><br>While near the shop, you can exchange 1 Summoner Shard to replace a Summoner Spell with a different one. <br><br>Additionally, your Summoner Spell Cooldowns are reduced by 15%.<br><br><rules><i>Smite:</i> Buying Smite won't grant access to Smite items<br>You cannot have two of the same Summoner Spell</rules>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/UnsealedSpellbook/UnsealedSpellbook.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Second Wind\",\r\n" + 
            "        \"id\": 8444,\r\n" + 
            "        \"key\": \"SecondWind\",\r\n" + 
            "        \"shortDesc\": \"After taking damage from an enemy champion heal back some missing health over time. \",\r\n" + 
            "        \"longDesc\": \"After taking damage from an enemy champion, heal for 4% of your missing health +6 over 10s.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/SecondWind/SecondWind.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Cheap Shot\",\r\n" + 
            "        \"id\": 8126,\r\n" + 
            "        \"key\": \"CheapShot\",\r\n" + 
            "        \"shortDesc\": \"Deal bonus true damage to enemy champions with <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_ImpairAct'>impaired movement or actions</lol-uikit-tooltipped-keyword>. \",\r\n" + 
            "        \"longDesc\": \"Damaging champions with <b>impaired movement or actions</b> deals 12 - 30 bonus true damage (based on level).<br><br>Cooldown: 4s<br><rules>Activates on damage occurring after the impairment.</rules>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/CheapShot/CheapShot.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Precision\",\r\n" + 
            "        \"runePathId\": 8000,\r\n" + 
            "        \"name\": \"Press the Attack\",\r\n" + 
            "        \"id\": 8005,\r\n" + 
            "        \"key\": \"PressTheAttack\",\r\n" + 
            "        \"shortDesc\": \"Hitting an enemy champion 3 consecutive times makes them vulnerable, dealing bonus damage and causing them to take more damage from all sources for 6s.\",\r\n" + 
            "        \"longDesc\": \"Hitting an enemy champion with 3 consecutive basic attacks deals 40 - 180 bonus <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>adaptive damage</font></lol-uikit-tooltipped-keyword> (based on level) and makes them vulnerable, increasing the damage they take by 8 - 12% from all sources for 6s.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Domination\",\r\n" + 
            "        \"runePathId\": 8100,\r\n" + 
            "        \"name\": \"Ghost Poro\",\r\n" + 
            "        \"id\": 8120,\r\n" + 
            "        \"key\": \"GhostPoro\",\r\n" + 
            "        \"shortDesc\": \"When you enter brush, a poro appears. It will stay behind to give you vision.\",\r\n" + 
            "        \"longDesc\": \"Enter a brush to summon a poro after a brief channel. The poro will stay behind to give you vision until you summon a new one.<br><br>If an enemy enters brush with a poro in it, they scare it away, putting Ghost Poro on a 3s cooldown.<br><br>Poro channel is interrupted if you take damage.\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Domination/GhostPoro/GhostPoro.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Aftershock\",\r\n" + 
            "        \"id\": 8439,\r\n" + 
            "        \"key\": \"Aftershock\",\r\n" + 
            "        \"shortDesc\": \"After <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Immobilize'>immobilizing</lol-uikit-tooltipped-keyword> an enemy champion gain defenses and later deal a burst of magic damage around you.\",\r\n" + 
            "        \"longDesc\": \"After immobilizing an enemy champion, increase your Armor and Magic Resist by 70 - 120 for 2.5s. Then explode, dealing magic damage to nearby enemies.<br><br>Damage: 10 - 120 (+3% of your maximum health) (+15% of your bonus attack damage) (+10% of your ability power)<br>Cooldown: 35s\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/VeteranAftershock/VeteranAftershock.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Inspiration\",\r\n" + 
            "        \"runePathId\": 8300,\r\n" + 
            "        \"name\": \"Minion Dematerializer\",\r\n" + 
            "        \"id\": 8316,\r\n" + 
            "        \"key\": \"MinionDematerializer\",\r\n" + 
            "        \"shortDesc\": \"Start the game with 6 Minion Dematerializers. Killing minions with the item gives permanent bonus damage vs. that minion type.\",\r\n" + 
            "        \"longDesc\": \"Start the game with 6 Minion Dematerializers that kill and absorb lane minions instantly. Minion Dematerializers are on cooldown for the first 155s of the game.<br><br>Absorbing a minion increases your damage by +4% against that type of minion permanently, and an extra +1% for each additional minion of that type absorbed.<br>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Inspiration/MinionDematerializer/MinionDematerializer.png\"\r\n" + 
            "    },\r\n" + 
            "    {\r\n" + 
            "        \"runePathName\": \"Resolve\",\r\n" + 
            "        \"runePathId\": 8400,\r\n" + 
            "        \"name\": \"Grasp of the Undying\",\r\n" + 
            "        \"id\": 8437,\r\n" + 
            "        \"key\": \"GraspOfTheUndying\",\r\n" + 
            "        \"shortDesc\": \"Every 4s your next attack on a champion deals bonus magic damage, heals you, and permanently increases your health.\",\r\n" + 
            "        \"longDesc\": \"Every 4s in combat, your next basic attack on a champion will:<li>Deal bonus magic damage equal to 4% of your max health</li><li>Heals you for 2% of your max health</li><li>Permanently increase your health by 5</li><br><rules><i>Ranged Champions:</i> Damage, healing, and permanent health gained reduced by 40%.</rules><br>\",\r\n" + 
            "        \"icon\": \"perk-images/Styles/Resolve/GraspOfTheUndying/GraspOfTheUndying.png\"\r\n" + 
            "    }\r\n" + 
            "]";
    
    /** Gets the cooldowns of a champion 
     * 
     * @param champ the champion name
     * @return the string to be shown to the user
     */
    
    public static String getCDs(String champ) {
        
        String[] words = champ.split(" ");
        StringBuilder str = new StringBuilder();
        
        for (String word : words) {
            str.append(word.substring(0,1).toUpperCase() + word.substring(1));
        }
        
        String champname = "";
        try {
            champname = getRealChampName(str.toString());  
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot find champ.";
        }
        String url = URL + PATCH_NO + "/data/en_US/champion/" + champname + ".json";
            
        try (InputStream is = HttpRequest.sendGet(url); ) {        
            
          
            String response = JsonParser.getSpells(is, champname);
            return response;
            
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot find champ.";
        }

    }
    
    public static String getRealChampName(String champ) throws IOException {
     
        String response = JsonParser.getRealChampName(champ);
        return response;
    
        
    }
    
    public static String getAllChampNames() throws IOException {
        String url = URL + PATCH_NO + "/data/en_US/champion.json";
        try (InputStream is = HttpRequest.sendGet(url);) {
            String response = JsonParser.getAllChampNames(is);
            return response;
        }
        
    }
    
    /** Gets the live game information of a summoner in League. 
     * 
     * @param summoner the summoner's name
     * @param key the personal League of Legends API key
     * @return live game information if there is a current game, an empty game object otherwise
     * @throws IOException if the summoner's name was not found
     */
    public static Optional<Game> getLiveGameInfo(String summoner, String key) throws IOException {
        
      
        Summoner s = getSummonerInfo(summoner, key);
        String url = URL2 + "spectator/v3/active-games/by-summoner/" + s.getId();
    
        try (InputStream is = HttpRequest.sendGetLoL(url, key);) {
              
            Game response = JsonParser.getLiveGame(is, key);
            return Optional.of(response);
            
        } catch (IOException e) {
            
            e.printStackTrace();
            return Optional.empty();
            
        }

    }
    
    public static Summoner getSummonerInfo(String name, String key) throws IOException {
  
        String url = URL2 + "summoner/v3/summoners/by-name/" + URLEncoder.encode(name, "UTF-8").replace("+", "%20");
        
        try (InputStream is = HttpRequest.sendGetLoL(url, key);) {
            
            Summoner s = JsonParser.getSummonerInfo(is);
            Summoner s2 = getSummonerSoloStats(s, key);
            
            return s2;
        }
    }
    
    public static Summoner getSummonerSoloStats(Summoner s, String key) throws IOException {
       
        String url = URL2 + "league/v3/positions/by-summoner/" + s.getId();

        try (InputStream is = HttpRequest.sendGetLoL(url, key);) {
            
            Summoner response = JsonParser.getSummonerSoloStats(s, is);
            return response;
            
        }
      
    }

    public static String getChampionName(long id) {
        
        String name;
        try {
            name = JsonParser.getChampionName(id);
        } catch (IOException e) {
            
            e.printStackTrace();
            name = "Aatrox";
        }
        return name;
        
    }
    
    
    
    public static String getRuneName(long id) {
        String name = JsonParser.getRuneName(id);
        
        
        /*int i = (int) id;
        
        switch (i) {
            case 8134: name = "Ingeneious Hunter"; break;
            case 8299: name = "Last Stand"; break;
            case 8453: name = "Revitalize"; break;
            case 8135: name = "Ravenous Hunter"; break;
            case 8410: name = "Approach Velocity"; break;
            case 9103: name = "Legend: Bloodline"; break;
            case 8014: name = "Coup de Grace"; break;
            case 8451: name = "Overgrowth"; break;
            case 9101: name = "Overheal"; break;
            case 8210: name = "Transcendence"; break;
            case 8138: name = "Eyeball Collection"; break;
            case 8017: name = "Cut Down"; break;
            case 8139: name = "Taste of Blood"; break;
            case 8136: name = "Zombie Ward"; break;
            case 9104: name = "Legend: Alacrity"; break;
            case 9105: name = "Legend: Tenacity"; break;
            case 8214: name = "Summon Aery"; break;
            case 8010: name = "Conqueror"; break;
            case 8808: name = "Lethal Tempo"; break;
            case 8009: name = "Presence of Mind"; break;
            case 8465: name = "Guardian"; break;
            case 8143: name = "Sudden Impact"; break;
            case 9111: name = "Triumph"; break;
            case 8463: name = "Font of Life"; break;
            case 8105: name = "Relentless Hunter"; break;
            case 8347: name = "Cosmic Insight"; break;
            case 8226: name = "Manaflow Band"; break;
            case 8304: name = "Magical Footwear"; break;
            case 8345: name = "Biscuit Delivery"; break;
            case 8224: name = "Nullifying Orb"; break;
            case 8021: name = "Fleet Footwork"; break;
            case 8112: name = "Electrocute"; break;
            case 8233: name = "Absolute Focus"; break;
            case 8234: name = "Celerity"; break;
            case 8352: name = "Time Warp Tonic"; break;
            case 8473: name = "Bone Plating"; break;
            case 8232: name = "Waterwalking"; break;
            case 8237: name = "Scorch"; break;
            case 8359: name = "Kleptomancy"; break;
            case 8313: name = "Perfect Timing"; break;
            case 8236: name = "Gathering Storm"; break;
            case 8351: name = "Glacial Augment"; break;
            case 8472: name = "Chrysalis"; break;
            case 8230: name = "Phase Rush"; break;
            case 8429: name = "Conditioning"; break;
            case 8306: name = "Hextech Flashtraption"; break;
            case 8229: name = "Arcane Comet"; break;
            case 8231: name = "Future's Market"; break;
            case 8124: name = "Predator"; break;
            case 8242: name = "Unflinching"; break;
            case 8243: name = "The Ultimate Hat"; break;
            case 8446: name = "Demolish"; break;
            case 8128: name = "Dark Harvest"; break;
            case 8326: name = "Unsealed Spellbook"; break;
            case 8444: name = "Second Wind"; break;
            case 8126: name = "Cheap Shot"; break;
            case 8005: name = "Press the Attack"; break;
            case 8120: name = "Ghost Poro"; break;
            case 8439: name = "Aftershock"; break;
            case 8316: name = "Minion Dematerializer"; break;
            case 8437: name = "Grasp of the Undying"; break;
            default: name = ""; break;
        }
        */
        
        return name;
    }
    
    

    
    
}
