package moves.Minun;

import ru.ifmo.se.pokemon.*;
public class Swift extends SpecialMove {
    public Swift(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
