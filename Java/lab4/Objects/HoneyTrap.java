package Objects;

import Enums.ConditionAfterFall;
import Enums.TrapType;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;

public class HoneyTrap extends TrapSystem {
    public Honey honey;
    public Integer damage;
    public Integer honeyQuantity;
    public HoneyTrap(Integer damage, Integer honeyQuantity) {
        super("Горшок с мёдом", TrapType.Honey, damage);
        Honey honey = new Honey(honeyQuantity);
    }
    @Override
    public void Catch(Heffalump c) throws CannotDieTwice, CannotDoThisAction {
        if (((c.getCondition() == null) | (c.getCondition() == ConditionAfterFall.NotCatched)) && this.isActive) {
            honey.giveDamage(c, this.damage);
            c.setCondition(ConditionAfterFall.InHoneyTrap);
            System.out.println(c.getName() + " угодил в медовую ловушку, Пятачку повезло!");
        }
        else {
            throw new CannotDoThisAction();
        }
    }
    //вложенный статический
    public static class Honey {
        String name;
        Integer quantity;
        public Honey(Integer quantity) {
            this.name = "мёд";
            this.quantity = quantity;
        }
        public void giveDamage(Heffalump c, Integer damage) {
            if (this.quantity >= 60) {
                System.out.println("Персонаж " + c.getName() + " тонет в меду!!!");
            }
            c.hurt(damage*this.quantity);
        }
    }
}