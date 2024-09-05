package Interfaces;

import utility.Command;

public interface ValidatorInterface {

    boolean nonObjectArgumentCommands(Command aCommand);

    boolean objectArgumentCommands(Command aCommand);

    boolean validateScriptArgumentCommand(Command aCommand);
}
