/* Author: Jordan A. Caraballo Vega 842-15-1032
 * COMP 4096 Operating Systems - Prof. Sotero
 * Purpose: Program that works as a server that receives and Integer
 * and calculates Pi function.
 * Input: Text value (Integer) received from the client through a socket
*/
import java.net.*;
import java.io.*;

/* Define class to store output from pi function */
class PIV {
    private int value; // value to store integer sum
    public int getValue() { // getter function
        return value;
    }
    public void setValue(int value) { // setter function
        this.value = value;
    }
}

/* Define class to run thread and calculate pi */
class Pi implements Runnable {
    private int lim; // value that states the limit value to calculate Pi run function
    private PIV  piValue; // PIV values that stores the end result from Pi run function

    public Pi(PIV piValue, int lim) {
        this.lim     = lim;
        this.piValue = piValue;
    }

    /* subrouting given by Prof. Sotero */
    public void run() { // threads run from here
        int cont = 0;   // counter to sum all ocurrences of the prime numbers
        for (int n = 2; n <= lim; n++) {
            int div = 2;
            for (; div < n; div++)
                if (n % div == 0) break;
            if (div == n) cont++;
        }
        piValue.setValue(cont); // set value to PIV object
    }
}

/* Define server class and get input from server to execute pi */
public class DateServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013); // set server socket to listen at port
            /* now listen for connections */
            while (true) {
                Socket client = sock.accept();
                /* receive value from socket */
                BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                /* read the date from the socket */
                String inputLine;
                while ( (inputLine = bin.readLine()) != null) {
                    /* Print from where is the connection coming */
                    System.out.println("Conexion de " + client.getRemoteSocketAddress().toString());

                    /* Set ouput parameters to talk to client */
                    PrintWriter pout = new
                    PrintWriter(client.getOutputStream(), true);

                    /* Verify if value is not negative */
                    if (Integer.parseInt(inputLine) < 0) {
                      pout.println("El valor del cliente debe ser entero positivo.");
                    }

                    /* Create and start threads */
                    PIV piObject = new PIV(); // define PIV object
                    Thread thrd = new Thread(new Pi(piObject, Integer.parseInt(inputLine))); // create thread
                    thrd.start(); // start thread
                    try {
                        thrd.join();
                        /* write the Date to the socket */
                        pout.println("El valor "+ inputLine +" tiene "+piObject.getValue()+" primos.");
                    } catch (InterruptedException ie) { }
                }
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
