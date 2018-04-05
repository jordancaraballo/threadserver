/* Author: Jordan A. Caraballo Vega 842-15-1032
 * COMP 4096 Operating Systems - Prof. Sotero
 * Purpose: Program that works as a client that send and Integer
 * to server, and receives PI function output from server.
 * Input: None, receives output from server
*/
import java.net.*;
import java.io.*;

public class DateClient {
    public static void main(String[] args) {
        try {
            /* make connection to server socket */
            Socket sock = new Socket(args[0],6013);

            /* Send integer value to server */
            PrintWriter pout = new
            PrintWriter(sock.getOutputStream(), true);
            pout.println(args[1]);

            /* Get input from server */
            InputStream in = sock.getInputStream();
            BufferedReader bin = new
            BufferedReader(new InputStreamReader(in));

            /* read the output from server */
            String line;
            line = bin.readLine();
            System.out.println(line);
            sock.close();
        }
        catch (IOException ioe) {
            System.out.println("Verify that the server is up and running...");
            System.err.println(ioe);
        }
    }
}
