package Exceptions;

public class CannotAddThisAmount extends Exception{
    public CannotAddThisAmount() {
        super("Ошибка: Указанное количество очков невозможно добавить.");
    }
}
