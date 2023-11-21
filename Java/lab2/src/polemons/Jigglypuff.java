package polemons;
import moves.Igglybuff.DefenseCurl;
import moves.Igglybuff.ShadowBall;
import moves.Jigglypuff.Pound;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Jigglypuff extends Wigglytuff {
    public Jigglypuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        super.setStats(115,45,20,45,25,20);

        DefenseCurl defenseCurl = new DefenseCurl();
        ShadowBall shadowBall = new ShadowBall(80,100);
        Pound pound = new Pound(40, 100);

        super.setMove(defenseCurl, shadowBall, pound);
    }
}
