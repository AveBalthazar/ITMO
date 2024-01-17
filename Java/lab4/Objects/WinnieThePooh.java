package Objects;

import Enums.ConditionAfterFall;
import Enums.Gender;

import Exceptions.CannotDoThisAction;
import Interfaces.Grabbable;
public class WinnieThePooh extends Character implements Grabbable {
    public BodyPart nose;
    public BodyPart head;
    public WinnieThePooh() {
        super("Винни Пух", Gender.Male);
        nose = new BodyPart("Нос");
        head = new BodyPart("Затылок");
    }
    public void scratch(BodyPart body_part) {
        if (body_part.getCondition() == Boolean.FALSE) {
            System.out.println(getName() + " почесал " + body_part.getName());
            body_part.is_scratched = Boolean.TRUE;
        } else {
            System.out.println("Эта часть тела у Винни Пуха уже почесана, сейчас он не хочет её чесать.");
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
    //Вложенный внутренний
    public class BodyPart {
        private String name;
        private Boolean is_scratched;

        public BodyPart(String name) {
            this.name = name;
            this.is_scratched = Boolean.FALSE;
        }

        private String getName() {
            return this.name;
        }

        private Boolean getCondition() {
            return this.is_scratched;
        }
    }
}
