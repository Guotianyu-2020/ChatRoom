package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientWriteThread {
    private Socket socket;
    private String ClientName;

    public ClientWriteThread(Socket socket, String ClientName) {
        this.socket = socket;
        this.ClientName = ClientName;
    }

    public void write() {
        try {
            if (ClientGUI.words != null) {
                OutputStream os = this.socket.getOutputStream();
                os.write(ClientGUI.words.getBytes());
                ClientGUI.outputs.append("\r\n" + this.ClientName + " : \r\n");
                ClientGUI.outputs.append("\r\n" + ClientGUI.words);
                ClientGUI.inputs.setText("");
                ClientGUI.words = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
