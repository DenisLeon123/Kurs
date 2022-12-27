package task;

import service.ServiceMemory;
import writer.PrintWriterWrapper;

import java.io.IOException;

public class ClientHandlerTask implements Runnable {

    private final PrintWriterWrapper printWriterWrapper;
    private final ServiceMemory serviceMemory;

    public ClientHandlerTask(PrintWriterWrapper printWriterWrapper, ServiceMemory serviceMemory) {
        this.printWriterWrapper = printWriterWrapper;
        this.serviceMemory = serviceMemory;
    }

    @Override
    public void run() {
        try {
            double freeMem = serviceMemory.getFreeMemory();

            Thread.sleep(1000);

            printWriterWrapper.writeHttpHeaders();
            printWriterWrapper.writeResult(String.valueOf(freeMem));
            printWriterWrapper.flush();
            printWriterWrapper.closeConnection();

            System.out.println("Result: " + freeMem);
            System.out.println("Количество свободной памяти расчитано\n");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
