package moves.Wigglytuff;

import eshkere.Main;
import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove {
    public Thunderbolt(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        if(Main.chance(0.1)) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
