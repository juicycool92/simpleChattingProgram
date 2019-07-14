import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket sc;
    ClientList clientList;
    public Server() {
        clientList = ClientList.getInstance();
        try {
            sc = new ServerSocket(3001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is up");
    }
    private void acceptSocket() {
        Socket socket = null;
        while( true ) {
            try {
                socket = sc.accept();
                clientList.addClients(socket);
                SocketInfo si = clientList.getSelectedSocketInfo(socket);
                Thread th = new Thread( new ServerReceiver( si.getSc(), si.getBr() ) );
                th.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Server serv = new Server();
        serv.acceptSocket();
    }
}
