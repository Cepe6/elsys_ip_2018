import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(3333);
                Socket clientSocket = serverSocket.accept();
                PrintStream out = new PrintStream(clientSocket.getOutputStream());
                Scanner in = new Scanner(clientSocket.getInputStream());
                Scanner inPut = new Scanner(System.in)) {
                new Thread(() -> {
                    while(in.hasNextLine()) {
                        System.out.println(in.nextLine());
                    }
                }).start();

                while(inPut.hasNextLine()) {
                    out.println(inPut.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
