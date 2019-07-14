import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver implements Runnable {
    ClientList clientList = ClientList.getInstance();
    BufferedReader br;
    Socket sc;
    public ServerReceiver (Socket sc, BufferedReader br) {
        this.br = br;
        this.sc = sc;
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
            clientList.broadCastToAllClients(sc, msg);
        }
    }
}
