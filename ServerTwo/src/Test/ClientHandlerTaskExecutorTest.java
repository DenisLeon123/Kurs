import executor.ClientHandlerExecutor;
import org.junit.jupiter.api.Test;
import service.ServiceMemory;
import writer.PrintWriterWrapper;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.anyString;


class ClientHandlerTaskExecutorTest {

    @Test
    void runTest() throws IOException {
        PrintWriterWrapper printWriterWrapper = mock(PrintWriterWrapper.class);
        ServiceMemory serviceMemory = mock(ServiceMemory.class);

        ClientHandlerExecutor clientHandlerTaskExecutor = new ClientHandlerExecutor(serviceMemory);

        //WHEN
        clientHandlerTaskExecutor.createTaskClientHandler(printWriterWrapper);

        //THEN
        verify(printWriterWrapper, times(1)).closeConnection();
        verify(printWriterWrapper, times(1)).flush();
        verify(printWriterWrapper, times(1)).writeHttpHeaders();
        verify(printWriterWrapper, times(1)).writeResult(anyString());
        verify(serviceMemory, times(1)).getFreeMemory();
    }
}