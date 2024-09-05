package Interfaces;

import data.StudyGroup;
import utility.Command;
import utility.Session;

import java.net.InetSocketAddress;

public interface RequestHandlerInterface {

    String send(Command aCommand);

    String send(Command aCommand, StudyGroup studyGroup);

    void setRemoteHostSocketAddress(InetSocketAddress aSocketAddress);

    String getInformation();

    void setSocketStatus(boolean aSocketStatus);

    boolean getSocketStatus();

    void setSession(Session aSession);
}
