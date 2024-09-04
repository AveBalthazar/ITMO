package commands;

import data.StudyGroup;
import utility.Receiver;
import utility.Request;
import utility.Response;
import utility.TypeOfAnswer;

import java.util.Set;

/**
 * Класс для вывода всех элементов коллекции в стандартный поток вывода
 */
public class Show extends CommandAbstract {

    private final Receiver receiver;

    public Show(Receiver aReceiver) {
        super("выводит все элементы коллекции строкой в стандартный поток вывода", false);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        String username = aRequest.getSession().getName();
        receiver.addToHistory(username, "show");

        Set<StudyGroup> studyGroups = receiver.show();
        if (studyGroups == null) return new Response(TypeOfAnswer.EMPTYCOLLECTION);
        else return new Response(studyGroups, TypeOfAnswer.SUCCESSFUL);
    }
}