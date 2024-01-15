package Objects;
import Enums.ConditionAfterFall;
import Enums.TrapType;
import Enums.Distance;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;

public abstract class Trap {
    private final String name;
    private final TrapType trapType;
    Integer damage;
    Distance distance;
    public boolean isActive = false;
    public Trap(String name, TrapType trapType, Integer damage) {
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

    public abstract void Catch(Heffalump c) throws CannotDieTwice;
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

    //Вложенные static
    public static class NutsTrap extends Trap{
        public NutsTrap() {
            super("Лукошко с орехами [Ловушка]", TrapType.Nuts, 25);
        }
        @Override
        public void Catch(Heffalump c) throws CannotDieTwice, CannotDoThisAction {
            if (c.condition == null && this.isActive) {
                c.hurt(this.damage);
                c.setCondition(ConditionAfterFall.InNutsTrap);
                System.out.println(c.getName() + " угодил в ореховую ловушку, Пятачку повезло!");
            }
            else {
                throw new CannotDoThisAction();
            }
        }
    }
    public static class HoneyTrap extends Trap{
        public HoneyTrap() {
            super("Горшок с мёдом [Ловушка]", TrapType.Honey, 15);
        }
        @Override
        public void Catch(Heffalump c) throws CannotDieTwice, CannotDoThisAction {
            if (c.condition == null && this.isActive) {
                c.hurt(this.damage);
                c.setCondition(ConditionAfterFall.InHoneyTrap);
                System.out.println(c.getName() + " угодил в медовую ловушку, Пятачку повезло!");
            }
            else {
                throw new CannotDoThisAction();
            }
        }
    }

}
