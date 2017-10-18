import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            try (Socket echoSocket = new Socket("localhost", 3333);
                // get the output stream of the socket. We are sending this
                PrintStream out = new PrintStream(echoSocket
                         .getOutputStream());
                // get info from server here
                Scanner in = new Scanner(echoSocket.getInputStream());
                // keyboard input
                Scanner stdIn = new Scanner(System.in)) {
                new Thread(() -> {
                    while(in.hasNextLine()) {
                        System.out.println(in.nextLine());
                    }
                }).start();

                while(stdIn.hasNextLine()) {
                    out.println(stdIn.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
