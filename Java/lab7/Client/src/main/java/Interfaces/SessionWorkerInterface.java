package Interfaces;

import utility.Session;

import java.io.IOException;

public interface SessionWorkerInterface {

    /**
     * Получение сессии
     */
    Session getSession() throws IOException;
}
