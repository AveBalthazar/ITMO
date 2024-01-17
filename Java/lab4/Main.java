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

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


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
            public void min(List<Place> exploredList) {
                Function<Place, Integer> scoreMinimumFunction = Place::getExploredScore;
                List<Integer> score = exploredList.stream().map(scoreMinimumFunction).toList();
                int min = score.get(0);
                for (int j = 1; j < score.size(); j++) {
                    if (score.get(j) < min) {
                        min = score.get(j);
                    }
                }
                System.out.println("Самое пугающее место исследовано на " + min + " процентов.");
            }
        };

        scoreMinimumFind.min(Arrays.asList(forest, six_trees, meadow));

        TrapSystem nutsTrap = new NutsTrap(40, 10);
        TrapSystem honeyTrap = new HoneyTrap(20, 15);

        elephant.move(forest);
        piglet.move(meadow);
        puh.move(meadow);


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