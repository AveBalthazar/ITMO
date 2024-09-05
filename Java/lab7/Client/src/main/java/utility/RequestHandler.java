package utility;

import data.StudyGroup;
import Interfaces.RequestHandlerInterface;
import Interfaces.SessionWorkerInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;

public class RequestHandler implements RequestHandlerInterface {

    private static RequestHandler instance;
    private InetSocketAddress socketAddress;
    private boolean socketStatus;
    private Session session;

    public static RequestHandler getInstance() {
        if (instance == null) instance = new RequestHandler();
        return instance;
    }

    @Override
    public String send(Command aCommand) {
        try {
            Request request = new Request(aCommand, session);

            utility.SocketWorker socketWorker = new utility.SocketWorker(socketAddress);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            ObjectOutputStream outObj = new ObjectOutputStream(byteArrayOutputStream);
            outObj.writeObject(request);
            session.setTypeOfSession(TypeOfSession.Login);
            return socketWorker.sendRequest(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            return TextFormatting.getRedText("\tОшибка при сериализации запроса!\n");
        }
    }

    @Override
    public String send(Command aCommand, StudyGroup studyGroup) {

        if (studyGroup != null) {
            aCommand.addStudyGroup(studyGroup);
            return send(aCommand);
        } else return TextFormatting.getRedText("\tStudy group не существует, попробуйте снова!\n");
    }

    @Override
    public void setRemoteHostSocketAddress(InetSocketAddress aSocketAddress) {
        socketAddress = aSocketAddress;
    }

    @Override
    public String getInformation() {

        return "\nСтатус соединения:\n\n" +
                "Адрес:\t" + String.valueOf(socketAddress.getAddress()) + "\n" +
                "Порт:\t" + String.valueOf(socketAddress.getPort()) + "\n";
    }

    @Override
    public void setSocketStatus(boolean aSocketStatus) {
        socketStatus = aSocketStatus;
    }

    @Override
    public boolean getSocketStatus() {
        return socketStatus;
    }

    @Override
    public void setSession(Session aSession) {
        session = aSession;
    }

    public String register(Session aSession) {
        setSession(aSession);
        return send(new Command("register", ""));
    }

    public String login(Session aSession) {
        setSession(aSession);
        return send(new Command("login", ""));
    }
}