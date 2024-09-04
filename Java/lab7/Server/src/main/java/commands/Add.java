package commands;

import data.StudyGroup;
import utility.Receiver;
import utility.Request;
import utility.Response;

/**
 * Класс для добавления новой записи в базу данных
 */
public class Add extends CommandAbstract {

    private final Receiver receiver;

    public Add(Receiver aReceiver) {
        super("добавляет новый элемент в коллекцию", true);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        StudyGroup studyGroup = aRequest.getCommand().getStudyGroup();
        return new Response(receiver.add(studyGroup));
    }
}