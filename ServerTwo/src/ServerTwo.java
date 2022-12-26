import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {
    public static void main(String[] args)  {
        try (ServerSocket server = new ServerSocket(9000))
        {
            while (true)
                try
                {
                    Socket socket = server.accept();
                    ClientHandler clientHandler = new ClientHandler(socket);
                    new Thread(clientHandler).start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter writer;

        ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.writer = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                this.writer = null;
            }
        }

        private void writeHttpHeaders() {
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html; charset=utf-8");
            writer.println("Access-Control-Allow-Origin: *");
            writer.println();
        }

        private void writeResult(String resultText) {
            writer.println("{\"status\": \"SUCCESS\", \"TEXT\":\""+resultText+"\"}");

        }

        private void flush() {
            writer.flush();
        }

        private void closeConnection() throws IOException {
            writer.close();
            socket.close();
        }

        @Override
        public void run() {
            try {
                if (this.writer == null) {
                    socket.close();
                    return;
                }

                ServiceMemory memory = new ServiceMemory();
                double freeMem = ((double)memory.getFreeBytes()/memory.getMaxMemory()*100);

                Thread.sleep(1000);

                writeHttpHeaders();
                writeResult(String.valueOf(freeMem));
                flush();
                closeConnection();

                System.out.println("Result: " + freeMem);
                System.out.println("Количество свободной памяти расчитано\n");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
