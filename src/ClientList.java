import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientList {
    private static ClientList myInstance = null;
    private ArrayList<SocketInfo> clients;

    private ClientList() {
        clients = new ArrayList<>();
    }

    public static ClientList getInstance() {
        if ( myInstance == null )
            myInstance =  new ClientList();
        return myInstance;
    }

    public  void addClients ( Socket sc ) {
        try {
            clients.add( new SocketInfo(sc) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  broadCastToAllClients ( Socket sc, String msg ) {
        clients.stream().forEach( item -> {
            if ( !item.getSc().equals(sc) )
                item.sendMsg(msg);
        });
    }
    public SocketInfo getSelectedSocketInfo ( Socket sc ){
        for( int i = 0 ; i < clients.size() ; i ++) {
            if ( clients.get(i).getSc().equals(sc) ) return clients.get(i);
        }
        return null;
    }
}
