package Exceptions;

public class CannotDieTwice extends RuntimeException{
    public CannotDieTwice() {
        super("Ошибка: Персонаж уже покинул этот мир и не может сделать этого ещё раз.");
    }
}
