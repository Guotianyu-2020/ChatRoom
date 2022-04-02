package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientWriteThread {
    private Socket socket;
    private final String ClientName;

    public ClientWriteThread(Socket socket, String ClientName) {
        this.socket = socket;
        this.ClientName = ClientName;
    }

    public void write(ClientGUI client) {
        try {
            if (ClientGUI.words != null) {
                OutputStream os = this.socket.getOutputStream();
                os.write(ClientGUI.words.getBytes());
                client.outputs.append("\r\n" + this.ClientName + " :");
                client.outputs.append("\r\n" + ClientGUI.words);
                client.inputs.setText("");
                ClientGUI.words = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void uploadMyName(String name) {
        try {
            OutputStream os = this.socket.getOutputStream();
            os.write(name.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
