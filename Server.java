import java.io.*;
import java.net.*;

/**
 *
 * @author david
 */
public class Server {

    public static void main(String[] args) {

        int port = 2000;

        Socket socket = null;

        try {

            ServerSocket serversocket = new ServerSocket(port);

            System.out.println("In ascolto...");

            socket = serversocket.accept();

            System.out.println("Connesso con "
                    + socket.getRemoteSocketAddress());

        } catch (BindException ex) {
            System.err.println("errore apertura " + ex);
        } catch (IOException e) {
            System.err.println("errore apertura " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("errore chiusura " + e);
            }
        }

    }
}
