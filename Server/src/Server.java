import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000)) {

            while (true)
                try (Socket socket = server.accept();
                     PrintWriter writer = new PrintWriter(socket.getOutputStream());
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
                {
                    boolean headersFinished = false;
                    int contentLength = -1;

                    while(!headersFinished) {
                        String line = reader.readLine();
                        headersFinished = line.isEmpty();
                        if (line.startsWith("Content-Length:")) {
                            String cl = line.substring("Content-Length:".length()).trim();
                            contentLength = Integer.parseInt(cl);
                        }
                    }
                    char[] buf = new char[contentLength];  //<-- http body is here
                    reader.read(buf);

                    String colorId = new String(buf);

                    System.out.println(SetColor(colorId));
                    System.out.println("Request: " + colorId);

                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html; charset=utf-8");
                    writer.println("Access-Control-Allow-Origin: *");
                    writer.println();
                    writer.println("{\"status\": \"SUCCESS\", \"TEXT\":\""+GetColorName(colorId, "Цвет успешно изменен на")+"\"}");
                    writer.flush();

                    System.out.println("Цвет успешно изменен");

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SetColor(String colorId) {
        return switch (colorId) {
            case "1" -> Colors.ANSI_RED;
            case "2" -> Colors.ANSI_BLACK;
            case "3" -> Colors.ANSI_BLUE;
            case "4" -> Colors.ANSI_CYAN;
            case "5" -> Colors.ANSI_GREEN;
            case "6" -> Colors.ANSI_PURPLE;
            case "7" -> Colors.ANSI_WHITE;
            case "8" -> Colors.ANSI_YELLOW;
            default -> Colors.ANSI_RESET;
        };
    }

    public static String GetColorName(String colorId, String prefix) {
        return switch (colorId) {
            case "1" -> "<span style=\'color: red\'>"+prefix+" красный</span>";
            case "2" -> "<span style=\'color: black\'>"+prefix+" чёрный</span>";
            case "3" -> "<span style=\'color: blue\'>"+prefix+" синий</span>";
            case "4" -> "<span style=\'color: aqua\'>"+prefix+" голубой</span>";
            case "5" -> "<span style=\'color: green\'>"+prefix+" зелёный</span>";
            case "6" -> "<span style=\'color: purple\'>"+prefix+" фиолетовый</span>";
            case "7" -> "<span style=\'color: white\'>"+prefix+" белый</span>";
            case "8" -> "<span style=\'color: yellow\'>"+prefix+" жёлтый</span>";
            default -> "";
        };
    }
}
