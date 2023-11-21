package moves.Pineco;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;
public class Bulldoze extends PhysicalMove {
    public Bulldoze(double pow, double acc) {
        super(Type.NORMAL, pow*2, acc);
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
