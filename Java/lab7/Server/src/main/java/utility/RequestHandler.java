package utility;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * Класс, обрабатывающий запросы
 */
public class RequestHandler {

    private final Invoker invoker;
    private final ForkJoinPool forkJoinPool;
    private final ExecutorService executorService;

    public RequestHandler(Invoker anInvoker, ForkJoinPool aForkJoinPool, ExecutorService anExecutorService) {
        invoker = anInvoker;
        forkJoinPool = aForkJoinPool;
        executorService = anExecutorService;
    }


    public void process(Request request, DatagramSocket datagramSocket, SocketAddress socketAddress) throws ExecutionException, InterruptedException {
        Task task = new Task(invoker, request);
        Response response = executorService.submit(task).get();
        Deliver deliver = new Deliver(datagramSocket, response, socketAddress);
        forkJoinPool.execute(deliver);
    }
}