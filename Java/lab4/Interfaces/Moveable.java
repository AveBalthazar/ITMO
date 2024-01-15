package Interfaces;

import Exceptions.CannotAddThisAmount;
import Exceptions.CannotDoThisAction;
import Objects.Place;

public interface Moveable {
    void move(Place place) throws CannotAddThisAmount, CannotDoThisAction;
}