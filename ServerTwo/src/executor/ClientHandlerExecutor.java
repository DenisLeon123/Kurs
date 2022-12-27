package executor;

import service.ServiceMemory;
import task.ClientHandlerTask;
import writer.PrintWriterWrapper;

public class ClientHandlerExecutor {
    private final ServiceMemory serviceMemory;

    public ClientHandlerExecutor(ServiceMemory serviceMemory) {
        this.serviceMemory = serviceMemory;
    }

    public void createTaskClientHandler(PrintWriterWrapper printWriterWrapper) {
        ClientHandlerTask clientHandlerTask = new ClientHandlerTask(printWriterWrapper, serviceMemory);
        clientHandlerTask.run();
    }
}
