package commands;

import data.StudyGroup;
import utility.Receiver;
import utility.Request;
import utility.Response;

/**
 * Класс для обновления записей в таблице по их id
 */
public class UpdateId extends CommandAbstract {

    private final Receiver receiver;

    public UpdateId(Receiver aReceiver) {
        super("обновляет значения элемента коллекции, id которого совпадает с введённым", true);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        StudyGroup upgradedGroup = aRequest.getCommand().getStudyGroup();
        int id = Integer.parseInt(aRequest.getCommand().getArg());
        return new Response(receiver.updateId(upgradedGroup, id));
    }
}