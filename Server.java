
import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

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

            //output stream
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Greets the client
            out.write("Ciao utente " + socket.getRemoteSocketAddress() + "\n");
            out.flush();

            //Check synchronization request
            String s = in.readLine();
            if ("Voglio sincronizzarmi".equals(s)) {

                //Get current date and time
                ZonedDateTime now = ZonedDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
                //Convert the ZonedDateTime to a formatted string
                String time = now.format(formatter);

                //Outputs the time to the stream
                out.write(time);
                out.flush();

            }

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
