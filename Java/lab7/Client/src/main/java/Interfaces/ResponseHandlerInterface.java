package Interfaces;

import utility.Command;

import java.nio.ByteBuffer;

public interface ResponseHandlerInterface {

    String receive(ByteBuffer buffer);

    String receive(String errorInformation);
}
