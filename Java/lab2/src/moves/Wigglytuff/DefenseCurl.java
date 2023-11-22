package moves.Wigglytuff;

import ru.ifmo.se.pokemon.*;

public class DefenseCurl extends StatusMove {
    public DefenseCurl() {
        super();
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.DEFENSE, 1);

        p.addEffect(e);
    }
}
