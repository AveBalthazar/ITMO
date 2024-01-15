package Exceptions;

public class CannotDoThisAction extends RuntimeException{
    public CannotDoThisAction() {
        super("Ошибка: В данный момент невозможно выполнить это действие.");
    }
}
