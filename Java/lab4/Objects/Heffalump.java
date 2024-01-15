package Objects;

import Enums.Gender;
import Enums.ConditionAfterFall;
import Exceptions.CannotAddThisAmount;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;

public class Heffalump extends Character {
    public Heffalump() {
        super("Слонопотам", Gender.Male);
    }
    public void setCondition(ConditionAfterFall conditionAfterFall) {
        this.condition = conditionAfterFall;
        System.out.println(this.getName() + " теперь " + conditionAfterFall.getName());
    }
    public ConditionAfterFall getCondition() {
        if (this.hp > 0) {
            if (this.condition != null) {
                return this.condition;
            } else {
                return ConditionAfterFall.NotCatched;
            }
        }
        else {
            return ConditionAfterFall.Dead;
        }
    }
    public void fall(Trap trap) throws CannotDieTwice {
        if (this.getCondition() != ConditionAfterFall.NotCatched) {
            System.out.println(this.name + " упал в " + trap.getName());
            this.hurt(30);
            switch (trap.getType()) {
                case Honey -> this.setCondition(ConditionAfterFall.InHoneyTrap);
                case Nuts -> this.setCondition(ConditionAfterFall.InNutsTrap);
            }
        } else {
            System.out.println(this.getName() + "уже пойман, больше ему деваться некуда");
        }
    }
    @Override
    public void move(Place place) throws CannotAddThisAmount, CannotDoThisAction {
        boolean b = (this.condition == ConditionAfterFall.NeedHelp) || (this.condition == ConditionAfterFall.InHoneyTrap);
        if (b) {
            throw new CannotDoThisAction();
        } else {
            System.out.println(this.getName() + " прогулялся и пришёл в " + place.getName());
            this.setLocation(place);
            place.addExploredScore(30);
        }
    }
}