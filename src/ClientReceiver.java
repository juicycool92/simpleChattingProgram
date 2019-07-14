import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver implements Runnable {
    BufferedReader br;
    Socket sc;
    public ClientReceiver( Socket sc ) {
        this.sc = sc;
        try {
            br = new BufferedReader( new InputStreamReader( sc.getInputStream() ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        String msg = null;
        while ( true ) {
            try {
                msg = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println( msg );
        }
    }
}
