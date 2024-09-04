package commands;

import utility.Receiver;
import utility.Request;
import utility.Response;

/**
 * Класс, который удаляет элемент из коллекции по его ID
 */
public class RemoveById extends CommandAbstract {

    private final Receiver receiver;

    public RemoveById(Receiver aReceiver) {
        super("удаляет элемент из коллекции по его ID", true);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        String username = aRequest.getSession().getName();
        int id = Integer.parseInt(aRequest.getCommand().getArg());
        return new Response(receiver.removeById(username, id));
    }
}