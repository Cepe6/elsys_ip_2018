package org.elsys.ip;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
  private static final String HOST_NAME = "localhost";
  private static final int PORT = 31111;

  public static void main(String[] args) throws IOException {
    try (Socket echoSocket = new Socket(HOST_NAME, PORT);
         // get the output stream of the socket. We are sending this
         PrintStream out = new PrintStream(echoSocket
           .getOutputStream());
         // get info from server here
         Scanner in = new Scanner(echoSocket.getInputStream());
         // keyboard input
         Scanner stdIn = new Scanner(System.in)) {
      String userInput;

      handleInput(echoSocket);

      while((userInput = stdIn.nextLine()) != null) {
        System.out.println("Sent: " + userInput);
        out.println(userInput);
      }
    }
  }

  private static void handleInput(Socket socket) {
    Thread t = new Thread(() -> {
      String serverOutput;
      Scanner in = null;
      try {
        in = new Scanner(socket.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }

      while ((serverOutput = in.nextLine()) != null) {
        System.out.println("Received from server: " + serverOutput);
      }
    });
    t.start();
  }
}




