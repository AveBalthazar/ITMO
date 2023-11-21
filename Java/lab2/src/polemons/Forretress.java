package polemons;
import moves.Forretress.FlashCannon;
import moves.Forretress.Rest;
import moves.Pineco.Bulldoze;
import moves.Pineco.Facade;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Forretress extends Pokemon {
    public Forretress(String name, int level) {
        super(name, level);
        super.setType(Type.BUG, Type.STEEL);
        super.setStats(75, 90,140,60,60,40);

        Bulldoze bulldoze = new Bulldoze(60,100);
        Facade facade = new Facade(70,100);
        FlashCannon flashCannon = new FlashCannon(80,100);
        Rest rest = new Rest();

        super.setMove(bulldoze, facade, flashCannon, rest);
    }
}
