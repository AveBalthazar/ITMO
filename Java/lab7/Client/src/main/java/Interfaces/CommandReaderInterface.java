package Interfaces;

import utility.Command;

public interface CommandReaderInterface {

    boolean enable();

    Command readCommand(String anInputString);
}
