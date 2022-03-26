package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientListenThread {
    private Socket socket;
    private String ClientName;

    public ClientListenThread(Socket socket, String ClientName) {
        this.socket = socket;
        this.ClientName = ClientName;
    }

    public void listen(ClientGUI client) {
        InputStream is;
        try {
            is = this.socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            String s = new String(bytes, 0, len);
            if (s != null) {
                client.outputs.append("\r\n" + "Message from : " + this.ClientName + "\r\n");
                client.outputs.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
