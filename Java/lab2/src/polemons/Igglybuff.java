package polemons;
import moves.Igglybuff.DefenseCurl;
import moves.Igglybuff.ShadowBall;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Igglybuff extends Jigglypuff {
    public Igglybuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        super.setStats(90,30,15,40,20,15);

        DefenseCurl defenseCurl = new DefenseCurl();
        ShadowBall shadowBall = new ShadowBall(80,100);

        super.setMove(defenseCurl, shadowBall);
    }
}
