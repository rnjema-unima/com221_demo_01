import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);

            // Associates socket with IP address and port number
            serverSocket.bind(new InetSocketAddress("localhost", 35000));

            System.out.printf("SUCCESS : Server bound and listening at %s\n",
                    serverSocket.getLocalSocketAddress().toString().split("/")[1]);

            // Accept incoming connections
            Socket clientSocket = serverSocket.accept();
            System.out.printf("\tACCEPTED connection from client<%s>\n",
                    clientSocket.getRemoteSocketAddress().toString().split("/")[1]);

            // Access underlying socket streams
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            outputStream.writeUTF("Hello there!");
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            serverSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
