package utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.*;

/**
 * Класс для получения ответов со стороны клиента
 */
public class RequestReceiver extends RecursiveTask<Boolean> {

    private final DatagramPacket datagramPacket;
    private final DatagramSocket datagramSocket;
    private final RequestHandler requestHandler;
    public static final Logger logger = LoggerFactory.getLogger("Server");

    public RequestReceiver(DatagramSocket aDatagramSocket, DatagramPacket aDatagramPacket,
                           Invoker anInvoker, ForkJoinPool aForkJoinPool, ExecutorService executorService) throws SocketException {

        datagramSocket = aDatagramSocket;
        requestHandler = new RequestHandler(anInvoker, aForkJoinPool, executorService);
        datagramPacket = aDatagramPacket;
    }

    @Override
    protected Boolean compute() {
        try {
            ObjectInputStream inObj = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData()));
            Request request = AutoGenFieldsSetter.setFields((Request) inObj.readObject());
            logger.info("Server received command: {}", request);

            requestHandler.process(request, datagramSocket, datagramPacket.getSocketAddress());
            return Boolean.TRUE;
        } catch (ClassNotFoundException e) {
            logger.info("Client sent outdated request!");
            return Boolean.FALSE;
        } catch (IOException e) {
            logger.info("Some problem's with getting request!");
            return Boolean.FALSE;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}