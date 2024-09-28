package photon;
import java.io.IOException;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDP_Client {
    //Class variables
    byte data[] = null;
    InetAddress ip;
    private DatagramSocket clientSocket;

    //Constructor that inits local host ip
    public UDP_Client() throws UnknownHostException, IOException {
        ip = InetAddress.getLocalHost();
        clientSocket = new DatagramSocket(7500); // Binding to port 7500
    }
    
    //Send data method, (From client to server)
    public void UDP_SendData(String message) throws IOException {
        data = message.getBytes();

        DatagramPacket packetSend = new DatagramPacket (data, data.length, ip, 7501);

        clientSocket.send(packetSend);
        System.out.println("Sent equipment code: " + message);
    }

    public void close() {
        clientSocket.close();
    }
}
