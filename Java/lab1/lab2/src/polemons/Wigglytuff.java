package polemons;
import moves.Igglybuff.DefenseCurl;
import moves.Igglybuff.ShadowBall;
import moves.Wigglytuff.Pound;
import moves.Wigglytuff.Thunderbolt;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Wigglytuff extends Pokemon {
    public Wigglytuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        super.setStats(140,70,45,85,50,45);

        DefenseCurl defenseCurl = new DefenseCurl();
        ShadowBall shadowBall = new ShadowBall(80,100);
        Thunderbolt thunderbolt = new Thunderbolt(90,100);
        Pound pound = new Pound(40,100);

        super.setMove(defenseCurl, shadowBall, thunderbolt, pound);
    }
}
