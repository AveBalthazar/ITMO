package commands;

import utility.Request;
import utility.Response;

/**
 * Абстрактный класс для команд
 */
public abstract class CommandAbstract implements CommandInterface {

    private final String description;
    private final boolean isRequiredAuthorization;

    /**
     * Конструктор класса
     *
     * @param aDescription - описание команды
     */
    public CommandAbstract(String aDescription, boolean anIsRequiredAuthorization) {
        description = aDescription;
        isRequiredAuthorization = anIsRequiredAuthorization;
    }

    /**
     * Метод для вывода информации о команде
     *
     * @return Строку, содержащую имя команыд и её описание
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Метод для получения статуса авторизации
     *
     * @return статус boolean - True/False
     */
    @Override
    public boolean getAuthorizationStatus() {
        return isRequiredAuthorization;
    }

    /**
     * Метод для вызова команд
     */
    @Override
    public abstract Response execute(Request aRequest);
}