package moves.Forretress;

import ru.ifmo.se.pokemon.*;
public class Bulldoze extends PhysicalMove {
    public Bulldoze(double pow, double acc) {
        super(Type.NORMAL, pow*2, acc);
    }
}
