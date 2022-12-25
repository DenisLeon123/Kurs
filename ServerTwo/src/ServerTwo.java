import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {
    public static void main(String[] args)  {
        try (ServerSocket server = new ServerSocket(9000))
        {
            ServiceMemory memory = new ServiceMemory();

            while (true)
                try (
                        Socket socket = server.accept();
                        PrintWriter writer = new PrintWriter(socket.getOutputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
                {
                    double freeMem = ((double)memory.getFreeBytes()/memory.getMaxMemory()*100);
                    System.out.println("Result: " + freeMem);

                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html; charset=utf-8");
                    writer.println("Access-Control-Allow-Origin: *");
                    writer.println();
                    writer.println("{\"status\": \"SUCCESS\", \"TEXT\": \""+freeMem+"%\"}");
                    writer.flush();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
