package moves.Forretress;

import ru.ifmo.se.pokemon.*;
public class Facade extends PhysicalMove {
    public Facade(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect.paralyze(p);
    }
}
