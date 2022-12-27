package writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PrintWriterWrapper {
    private final PrintWriter writer;
    private final Socket socket;

    public PrintWriterWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    public void writeHttpHeaders() {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println("Access-Control-Allow-Origin: *");
        writer.println();
    }

    public void writeResult(String resultText) {
        writer.println("{\"status\": \"SUCCESS\", \"TEXT\":\"" + resultText + "\"}");

    }

    public void flush() {
        writer.flush();
    }

    public void closeConnection() throws IOException {
        writer.close();
        socket.close();
    }
}
