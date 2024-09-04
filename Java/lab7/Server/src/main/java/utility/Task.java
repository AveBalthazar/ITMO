package utility;

import java.util.concurrent.Callable;

/**
 * Класс, имплементирующий таск для ForkJoinPool
 */
public class Task implements Callable<Response> {

    private final Invoker invoker;
    private final Request request;

    public Task(Invoker anInvoker, Request aRequest) {
        invoker = anInvoker;
        request = aRequest;
    }

    @Override
    public Response call() {
        return invoker.execute(request);
    }
}