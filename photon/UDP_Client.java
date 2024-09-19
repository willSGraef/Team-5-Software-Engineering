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

    //Constructor that inits local host ip
    public UDP_Client() throws UnknownHostException {
        ip = InetAddress.getLocalHost();
    }
    
    //Send data method, (From client to server)
    public void UDP_SendData(String message) throws IOException {
        data = message.getBytes();
        //create client socket
        DatagramSocket clientSocket = new DatagramSocket(68);

        DatagramPacket packetSend = new DatagramPacket (data, data.length, ip, 67);

        clientSocket.send(packetSend);

        clientSocket.close();
    }
}
