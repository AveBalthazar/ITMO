package Interfaces;

public interface SocketWorkerInterface {

    /**
     * Отправка сокета к серверу
     */
    String sendRequest(byte[] serializedRequest);
}
