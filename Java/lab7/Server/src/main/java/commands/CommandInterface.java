package commands;

import utility.Request;
import utility.Response;

public interface CommandInterface {

    String getDescription();

    boolean getAuthorizationStatus();

    Response execute(Request aRequest);
}