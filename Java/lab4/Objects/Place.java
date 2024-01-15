package Objects;

import Exceptions.CannotAddThisAmount;
import Exceptions.CannotDieTwice;

import java.util.Objects;
public class Place {
    private final String name;
    private Integer exploredScore;
    public Place(String name, Integer exploredScore) {
        this.name = name;
        this.exploredScore = exploredScore;
    }
    @Override
    public int hashCode() {
        return (Objects.hash(name))*(Objects.hash(exploredScore));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return (place.name == this.name) && (place.exploredScore == this.exploredScore);
    }
    public void addExploredScore(int score) throws CannotAddThisAmount{
        if (score > 0){
            this.exploredScore += score;
            if (this.exploredScore >= 100) {
                System.out.println("Теперь друзьям не страшно гулять в " + this.name + ", ведь это место полностью исследовано!");
            } else {
                System.out.println("Теперь " + this.getName() + " исследован на " + this.exploredScore + "%");
            }
        }
        else {
            throw new CannotAddThisAmount();
        }
    }
    public void printExploredScore() {
        if (this.exploredScore >= 100) {
            System.out.println("Место " + this.getName() + " полностью исследовано.");
        }
        System.out.println("Место " + this.getName() + " исследовано на " + this.exploredScore + "%");
    }
    public void scare(Character c) throws CannotDieTwice {
        System.out.println("Место " + this.getName() + " испугало персонажа " + c.getName());
        if (this.exploredScore != 0) {
            c.hurt(this.exploredScore / 100);
        }
        else {
            c.hurt(100);
            System.out.println("Персонаж " + c.getName() + "погиб от испуга!");
        }
    }
    public String getName() {
        return this.name;
    }
}
