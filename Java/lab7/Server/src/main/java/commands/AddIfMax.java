package commands;

import data.StudyGroup;
import utility.Receiver;
import utility.Request;
import utility.Response;
import utility.TypeOfAnswer;

/**
 * Класс дял добавления максимального элемента в коллекцию
 */
public class AddIfMax extends CommandAbstract {

    private final Receiver receiver;

    public AddIfMax(Receiver aReceiver) {
        super("добавляет элемент в коллекцию, если его значение максимально", true);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        StudyGroup studyGroup = aRequest.getCommand().getStudyGroup();
        String username = aRequest.getSession().getName();
        TypeOfAnswer status = receiver.addIfMax(studyGroup);
        if (status.equals(TypeOfAnswer.SUCCESSFUL)) {
            receiver.addToHistory(username, "add_if_max");
        }
        return new Response(status);
    }
}