package Server;

import java.io.IOException;

public class TCPServer {
    public static void main(String[] args) throws IOException {
            ServerThread st = new ServerThread(8888);
            st.runServer();
    }
}
