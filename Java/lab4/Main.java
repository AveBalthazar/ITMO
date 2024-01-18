import Exceptions.CannotAddThisAmount;
import Exceptions.CannotDieTwice;
import Exceptions.CannotDoThisAction;
import Interfaces.PlaceFunction;
import Objects.WinnieThePooh;
import Objects.Heffalump;
import Objects.Piglet;
import Objects.TrapSystem;
import Objects.Place;
import Objects.NutsTrap;
import Objects.HoneyTrap;
import Enums.*;

public class Main {
    public static void main(String[] args) throws CannotAddThisAmount, CannotDieTwice, CannotDoThisAction {
        WinnieThePooh puh = new WinnieThePooh();
        puh.scratch(puh.nose);
        Piglet piglet = new Piglet();
        Heffalump elephant = new Heffalump();

        Place forest = new Place("Дремучий лес", 0);
        Place six_trees = new Place("Шесть сосен", 20);
        Place meadow = new Place("Полянка", 85);

        //анонимный класс
        PlaceFunction scoreMinimumFind = new PlaceFunction() {
            @Override
            public void min(Place[] places) {
                int minExplored = places[0].exploredScore;
                for (Place score : places) {
                    if (score.exploredScore < minExplored) {
                        minExplored = score.exploredScore;
                    }
                }
                System.out.println("Самое пугающее место исследовано на " + minExplored + " процентов.");
            }
        };
        Place [] list = {forest, six_trees, meadow};
        scoreMinimumFind.min(list);

        TrapSystem nutsTrap = new NutsTrap(40, 10);
        TrapSystem honeyTrap = new HoneyTrap(20, 15);
        nutsTrap.toggleBait();

        elephant.move(forest);
        piglet.move(meadow);
        puh.move(meadow);

        piglet.grab(puh, nutsTrap);
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
        } catch (CannotDoThisAction|CannotDieTwice e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("попытались поймать Слонопотама");
        }
        piglet.freeze();
        piglet.warn(nutsTrap);
        try {
            piglet.addKarmaPoints(0);
        } catch (CannotAddThisAmount e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("попытались добавить карму Пятачку");
        }

        puh.hurt(100);

        try {
            puh.hurt(100);
        } catch (CannotDieTwice e) {
            System.out.println(e.getMessage());
        }

    }
}