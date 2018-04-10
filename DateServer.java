/* Author: Jordan A. Caraballo Vega 842-15-1032
 * COMP 4096 Operating Systems - Prof. Sotero
 * Purpose: Program that works as a server that receives and Integer
 * and calculates Pi function.
 * Input: Text value (Integer) received from the client through a socket
 * User can set port from command line to
*/
import java.net.*;
import java.io.*;

/* Define class to enable concurrent threads */
class MultipleThreads extends Thread {
  protected Socket socket; // socket for each client

  /* get input from main class to start threads */
  public MultipleThreads(Socket clientSocket) {
    this.socket = clientSocket; // socket established for a new client
  }

  /* threads run from here */
  public void run() {
      InputStream    clientInput = null; /* socket for client input */
      BufferedReader bufferInput = null; /* receive value from socket */
      PrintWriter    printOutput = null; /* establish output parameters to talk to client */
      /* try and catch it case something went wrong */
      try {
          clientInput = socket.getInputStream(); /* set socket for client input */
          bufferInput = new BufferedReader(new InputStreamReader(clientInput)); /* set value from socket */
          printOutput = new PrintWriter(socket.getOutputStream(), true); /* set ouput parameters to talk to client */
      } catch (IOException e) {
          return;
      }

      /* Print from where the connection is coming and start reading the socket */
      System.out.println("Connection from " + socket.getRemoteSocketAddress().toString());

      /* Read and execute Pi steps */
      String inputLine; /* variable to store client input */
      while (true) {
          /* first try: get the connection working properly */
          try {
              inputLine = bufferInput.readLine(); /* read input from client */
              if ((inputLine == null) || inputLine.equalsIgnoreCase("QUIT")) {
                  socket.close(); // close the socket if the is not input or quit
                  return; // get out from here
              }
              else {
                  /* Verify if value is not negative */
                  try {
                  if (Integer.parseInt(inputLine) < 0) {
                      printOutput.println("The value needs to be a positive integer. Closing connection.");
                  }
                  else {
                      /* subrouting given by Prof. Sotero */
                      int piValue = 0; // counter to sum all ocurrences of the prime numbers
                      for (int n = 2; n <= Integer.parseInt(inputLine); n++) {
                          int div = 2;
                          for (; div < n; div++)
                              if (n % div == 0) break;
                          if (div == n) piValue++;
                      }
                      /* Send to the client the value of the pi function */
                      printOutput.println("The value "+ inputLine +" has "+ piValue +" prime numbers.");
                  }
                } catch (NumberFormatException ex ) { printOutput.println("The value needs to be a positive integer. Closing connection."); }
              }
          } catch (IOException e) {
              e.printStackTrace();
              return;
          }
       }
    }
}

/* Define server class and get input from server to execute pi */
public class DateServer {
    public static void main(String[] args) {
        /* Establish server socket variables */
        ServerSocket serverSocket = null;
        Socket       socket = null;

        /* Start server socket at port default: 6013 */
        int port = 6013;
        if (args.length != 0) { port = Integer.parseInt(args[0]); }

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* While socket is active, maintain connection alive */
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            new MultipleThreads(socket).start();
        }
    }
}
