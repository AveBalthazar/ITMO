package commands;

import utility.Receiver;
import utility.Request;
import utility.Response;
import utility.TypeOfAnswer;

/**
 * Класс для вывода всей информации о коллекции
 */
public class Info extends CommandAbstract {

    private final Receiver receiver;

    public Info(Receiver aReceiver) {
        super("выводиь информацию о коллекции (type, "
                + "initialization date, number of elements, etc.) в стандартный поток вывода", false);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        String username = aRequest.getSession().getName();
        receiver.addToHistory(username, "info");
        return new Response(receiver.info(), TypeOfAnswer.SUCCESSFUL);
    }
}