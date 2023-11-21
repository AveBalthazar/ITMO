package moves.Minun;

import ru.ifmo.se.pokemon.*;
public class Spark extends PhysicalMove {
    public Spark(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
