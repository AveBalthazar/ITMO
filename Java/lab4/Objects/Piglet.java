package Objects;

import Enums.ConditionAfterFall;
import Enums.Gender;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;
import Interfaces.Grabbable;

public class Piglet extends Character implements Grabbable {
    public Piglet() {
        super("Пятачок", Gender.Male);
    }
    public void freeze() throws CannotDieTwice, CannotDoThisAction {
        if (this.hp > 0) {
            System.out.println(getName() + " замерзает");
            hurt(10);
        } else {
            throw new CannotDoThisAction();
        }
    }
    public void warn(TrapSystem trap) throws CannotDoThisAction {
        if (this.hp > 0){
            System.out.println(this.name + " предупреждает: ловушка " + trap.getName() + "!");
            this.karma += 10;
        } else {
            throw new CannotDoThisAction();
        }
    }
    @Override
    public void grab(Character c, TrapSystem trap) throws CannotDoThisAction{
        if (this.hp > 0 && trap.isActive){
            System.out.println(this.getName() + " поймал " + c.getName() + " при помощи " + trap.getName() + "!");
            switch (trap.getType()) {
                case Honey -> c.condition = ConditionAfterFall.InHoneyTrap;
                case Nuts -> c.condition = ConditionAfterFall.InNutsTrap;
            }
            c.hurt(10);
        } else {
            throw new CannotDoThisAction();
        }
    }
}