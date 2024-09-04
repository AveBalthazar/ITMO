package commands;

import data.StudyGroup;
import utility.Receiver;
import utility.Request;
import utility.Response;
import utility.TypeOfAnswer;

/**
 * Класс для добавления элемента в коллекцию, если он минимален
 */
public class AddIfMin extends CommandAbstract {

    private final Receiver receiver;

    public AddIfMin(Receiver aReceiver) {
        super("добавляет элемент в коллекцию, если он минимален", true);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        StudyGroup studyGroup = aRequest.getCommand().getStudyGroup();
        String username = aRequest.getSession().getName();
        TypeOfAnswer status = receiver.addIfMin(studyGroup);
        if (status.equals(TypeOfAnswer.SUCCESSFUL)) {
            receiver.addToHistory(username, "add_if_min");
        }
        return new Response(status);
    }
}