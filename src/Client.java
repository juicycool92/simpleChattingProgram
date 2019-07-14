import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    Socket sc;
    BufferedWriter bw;
    BufferedReader br;
    String name = "";
    public Client() {
        sc = new Socket();
        try {
            sc.connect( new InetSocketAddress("127.0.0.1",3001));
            Thread th = new Thread( new ClientReceiver(sc));
            th.start();
            br = new BufferedReader( new InputStreamReader( System.in ));
            bw = new BufferedWriter( new OutputStreamWriter( sc.getOutputStream() ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client is up");
    }
    public void send() {
        System.out.print("name : ");
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while ( true ) {
            String msg = null;
            try {
                msg = br.readLine();
                bw.write( name+" : "+msg + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static  void main(String [] args) {
        new Client().send();
    }
}
