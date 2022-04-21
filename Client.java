
import java.io.*;
import java.net.*;

/**
 *
 * @author david
 */
public class Client {
    public static void main(String[] args) {

        int port = 2000;

        Socket socket = null;

        try {

            socket = new Socket("127.0.0.1", port);

            System.out.println("Connesso con "
                    + socket.getRemoteSocketAddress());

            System.out.println("La porta usata e' " +  socket.getLocalPort());
            
            //output stream
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //Gets the incoming stream
            System.out.println(in.readLine());
            
            //Sends synchronization request
            out.write("Voglio sincronizzarmi\n");
            out.flush();
            
            //Gets the timestamp
            System.out.println(in.readLine());
            
        } catch (ConnectException ex) {
            System.err.println("errore connessione " + ex);
        } catch (IOException e) {
            System.err.println("errore apertura " + e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("errore chiusura " + e);
            }
        }

    }
}
