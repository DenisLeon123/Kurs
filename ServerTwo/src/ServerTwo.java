import executor.ClientHandlerExecutor;
import service.ServiceMemory;
import writer.PrintWriterWrapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {

    private final static ServiceMemory serviceMemory = new ServiceMemory();
    private final static ClientHandlerExecutor clientHandlerExecutor = new ClientHandlerExecutor(serviceMemory);

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9000)) {
            while (true)
                try {
                    Socket socket = server.accept();
                    PrintWriterWrapper printWriterWrapper = new PrintWriterWrapper(socket);
                    clientHandlerExecutor.createTaskClientHandler(printWriterWrapper);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
