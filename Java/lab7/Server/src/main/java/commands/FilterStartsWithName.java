package commands;

import data.StudyGroup;
import utility.Receiver;
import utility.Request;
import utility.Response;
import utility.TypeOfAnswer;

import java.util.Set;

/**
 * Класс для вывода элементов коллекции, поле name которых начинается с введённой строки
 */
public class FilterStartsWithName extends CommandAbstract {

    private final Receiver receiver;

    public FilterStartsWithName(Receiver aReceiver) {
        super("выводит элементы коллекции, поле name которых начинается с введённой строки", false);
        receiver = aReceiver;
    }

    public Response execute(Request aRequest) {
        String username = aRequest.getSession().getName();
        String startName = aRequest.getCommand().getArg();
        Set<StudyGroup> setOfGroups = receiver.filterStartsWithName(startName, username);
        return (setOfGroups == null)
                ? new Response(TypeOfAnswer.EMPTYCOLLECTION)
                : (setOfGroups.isEmpty())
                ? new Response(TypeOfAnswer.OBJECTNOTEXIST)
                : new Response(setOfGroups, TypeOfAnswer.SUCCESSFUL);
    }
}