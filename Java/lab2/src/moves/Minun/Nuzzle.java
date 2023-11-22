package moves.Minun;

import ru.ifmo.se.pokemon.*;
public class Nuzzle extends PhysicalMove {
    public Nuzzle(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect.paralyze(p);
    }
}
