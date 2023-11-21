package polemons;
import moves.Forretress.Bulldoze;
import moves.Forretress.Facade;
import moves.Forretress.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Pineco extends Forretress {
    public Pineco(String name, int level) {
        super(name, level);
        super.setType(Type.BUG);
        super.setStats(50, 65,90,35,35,15);

        Bulldoze bulldoze = new Bulldoze(60, 100);
        Facade facade = new Facade(70,100);
        Rest rest = new Rest();

        super.setMove(bulldoze, facade, rest);
    }
}
