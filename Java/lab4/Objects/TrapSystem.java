package Objects;
import Enums.TrapType;
import Enums.Distance;
import Exceptions.CannotDieTwice;

public abstract class TrapSystem {
    private final String name;
    private final TrapType trapType;
    protected Integer damage;
    Distance distance;
    public boolean isActive = false;
    public TrapSystem(String name, TrapType trapType, Integer damage) {
        this.name = name;
        this.trapType = trapType;
        this.damage = damage;
    }
    public String getName() {
        return this.name;
    }
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
    public TrapType getType() {
        return this.trapType;
    }

    public abstract void grab(Heffalump c) throws CannotDieTwice;

    //локальный класс
    public void toggleBait() {
        class Bait {
            public void interact() {
                isActive = !isActive;
                if (isActive) {
                    System.out.println("Ловушка " + getName() + " деактивирована");
                } else {
                    System.out.println("Ловушка " + getName() + " активирована");
                }
            }
        }
        Bait bait = new Bait();
        bait.interact();
    }
}
