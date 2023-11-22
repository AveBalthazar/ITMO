package moves.Minun;

import ru.ifmo.se.pokemon.*;
public class Agility extends StatusMove {
    public Agility() {
        super();
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.SPEED, 2);

        p.addEffect(e);
    }

}
