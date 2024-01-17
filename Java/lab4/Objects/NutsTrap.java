package Objects;

import Enums.ConditionAfterFall;
import Enums.TrapType;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;

public class NutsTrap extends TrapSystem {
    public Nuts nuts;
    public Integer damage;
    public NutsTrap(Integer damage, Integer nutsQuantity) {
        super("Лукошко с орехами", TrapType.Nuts, damage);
        Nuts nuts = new Nuts(nutsQuantity);
    }
    @Override
    public void Catch(Heffalump c) throws CannotDieTwice, CannotDoThisAction {
        if (((c.getCondition() == null) | (c.getCondition() == ConditionAfterFall.NotCatched)) && this.isActive) {
            nuts.giveDamage(c, this.damage);
            c.setCondition(ConditionAfterFall.InNutsTrap);
            System.out.println(c.getName() + " угодил в ореховую ловушку, Пятачку повезло!");
        }
        else {
            throw new CannotDoThisAction();
        }
    }
    //вложенный статический
    public static class Nuts {
        String name;
        Integer quantity;
        public Nuts(Integer quantity) {
            this.name = "орешки";
            this.quantity = quantity;
        }
        public void giveDamage(Heffalump c, Integer damage) {
            c.hurt(damage*this.quantity);
        }
    }
}