import Exceptions.CannotAddThisAmount;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;
import Objects.WinnieThePooh;
import Objects.Heffalump;
import Objects.Piglet;
import Objects.TrapSystem;
import Objects.Place;
import Enums.*;
public class Main {
    public static void main(String[] args) throws CannotAddThisAmount, CannotDieTwice, CannotDoThisAction {
        WinnieThePooh puh = new WinnieThePooh();
        Piglet piglet = new Piglet();
        Heffalump elephant = new Heffalump();

        //*анонимный класс
        TrapSystem nutsTrap = new TrapSystem.NutsTrap();
        TrapSystem honeyTrap = new TrapSystem.HoneyTrap();

        Place forest = new Place("Дремучий лес", 0);
        Place six_trees = new Place("Шесть сосен", 20);
        Place meadow = new Place("Полянка", 85);

        elephant.move(forest);
        piglet.move(meadow);
        puh.move(meadow);

        WinnieThePooh.BodyPart nose = puh.new BodyPart("Нос");
        WinnieThePooh.BodyPart head = puh.new BodyPart("Затылок");
        puh.setWannaScratch(nose);
        puh.setWannaScratch(head);
        System.out.println(puh.getBodyPartsToScratch());

        //обработка исключения, связанного с Reflections
        try {
            puh.scratch(nose);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            puh.scratch(nose);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        honeyTrap.setDistance(Distance.VeryClose);
        nutsTrap.setDistance(Distance.Near);
        puh.push_friend(piglet, honeyTrap);
        honeyTrap.toggleBait();
        nutsTrap.toggleBait();
        honeyTrap.Catch(elephant);
        forest.scare(piglet);
        meadow.printExploredScore();

        try {
            nutsTrap.Catch(elephant);
        } catch (CannotDoThisAction e) {
            System.out.println(e.getMessage());
        }
        piglet.freeze();
        piglet.warn(nutsTrap);
        try {
            piglet.addKarmaPoints(0);
        } catch (CannotAddThisAmount e) {
            System.out.println(e.getMessage());
        }

        puh.hurt(100);

        try {
            puh.hurt(100);
        } catch (CannotDieTwice e) {
            System.out.println(e.getMessage());
        }

    }
}