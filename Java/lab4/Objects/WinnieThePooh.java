package Objects;

import Enums.ConditionAfterFall;
import Enums.Gender;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.Consumer;

import Exceptions.CannotDoThisAction;
import Interfaces.Catchable;
public class WinnieThePooh extends Character implements Catchable {
    public ArrayList<BodyPart> wannaScratch = new ArrayList<>();
    public WinnieThePooh() {
        super("Винни Пух", Gender.Male);
        this.wannaScratch = wannaScratch;
    }
    public String getBodyPartsToScratch() {
        final String[] result = {""};
        this.wannaScratch.forEach(new Consumer<BodyPart>() {
            @Override
            public void accept(BodyPart part) {
                result[0] += part.getName() + " ";
            }
        });
        return result[0];
    }
    public void setWannaScratch(BodyPart bodyPart) {
        this.wannaScratch.add(bodyPart);
    }
    public void scratch(BodyPart body_part) throws NoSuchFieldException, IllegalAccessException {
        Field field = body_part.getClass().getDeclaredField("is_scratched");
        if (body_part.getCondition() == Boolean.FALSE) {
            System.out.println(getName() + " почесал " + body_part.getName());
            this.wannaScratch.remove(body_part);
            field.set(body_part, (Boolean) Boolean.TRUE);
            body_part.is_scratched = Boolean.TRUE;
        } else {
            System.out.println("Эта часть тела у Винни Пуха уже почесана. Сейчас он хочет почесать следующие части тела: " + this.getBodyPartsToScratch());
        }
    }
    @Override
    public void grab(Character c, Trap trap) throws CannotDoThisAction{
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
    //Вложенный non-static
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
