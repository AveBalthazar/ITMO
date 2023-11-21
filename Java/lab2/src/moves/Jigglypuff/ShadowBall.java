package moves.Jigglypuff;

import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove {
    public ShadowBall(double pow, double acc) {
        super(Type.GHOST, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.2).stat(Stat.SPECIAL_DEFENSE, -1);

        p.addEffect(e);
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
