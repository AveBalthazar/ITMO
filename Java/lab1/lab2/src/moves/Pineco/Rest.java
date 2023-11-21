package moves.Pineco;

import ru.ifmo.se.pokemon.*;
public class Rest extends PhysicalMove {
    public Rest() {
        super();
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
        Effect e = new Effect().turns(2).stat(Stat.HP, 75);
        Effect.paralyze(p);

        p.addEffect(e);
    }

    @Override
    protected String describe() {
        String[] rezhem = this.getClass().toString().split("\\.");
        return "применяет " + rezhem[rezhem.length-1];
    }
}
