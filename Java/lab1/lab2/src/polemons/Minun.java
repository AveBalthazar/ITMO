package polemons;
import moves.Igglybuff.DefenseCurl;
import moves.Igglybuff.ShadowBall;
import moves.Minun.Agility;
import moves.Minun.Nuzzle;
import moves.Minun.Spark;
import moves.Minun.Swift;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Minun extends Pokemon {
    public Minun(String name, int level) {
        super(name, level);
        super.setType(Type.ELECTRIC);
        super.setStats(60, 40,50,75,85,95);

        Agility agility = new Agility();
        Nuzzle nuzzle = new Nuzzle(20,100);
        Spark spark = new Spark(65,100);
        Swift swift = new Swift(60,Double.POSITIVE_INFINITY);

        super.setMove(agility, nuzzle, spark, swift);
    }
}
