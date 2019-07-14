import java.io.*;
import java.net.Socket;

public class SocketInfo {
    private Socket sc;
    private BufferedReader br;
    private BufferedWriter bw;
    public SocketInfo( Socket sc ) throws IOException {
        this.sc = sc;
        br = new BufferedReader( new InputStreamReader( sc.getInputStream()));
        bw = new BufferedWriter( new OutputStreamWriter( sc.getOutputStream()));
    }
    public void sendMsg( String msg ) {
        try {
            bw.write( msg+"\n");
            bw.flush();
            System.out.println("send msg :"+msg+" to socket : "+sc.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Socket getSc() {
        return sc;
    }

    public void setSc(Socket sc) {
        this.sc = sc;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }
}
