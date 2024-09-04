package commands;

import utility.Receiver;
import utility.Request;
import utility.Response;
import utility.TypeOfAnswer;

/**
 * Класс для вывода количества элементов коллекции, поле studentsCount которых меньше введённого
 */
public class CountLessThanStudentsCount extends CommandAbstract {

    private final Receiver receiver;

    public CountLessThanStudentsCount(Receiver aReceiver) {
        super("выводит количество элементов коллекции, поле studentsCount которых меньше введённого", false);
        receiver = aReceiver;
    }

    @Override
    public Response execute(Request aRequest) {
        String username = aRequest.getSession().getName();
        Integer count = Integer.valueOf(aRequest.getCommand().getArg());
        Long countStudyGroups = receiver.countLessThanStudentsCount(count, username);
        return (countStudyGroups == null)
                ? new Response(TypeOfAnswer.EMPTYCOLLECTION)
                : new Response(countStudyGroups, TypeOfAnswer.SUCCESSFUL);
    }
}