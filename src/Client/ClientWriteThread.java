package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientWriteThread {
    private Socket socket;

    public ClientWriteThread(Socket socket) {
        this.socket = socket;
    }

    public void write() {
        try {
            if (ClientGUI.words != null) {
                OutputStream os = this.socket.getOutputStream();
                os.write(ClientGUI.words.getBytes());
                ClientGUI.outputs.append("\n");
                ClientGUI.outputs.append(ClientGUI.words);
                ClientGUI.inputs.setText("");
                ClientGUI.words = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
