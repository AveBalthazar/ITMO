package Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public interface ConsoleInterface {

    void setScanner(Scanner aScanner);

    void print(Object anObj);

    void print(Object anObj, boolean printPermission);

    String read() throws IOException;

    boolean getExeStatus();

    void setBufferedReader(BufferedReader aBufferedReader);

    void setExeStatus(boolean anExeStatus);
}
