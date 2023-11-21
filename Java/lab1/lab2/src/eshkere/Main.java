package eshkere;

import polemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon So_nya = new Forretress("Selestia", 1);
        Pokemon matve = new Igglybuff("gr4nce", 1);
        Pokemon snus = new Jigglypuff("Neosnus", 1);
        Pokemon maks = new Minun("bludlung", 1);
        Pokemon ya = new Pineco("balzzar", 1);
        Pokemon Ivan = new Wigglytuff("gre4ka", 1);

        b.addAlly(So_nya);
        b.addAlly(Ivan);
        b.addAlly(ya);
        b.addFoe(matve);
        b.addFoe(snus);
        b.addFoe(maks);
        
        b.go();
    }
    public static boolean chance(double d) {
        return d > Math.random();
    }
}