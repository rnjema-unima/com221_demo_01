import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;

public class Client {
    public static void main(String[] qrgs) {
        try {
            Socket clientSocket = new Socket();
            // Allow address and port reuse
            clientSocket.setReuseAddress(true);

            clientSocket.connect(new InetSocketAddress("localhost", 35000));
            System.out.printf("CONNECTED to server at %s\n",
                    clientSocket.getRemoteSocketAddress().toString().split("/")[1]);

            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

            String message = inputStream.readUTF();

            System.out.printf("\tServer says %s\n", message);
            inputStream.close();
            clientSocket.close();
        } catch (IOException ioException) {
            System.err.printf("ERROR Encountered: %s",
                    ioException.getMessage());
        }

    }
}