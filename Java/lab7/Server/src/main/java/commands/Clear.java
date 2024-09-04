package commands;

import utility.Receiver;
import utility.Request;
import utility.Response;

/**
 * Класс, удаляющий все элементы коллекции
 */
public class Clear extends CommandAbstract {

    private final Receiver receiver;

    public Clear(Receiver aReceiver) {
        super("удаляет коллекцию", true);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        String username = aRequest.getSession().getName();
        return new Response(receiver.clear(username));
    }
}