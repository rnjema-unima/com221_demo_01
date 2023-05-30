import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] qrgs) {
        try {
            Socket clientSocket = new Socket();
            // Allow address and port reuse
            Scanner consoleScanner = new Scanner(System.in);
            clientSocket.setReuseAddress(true);

            clientSocket.connect(new InetSocketAddress("localhost", 35000));
            System.out.printf("CONNECTED to server at %s\n",
                    clientSocket.getRemoteSocketAddress().toString().split("/")[1]);

            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            String message = "";
            while (true) {
                System.out.print("Enter message :");
                message = consoleScanner.nextLine();
                outputStream.writeUTF(message);
                if (message.equalsIgnoreCase("close")) {
                    break;
                }
                String echoMessage = inputStream.readUTF();
                System.out.printf("Server says %s \n", echoMessage);
            }

            // System.out.printf("\tServer says %s\n", message);
            inputStream.close();
            clientSocket.close();
        } catch (IOException ioException) {
            System.err.printf("ERROR Encountered: %s",
                    ioException.getMessage());
        }

    }
}