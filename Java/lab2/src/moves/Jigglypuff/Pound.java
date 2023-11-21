package moves.Jigglypuff;

import ru.ifmo.se.pokemon.*;

public class Pound extends PhysicalMove {
    public Pound(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
